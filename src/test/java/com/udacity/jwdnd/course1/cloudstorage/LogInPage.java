package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class LogInPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "error-msg")
    private WebElement errorAlert;

    @FindBy(id = "submit")
    private WebElement submitButton;

    private JavascriptExecutor jsExecutor;

    public LogInPage(WebDriver driver, JavascriptExecutor jsExecutor) {
        PageFactory.initElements(driver, this);
        this.jsExecutor = jsExecutor;
    }

    void fillLoginForm(String username, String password) {
        jsExecutor.executeScript("arguments[0].value='" + username + "';", usernameField);
        jsExecutor.executeScript("arguments[0].value='" + password + "';", passwordField);
        jsExecutor.executeScript("arguments[0].click();", submitButton);
    }


}
