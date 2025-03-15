package pageObject;

import constants.UrlConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginForm {
    private final WebDriver driver;
    private final By loginButton = By.xpath(".//form/button[text()='Войти']");
    private final By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By fieldPassword = By.xpath("//input[@name='Пароль']");

    public LoginForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Check login button display and return text")
    public String checkVisibilityOfLoginButton() {
        WebElement element = driver.findElement(loginButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    @Step("Fill in the email field")
    public void setEmailLogin(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Fill in the password field")
    public void setPasswordLogin(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Click the login button")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(UrlConstants.MAIN_PAGE));
    }

    @Step("User login with filling in all fields")
    public void userLogin(String email, String password) {
        checkVisibilityOfLoginButton();
        setEmailLogin(email);
        setPasswordLogin(password);
        clickOnLoginButton();
    }
}
