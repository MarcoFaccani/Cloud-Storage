package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class SignUpPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(className = "btn-primary")
    private WebElement signUpButton;

    @FindBy(id = "alert-success")
    private WebElement alertSuccess;

    @FindBy(id = "success-login-link")
    private WebElement successLoginLink;

    @FindBy(id = "alert-error")
    private WebElement alertError;

    private JavascriptExecutor jsExecutor;

    public SignUpPage(WebDriver driver, JavascriptExecutor jsExecutor) {
        PageFactory.initElements(driver, this);
        this.jsExecutor = jsExecutor;
    }

    void redirectToLoginAfterSignUp() {
        jsExecutor.executeScript("arguments[0].click();", successLoginLink);
    }

    void fillInSignUpForm(String firstName, String lastName, String username, String password) {
        jsExecutor.executeScript("arguments[0].value='" + firstName + "';", firstNameField);
        jsExecutor.executeScript("arguments[0].value='" + lastName + "';", lastNameField);
        jsExecutor.executeScript("arguments[0].value='" + username + "';", usernameField);
        jsExecutor.executeScript("arguments[0].value='" + password + "';", passwordField);
        jsExecutor.executeScript("arguments[0].click();", signUpButton);
    }

}
