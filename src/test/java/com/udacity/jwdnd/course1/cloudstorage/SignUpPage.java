package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Data;
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

    @FindBy(className = "alert-success")
    private WebElement successDiv;

    @FindBy(id = "success-login-link")
    private WebElement successLoginLink;

    @FindBy(className = "alert-danger")
    private WebElement errorDiv;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    void fillInSignUpForm(String firstName, String lastName, String username, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        signUpButton.click();
    }
}
