package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;
    private final By buttonLoginPasswordRecoveryPage = By.xpath("//a[@href='/login']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Click on button login on the password recovery page")
    public void clickOnButtonLogin() {
        driver.findElement(buttonLoginPasswordRecoveryPage).click();
    }
}
