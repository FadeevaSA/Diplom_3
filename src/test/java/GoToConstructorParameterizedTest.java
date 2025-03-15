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
import pageObject.PersonalAccountPage;

import static constants.UrlConstants.LOGIN_PAGE;
import static org.junit.Assert.assertEquals;
import static pageObject.MainPage.*;

@RunWith(Parameterized.class)
public class GoToConstructorParameterizedTest {
    private final By button;
    private WebDriver driver;
    private RequestsApi requestsApi;
    private MainPage mainPage;
    private Response response;
    private final String email = "elena@gmail.com";
    private final String password = "555555";
    private final String name = "Elena";

    public GoToConstructorParameterizedTest(By button) {
        this.button = button;
    }

    @Parameterized.Parameters(name = "Тест {index}")
    public static Object[][] testButton() {
        return new Object[][]{
                {LOGO_STELLAR_BURGERS},
                {CONSTRUCTOR_BUTTON}
        };
    }

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
    @DisplayName("Successful open constructor from personal account")
    @Description("Successful open constructor by clicking on “Constructor” and on the Stellar Burgers logo")
    public void successfulOpenConstructor() {
        PersonalAccountPage profile = new PersonalAccountPage(driver);
        profile.openConstructor(button);
        assertEquals("Соберите бургер", mainPage.checkVisibilityOfAssembleTheBurger());
    }

    @After
    public void afterTest() {
        driver.quit();
        requestsApi.deleteUserAfterTest(response);
    }
}
