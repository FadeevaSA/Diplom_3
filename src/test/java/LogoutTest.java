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
import ru.stellarburgers.pages.PersonalAccountPage;

import static ru.stellarburgers.constants.UrlConstants.LOGIN_PAGE;
import static org.junit.Assert.assertEquals;

public class LogoutTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private Response response;

    @Before
    public void startTest() {
        requestsApi = new RequestsApi();
        UserFactory userFactory = new UserFactory();
        UserData user = userFactory.createRandomUser();
        response = RequestsApi.sendPostRequestUserCreating(user);
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_PAGE);
        LoginForm loginForm = new LoginForm(driver);
        loginForm.userLogin(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnButtonPersonalAccount();
    }

    @Test
    @DisplayName("Successful logout")
    @Description("Successful logout from personal account")
    public void testSuccessfulLogout() {
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

