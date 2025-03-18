package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.stellarburgers.constants.UrlConstants.LOGIN_PAGE;
import static ru.stellarburgers.constants.UrlConstants.MAIN_PAGE;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By ordersHistoryButton = By.xpath("//a[@href='/account/order-history']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check history button display and get text")
    public String checkVisibilityOfHistoryButton() {
        WebElement element = driver.findElement(ordersHistoryButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    @Step("Click on button logo Stellar Burgers and open constructor")
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(MAIN_PAGE));
    }

    @Step("Click on constructor button and open constructor")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(MAIN_PAGE));
    }

    @Step("Click on logout button")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE));
    }
}
