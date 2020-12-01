package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class HomePage {

    @FindBy(id = "logout-btn")
    private WebElement logoutButton;

    @FindBy(id = "upload-file-btn")
    private WebElement uploadFileButton;

    @FindBy(id = "view-file-btn")
    private WebElement viewFileButton;

    @FindBy(id = "delete-file-btn")
    private WebElement deleteFileButton;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    void logout() {
        logoutButton.click();
    }

}
