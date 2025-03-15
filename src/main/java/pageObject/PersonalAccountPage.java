package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.UrlConstants.LOGIN_PAGE;
import static constants.UrlConstants.MAIN_PAGE;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By ordersHistoryButton = By.xpath("//a[@href='/account/order-history']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

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

    @Step("Click on button and open constructor")
    public void openConstructor(By button) {
        driver.findElement(button).click();
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
