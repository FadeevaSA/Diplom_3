package ru.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestsApi extends BaseApi {

    public static final String USER_CREATING = "/api/auth/register";
    public static final String DELETE_USER = "/api/auth/user";
    public static final String USER_AUTHORIZATION = "/api/auth/login";
    private String userAccessToken;


    @Step("Send POST request to /api/auth/register")
    public static Response sendPostRequestUserCreating(UserData user) {
        return requestSpecification.body(user).when().post(USER_CREATING);
    }

    @Step("Send POST request to /api/auth/login")
    public static Response sendPostRequestUserAuthorization(UserData user) {
        return requestSpecification.body(user).when().post(USER_AUTHORIZATION);
    }

    @Step("Send POST request to /api/auth/register and get accessToken")
    public static String getAccessToken(Response response) {
        String userAccessToken = response.then().extract().body().path("accessToken");
        return userAccessToken;
    }

    @Step("Send DELETE request to /api/auth/user")
    public static void deleteUser(String userAccessToken) {
        RequestSpecification deleteRequest = RestAssured.given().header("Content-Type", "application/json").header("Authorization", userAccessToken);
        deleteRequest.when().delete(DELETE_USER).then().statusCode(202);
    }

    @Step("Send request to authorization and delete user")
    public void authorizeAndDeleteUser(UserData user) {
        Response response = sendPostRequestUserAuthorization(user);
        userAccessToken = getAccessToken(response);
        if (userAccessToken != null) {
            deleteUser(userAccessToken);
        }
    }

    @Step("Send request to delete user")
    public void deleteUserAfterTest(Response response) {
        userAccessToken = getAccessToken(response);
        if (userAccessToken != null) {
            deleteUser(userAccessToken);
        }
    }
}
