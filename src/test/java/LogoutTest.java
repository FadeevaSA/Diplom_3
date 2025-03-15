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

public class LogoutTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private MainPage mainPage;
    private Response response;
    private final String email = "elena@gmail.com";
    private final String password = "555555";
    private final String name = "Elena";

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
        mainPage = new MainPage(driver);
        mainPage.clickOnButtonPersonalAccount();
    }

    @Test
    @DisplayName("Successful logout")
    @Description("Successful logout from personal account")
    public void successfulLogout() {
        PersonalAccountPage profile = new PersonalAccountPage(driver);
        profile.clickLogoutButton();
        LoginForm loginForm = new LoginForm(driver);
        assertEquals("Войти", loginForm.checkVisibilityOfLoginButton());
    }

    @After
    public void afterTest() {
        driver.quit();
        requestsApi.deleteUserAfterTest(response);
    }
}

