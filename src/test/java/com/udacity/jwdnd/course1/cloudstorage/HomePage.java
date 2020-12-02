package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
public class HomePage {

    @Autowired
    private FileService fileService;

    @FindBy(id = "logout-btn")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement navFileTabButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNoteTabButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

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

    @FindBy(id = "upload-file-btn")
    private WebElement uploadFileBtn;

    @FindBy(id = "add-credential-btn")
    private WebElement addCredentialBtn;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(id = "credential-save-changes-btn")
    private WebElement credentialSaveChangesBtn;

    @FindBy(id = "credential-delete-btn")
    private WebElement credentialDeleteBtn;

    @FindBy(id = "credential-edit-btn")
    private WebElement credentialEditBtn;

    @FindBy(id = "fileUpload")
    private WebElement fileUploadInput;


    private JavascriptExecutor jsExecutor;

    public HomePage(WebDriver driver, JavascriptExecutor jsExecutor) {
        PageFactory.initElements(driver, this);
        this.jsExecutor = jsExecutor;
    }

    void logOut() { jsExecutor.executeScript("arguments[0].click();", logoutButton); }

    void navigateToNoteTab() {
        jsExecutor.executeScript("arguments[0].click();", navNoteTabButton);
    }

    void deleteNote() {
        jsExecutor.executeScript("arguments[0].click();", deleteNoteButton);
    }

    void navigateToCredentialTab() {
        jsExecutor.executeScript("arguments[0].click();", navCredentialsTab);
    }

    void deleteCredential() {
        jsExecutor.executeScript("arguments[0].click();", credentialDeleteBtn);
    }

    public void deleteFile() {
        jsExecutor.executeScript("arguments[0].click();", deleteFileButton);
    }

    void addNote(String title, String description) {
        navigateToNoteTab();
        jsExecutor.executeScript("arguments[0].click();", addNoteButton);
        jsExecutor.executeScript("arguments[0].value='" + title + "';", noteTitleInput);
        jsExecutor.executeScript("arguments[0].value='" + description + "';", noteDescriptionInput);
        jsExecutor.executeScript("arguments[0].click();", modalSaveNoteBtn);
    }

    void editNote(String title, String description) {
        jsExecutor.executeScript("arguments[0].click();", editNoteButton);
        jsExecutor.executeScript("arguments[0].value='" + title + "';", noteTitleInput);
        jsExecutor.executeScript("arguments[0].value='" + description + "';", noteDescriptionInput);
        jsExecutor.executeScript("arguments[0].click();", modalSaveNoteBtn);
    }

    void uploadNewFile(String fileName) throws Exception {
        Files.createFile(Paths.get(fileName));
        //String fileAbsolutePath = Paths.get("./testFile.txt").toAbsolutePath().toString();
        //jsExecutor.executeScript("arguments[0].value='" + fileAbsolutePath + "';", fileUploadInput);
        fileUploadInput.sendKeys(Paths.get(fileName).toAbsolutePath().toString());
        jsExecutor.executeScript("arguments[0].click();", uploadFileBtn);
    }

    void addCredential(String url, String username, String password) {
        jsExecutor.executeScript("arguments[0].click();", addCredentialBtn);
        jsExecutor.executeScript("arguments[0].value='" + url + "';", credentialUrlInput);
        jsExecutor.executeScript("arguments[0].value='" + username + "';", credentialUsernameInput);
        jsExecutor.executeScript("arguments[0].value='" + password + "';", credentialPasswordInput);
        jsExecutor.executeScript("arguments[0].click();", credentialSaveChangesBtn);
    }

    void editCredential(String url, String username, String password) {
        jsExecutor.executeScript("arguments[0].click();", credentialEditBtn);
        jsExecutor.executeScript("arguments[0].value='" + url + "';", credentialUrlInput);
        jsExecutor.executeScript("arguments[0].value='" + username + "';", credentialUsernameInput);
        jsExecutor.executeScript("arguments[0].value='" + password + "';", credentialPasswordInput);
        jsExecutor.executeScript("arguments[0].click();", credentialSaveChangesBtn);
    }

}
