import api.RequestsApi;
import api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.LoginForm;
import pageObject.MainPage;

import static constants.UrlConstants.*;

import static org.junit.Assert.assertEquals;
import static pageObject.MainPage.BUTTON_LOGIN_TO_ACCOUNT;
import static pageObject.MainPage.BUTTON_PERSONAL_ACCOUNT;
import static pageObject.PasswordRecoveryPage.BUTTON_LOGIN_PASSWORD_RECOVERY_PAGE;
import static pageObject.RegistrationForm.BUTTON_LOGIN_REGISTRATION_FORM;

@RunWith(Parameterized.class)
public class UserLoginParameterizedTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private String email;
    private String password;
    private String name;
    private Response response;
    private final String pageUrl;
    private final By button;

    public UserLoginParameterizedTest(String pageUrl, By button) {
        this.pageUrl = pageUrl;
        this.button = button;
    }

    @Parameterized.Parameters(name = "Page: {0}, Button: {1}")
    public static Object[][] testData() {
        return new Object[][]{
                {MAIN_PAGE, BUTTON_LOGIN_TO_ACCOUNT},
                {REGISTRATION_PAGE, BUTTON_LOGIN_REGISTRATION_FORM},
                {MAIN_PAGE, BUTTON_PERSONAL_ACCOUNT},
                {PASSWORD_RECOVERY_PAGE, BUTTON_LOGIN_PASSWORD_RECOVERY_PAGE}
        };
    }

    @Before
    public void start() {
        requestsApi = new RequestsApi();
        name = "Den";
        email = "den@gmail.com";
        password = "1231230";
        UserData user = new UserData(email, password, name);
        response = RequestsApi.sendPostRequestUserCreating(user);
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Successful user login")
    @Description("Successful user login from different pages")
    public void successfulLogin() {
        driver.get(pageUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnButtonLogin(button);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.userLogin(email, password);
        assertEquals("Оформить заказ", mainPage.checkVisibilityOfButtonPlaceAnOrder());
    }

    @After
    public void afterTest() {
        driver.quit();
        requestsApi.deleteUserAfterTest(response);
    }
}
