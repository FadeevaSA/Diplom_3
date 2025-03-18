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

public class GoToConstructorTest {
    private WebDriver driver;
    private RequestsApi requestsApi;
    private MainPage mainPage;
    private Response response;
    private PersonalAccountPage profile;

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
        mainPage = new MainPage(driver);
        mainPage.clickOnButtonPersonalAccount();
        profile = new PersonalAccountPage(driver);
    }

    @Test
    @DisplayName("Open constructor by logo Stellar Burgers")
    @Description("Successful open constructor by clicking on the Stellar Burgers logo")
    public void testSuccessfulOpenConstructorByLogoStellarBurgers() {
        profile.clickLogoStellarBurgers();
        assertEquals("Соберите бургер", mainPage.checkVisibilityOfAssembleTheBurger());
    }

    @Test
    @DisplayName("Open constructor by constructor button")
    @Description("Successful open constructor by clicking on constructor button")
    public void testSuccessfulOpenConstructorByConstructorButton() {
        profile.clickConstructorButton();
        assertEquals("Соберите бургер", mainPage.checkVisibilityOfAssembleTheBurger());
    }


    @After
    public void afterTest() {
        driver.quit();
        requestsApi.deleteUserAfterTest(response);
    }
}
