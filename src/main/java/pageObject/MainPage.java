package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.UrlConstants.PROFILE_PAGE;

public class MainPage {
    private final WebDriver driver;
    private final By buttonPlaceAnOrder = By.className("button_button__33qZ0");
    private final By textAssembleTheBurger = By.xpath("//h1[text()='Соберите бургер']");

    public static final By BUTTON_LOGIN_TO_ACCOUNT = By.className("button_button_type_primary__1O7Bx");
    public static final By BUTTON_PERSONAL_ACCOUNT = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    public static final By LOGO_STELLAR_BURGERS = By.className("AppHeader_header__logo__2D0X2");
    public static final By CONSTRUCTOR_BUTTON = By.xpath("//p[text()='Конструктор']");
    public static final By SECTION_FILLINGS = By.xpath(".//span[text() = 'Начинки']/parent::div");
    public static final By SECTION_BUNS = By.xpath(".//span[text() = 'Булки']/parent::div");
    public static final By SECTION_SAUCES = By.xpath(".//span[text() = 'Соусы']/parent::div");
    public static final By IMMORTAL_SHELLFISH_MEAT = By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']");
    public static final By FLUORESCENT_BUN = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    public static final By SPICY_SAUCE = By.xpath(".//img[@alt='Соус Spicy-X']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on button login")
    public void clickOnButtonLogin(By button) {
        driver.findElement(button).click();
    }

    @Step("Click on section")
    public void clickOnSection(By section) {
        if (section.equals(SECTION_BUNS)) {
            driver.findElement(SECTION_SAUCES).click();
        }
        driver.findElement(section).click();
    }

    @Step("Check element in section")
    public WebElement checkVisibilityOfElement(By ingredient) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(ingredient));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    @Step("Check that the element is in the visible area of the browser window")
    public boolean checkElementInWindow(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(driver -> {
                    Rectangle rect = element.getRect();
                    Dimension windowSize = driver.manage().window().getSize();
                    boolean isVisible = rect.getX() >= 0
                            && rect.getY() >= 0
                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                    if (isVisible) {
                        System.out.println("Элемент видим");
                    } else {
                        System.out.println("Элемент не видим");
                    }
                    return isVisible;
                });
    }

    @Step("Check constructor section")
    public boolean checkConstructorSection(By section, By ingredient) {
        clickOnSection(section);
        WebElement element = checkVisibilityOfElement(ingredient);
        return checkElementInWindow(element);
    }

    @Step("Click on button personal account")
    public void clickOnButtonPersonalAccount() {
        driver.findElement(BUTTON_PERSONAL_ACCOUNT).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(PROFILE_PAGE));
    }

    @Step("Check button place an order display and get text")
    public String checkVisibilityOfButtonPlaceAnOrder() {
        WebElement element = driver.findElement(buttonPlaceAnOrder);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    @Step("Check text assemble the burger display and get text")
    public String checkVisibilityOfAssembleTheBurger() {
        WebElement element = driver.findElement(textAssembleTheBurger);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}
