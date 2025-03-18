package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.stellarburgers.constants.UrlConstants.PROFILE_PAGE;

public class MainPage {
    private final WebDriver driver;
    private final By buttonPlaceAnOrder = By.className("button_button__33qZ0");
    private final By textAssembleTheBurger = By.xpath("//h1[text()='Соберите бургер']");
    private final By buttonLoginToAccount = By.className("button_button_type_primary__1O7Bx");
    private final By buttonPersonalAccount = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    private final By sectionFillings = By.xpath(".//span[text() = 'Начинки']/parent::div");
    private final By sectionBuns = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By sectionSauces = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By immortalShellfishMeat = By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']");
    private final By fluorescentBun = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    private final By spicySauce = By.xpath(".//img[@alt='Соус Spicy-X']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on button login to account")
    public void clickLoginToAccountButton() {
        driver.findElement(buttonLoginToAccount).click();
    }

    @Step("Click on button personal account")
    public void clickPersonalAccountButton() {
        driver.findElement(buttonPersonalAccount).click();
    }

    @Step("Click on section Buns")
    public void clickOnSectionBuns() {
        driver.findElement(sectionSauces).click();
        driver.findElement(sectionBuns).click();
    }

    @Step("Click on section Sauces")
    public void clickOnSectionSauces() {
        driver.findElement(sectionSauces).click();
    }

    @Step("Click on section Fillings")
    public void clickOnSectionFillings() {
        driver.findElement(sectionFillings).click();
    }

    @Step("Check element of section Buns")
    public WebElement checkVisibilityOfElementSectionBuns() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fluorescentBun));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    @Step("Check element of section Sauces")
    public WebElement checkVisibilityOfElementSectionSauces() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(spicySauce));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    @Step("Check element of section Fillings")
    public WebElement checkVisibilityOfElementSectionFillings() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(immortalShellfishMeat));
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

    @Step("Check constructor section Buns")
    public boolean checkConstructorSectionBuns() {
        clickOnSectionBuns();
        WebElement element = checkVisibilityOfElementSectionBuns();
        return checkElementInWindow(element);
    }

    @Step("Check constructor section Sauces")
    public boolean checkConstructorSectionSauces() {
        clickOnSectionSauces();
        WebElement element = checkVisibilityOfElementSectionSauces();
        return checkElementInWindow(element);
    }

    @Step("Check constructor section Fillings")
    public boolean checkConstructorSectionFillings() {
        clickOnSectionFillings();
        WebElement element = checkVisibilityOfElementSectionFillings();
        return checkElementInWindow(element);
    }

    @Step("Click on button personal account")
    public void clickOnButtonPersonalAccount() {
        driver.findElement(buttonPersonalAccount).click();
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
