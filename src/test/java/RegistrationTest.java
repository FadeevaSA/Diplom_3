import api.RequestsApi;
import api.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObject.LoginForm;
import pageObject.RegistrationForm;

import static constants.UrlConstants.REGISTRATION_PAGE;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    private RequestsApi requestsApi;

    @Before
    public void start() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(REGISTRATION_PAGE);
        requestsApi = new RequestsApi();
    }

    @Test
    @DisplayName("Successful user registration")
    @Description("After successful user registration login window opens")
    public void successfulRegistration() {
        name = "Smith";
        email = "smith@gmail.com";
        password = "33333333333";
        RegistrationForm testUser = new RegistrationForm(driver);
        testUser.userRegistration(name, email, password);
        testUser.waitLoginPage();
        LoginForm loginForm = new LoginForm(driver);
        assertEquals("Войти", loginForm.checkVisibilityOfLoginButton());
    }

    @Test
    @DisplayName("Unsuccessful user registration")
    @Description("Checking error for incorrect password when registration fails")
    public void registrationWithIncorrectPassword() {
        name = "Ivan";
        email = "ivan@gmail.com";
        password = "33";
        RegistrationForm testUser = new RegistrationForm(driver);
        testUser.userRegistration(name, email, password);
        assertEquals("Некорректный пароль", testUser.checkVisibilityOfPasswordError());
    }

    @After
    public void afterTest() {
        driver.quit();
        UserData userAuthorization = new UserData(email, password);
        requestsApi.authorizeAndDeleteUser(userAuthorization);
    }
}
