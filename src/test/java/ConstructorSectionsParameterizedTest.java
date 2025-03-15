import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;

import static constants.UrlConstants.MAIN_PAGE;
import static org.junit.Assert.assertTrue;
import static pageObject.MainPage.*;

import pageObject.MainPage;

@RunWith(Parameterized.class)
public class ConstructorSectionsParameterizedTest {
    private WebDriver driver;
    private final By section;
    private final By ingredient;

    public ConstructorSectionsParameterizedTest(By section, By ingredient) {
        this.section = section;
        this.ingredient = ingredient;
    }

    @Parameterized.Parameters(name = "Тест {index}")
    public static Object[][] testSection() {
        return new Object[][]{
                {SECTION_BUNS, FLUORESCENT_BUN},
                {SECTION_SAUCES, SPICY_SAUCE},
                {SECTION_FILLINGS, IMMORTAL_SHELLFISH_MEAT}
        };
    }

    @Before
    public void start() {
        driver = WebDriverFactory.createWebDriver();
        driver.manage().window().maximize();
        driver.get(MAIN_PAGE);
    }

    @Test
    @DisplayName("Successful transition to the constructor sections")
    @Description("Successful transition to the constructor sections: Buns, Sauces, Fillings")
    public void checkingConstructorSections() {
        MainPage mainPage = new MainPage(driver);
        boolean isElementInViewport = mainPage.checkConstructorSection(section, ingredient);
        assertTrue(isElementInViewport);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}

