package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.stellarburgers.constants.UrlConstants.LOGIN_PAGE;

public class RegistrationForm {
    private final WebDriver driver;
    private final By fieldName = By.xpath("//input[@name='name']");
    private final By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By fieldPassword = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Зарегистрироваться']");
    private final By passwordError = By.className("input__error");
    private final By buttonLoginRegistrationForm = By.xpath("//a[@href='/login']");

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill in the name field")
    public void setName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Fill in the email field")
    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Fill in the password field")
    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click the register button")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Click the login button")
    public void clickLoginButton() {
        driver.findElement(buttonLoginRegistrationForm).click();
    }

    @Step("Check password error display and get text")
    public String checkVisibilityOfPasswordError() {
        WebElement element = driver.findElement(passwordError);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    @Step("Waiting for login page to load")
    public void waitLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE));
    }

    @Step("User registration with filling in all fields")
    public void userRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnRegisterButton();
    }
}
