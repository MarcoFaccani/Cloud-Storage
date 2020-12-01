package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class HomePage {

    @FindBy(id = "logout-btn")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement navFileTabButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNoteTabButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTabNote;

    @FindBy(id = "upload-file-btn")
    private WebElement uploadFileButton;

    @FindBy(id = "view-file-btn")
    private WebElement viewFileButton;

    @FindBy(id = "delete-file-btn")
    private WebElement deleteFileButton;

    @FindBy(id = "add-note-btn")
    private WebElement addNoteButton;

    @FindBy(id = "add-note-btn")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note-btn")
    private WebElement deleteNoteButton;

    @FindBy(name = "noteTitle")
    private WebElement noteTitleInput;

    @FindBy(id = "save-note-btn")
    private WebElement modalSaveNoteBtn;

    @FindBy(name = "noteDescription")
    private WebElement noteDescriptionInput;

    private JavascriptExecutor jsExecutor;

    public HomePage(WebDriver driver, JavascriptExecutor jsExecutor) {
        PageFactory.initElements(driver, this);
        this.jsExecutor = jsExecutor;
    }

    void navigateToNoteTab() {
        jsExecutor.executeScript("arguments[0].click();", navNoteTabButton);
    }

    void deleteNote() {
        jsExecutor.executeScript("arguments[0].click();", deleteNoteButton);
    }

    void editNote(String title, String description) {
        jsExecutor.executeScript("arguments[0].click();", editNoteButton);
        jsExecutor.executeScript("arguments[0].value='" + title + "';", noteTitleInput);
        jsExecutor.executeScript("arguments[0].value='" + description + "';", noteDescriptionInput);
        jsExecutor.executeScript("arguments[0].click();", modalSaveNoteBtn);
    }

    void addNote(String title, String description) {
        navigateToNoteTab();
        jsExecutor.executeScript("arguments[0].click();", addNoteButton);
        jsExecutor.executeScript("arguments[0].value='" + title + "';", noteTitleInput);
        jsExecutor.executeScript("arguments[0].value='" + description + "';", noteDescriptionInput);
        jsExecutor.executeScript("arguments[0].click();", modalSaveNoteBtn);
    }


}
