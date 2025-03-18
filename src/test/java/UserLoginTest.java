import ru.stellarburgers.api.RequestsApi;
import ru.stellarburgers.api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pages.LoginForm;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.PasswordRecoveryPage;
import ru.stellarburgers.pages.RegistrationForm;

import static org.junit.Assert.assertEquals;
import static ru.stellarburgers.constants.UrlConstants.*;

public class UserLoginTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private Response response;
    private UserData user;
    private MainPage mainPage;
    private LoginForm loginForm;


    @Before
    public void startTest() {
        requestsApi = new RequestsApi();
        UserFactory userFactory = new UserFactory();
        user = userFactory.createRandomUser();
        response = RequestsApi.sendPostRequestUserCreating(user);
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        loginForm = new LoginForm(driver);
    }

    @Test
    @DisplayName("Successful user login by button login to account")
    @Description("Successful user login from main page by button login to account")
    public void testSuccessfulLoginWithLoginToAccountButton() {
        driver.get(MAIN_PAGE);
        mainPage.clickLoginToAccountButton();
        loginForm.userLogin(user.getEmail(), user.getPassword());
        assertEquals("Оформить заказ", mainPage.checkVisibilityOfButtonPlaceAnOrder());
    }

    @Test
    @DisplayName("Successful user login by button personal account")
    @Description("Successful user login from main page by button personal account")
    public void testSuccessfulLoginPersonalAccountButton() {
        driver.get(MAIN_PAGE);
        mainPage.clickPersonalAccountButton();
        loginForm.userLogin(user.getEmail(), user.getPassword());
        assertEquals("Оформить заказ", mainPage.checkVisibilityOfButtonPlaceAnOrder());
    }

    @Test
    @DisplayName("Successful user login by button login")
    @Description("Successful user login from password recovery page by button login")
    public void testSuccessfulLoginFromPasswordRecoveryPage() {
        driver.get(PASSWORD_RECOVERY_PAGE);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickOnButtonLogin();
        loginForm.userLogin(user.getEmail(), user.getPassword());
        assertEquals("Оформить заказ", mainPage.checkVisibilityOfButtonPlaceAnOrder());
    }

    @Test
    @DisplayName("Successful user login registration page")
    @Description("Successful user login from registration page by button login")
    public void testSuccessfulLoginFromRegistrationPage() {
        driver.get(REGISTRATION_PAGE);
        RegistrationForm registrationForm = new RegistrationForm(driver);
        registrationForm.clickLoginButton();
        loginForm.userLogin(user.getEmail(), user.getPassword());
        assertEquals("Оформить заказ", mainPage.checkVisibilityOfButtonPlaceAnOrder());
    }

    @After
    public void afterTest() {
        driver.quit();
        requestsApi.deleteUserAfterTest(response);
    }
}
