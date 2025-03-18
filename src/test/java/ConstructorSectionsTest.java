import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import static ru.stellarburgers.constants.UrlConstants.MAIN_PAGE;
import static org.junit.Assert.assertTrue;

import ru.stellarburgers.pages.MainPage;

public class ConstructorSectionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void startTest() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(MAIN_PAGE);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Constructor sections Buns")
    @Description("Successful transition to the constructor sections: Buns")
    public void testCheckingConstructorSectionBuns() {
        boolean isElementInViewport = mainPage.checkConstructorSectionBuns();
        assertTrue(isElementInViewport);
    }

    @Test
    @DisplayName("Constructor sections Sauces")
    @Description("Successful transition to the constructor sections: Sauces")
    public void testCheckingConstructorSectionSauces() {
        boolean isElementInViewport = mainPage.checkConstructorSectionSauces();
        assertTrue(isElementInViewport);
    }

    @Test
    @DisplayName("Constructor sections Fillings")
    @Description("Successful transition to the constructor sections: Fillings")
    public void testCheckingConstructorSectionFillings() {
        boolean isElementInViewport = mainPage.checkConstructorSectionFillings();
        assertTrue(isElementInViewport);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}

