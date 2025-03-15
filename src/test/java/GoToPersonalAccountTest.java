import api.RequestsApi;
import api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginForm;
import pageObject.MainPage;
import pageObject.PersonalAccountPage;

import static constants.UrlConstants.LOGIN_PAGE;
import static org.junit.Assert.assertEquals;

public class GoToPersonalAccountTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private Response response;
    private final String email = "vova@gmail.com";
    private final String password = "7777777";
    private final String name = "Vladimir";

    @Before
    public void start() {
        requestsApi = new RequestsApi();
        UserData user = new UserData(email, password, name);
        response = RequestsApi.sendPostRequestUserCreating(user);
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.userLogin(email, password);
    }

    @Test
    @DisplayName("Successful transition to your personal account")
    @Description("After transition to your personal account order history button is displayed")
    public void successfulTransitionToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnButtonPersonalAccount();
        PersonalAccountPage profile = new PersonalAccountPage(driver);
        assertEquals("История заказов", profile.checkVisibilityOfHistoryButton());
    }

    @After
    public void afterTest() {
        driver.quit();
        requestsApi.deleteUserAfterTest(response);
    }
}
