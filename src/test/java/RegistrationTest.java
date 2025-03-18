import ru.stellarburgers.api.RequestsApi;
import ru.stellarburgers.api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pages.LoginForm;
import ru.stellarburgers.pages.RegistrationForm;

import static ru.stellarburgers.constants.UrlConstants.REGISTRATION_PAGE;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private UserFactory userFactory;
    private UserData user;

    @Before
    public void startTest() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(REGISTRATION_PAGE);
        requestsApi = new RequestsApi();
        userFactory = new UserFactory();
    }

    @Test
    @DisplayName("Successful user registration")
    @Description("After successful user registration login window opens")
    public void testSuccessfulRegistration() {
        user = userFactory.createRandomUser();
        RegistrationForm testUser = new RegistrationForm(driver);
        testUser.userRegistration(user.getName(), user.getEmail(), user.getPassword());
        testUser.waitLoginPage();
        LoginForm loginForm = new LoginForm(driver);
        assertEquals("Войти", loginForm.checkVisibilityOfLoginButton());
    }

    @Test
    @DisplayName("Unsuccessful user registration")
    @Description("Checking error for incorrect password when registration fails")
    public void testRegistrationWithIncorrectPassword() {
        user = userFactory.createUserWithInvalidPassword();
        RegistrationForm testUser = new RegistrationForm(driver);
        testUser.userRegistration(user.getName(), user.getEmail(), user.getPassword());
        assertEquals("Некорректный пароль", testUser.checkVisibilityOfPasswordError());
    }

    @After
    public void afterTest() {
        driver.quit();
        UserData userAuthorization = new UserData(user.getEmail(), user.getPassword());
        requestsApi.authorizeAndDeleteUser(userAuthorization);
    }
}
