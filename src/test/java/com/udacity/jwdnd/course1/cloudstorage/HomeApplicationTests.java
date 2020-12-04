package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    private SignUpPage signUpPage;
    private LogInPage logInPage;
    private HomePage homePage;
    private ResultPage resultPage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;

        this.signUpPage = new SignUpPage(driver, jsExecutor);
        this.logInPage = new LogInPage(driver, jsExecutor);
        this.homePage = new HomePage(driver, jsExecutor);
        this.resultPage = new ResultPage(driver, jsExecutor);

        this.signUpAndLogin();
    }

    @AfterEach
    public void afterEach() throws InterruptedException {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void uploadNote() {
        homePage.addNote("title", "description");
        Assertions.assertEquals("Result", driver.getTitle());
    }

    @Test
    public void deleteNote() {
        homePage.addNote("title", "description");
        resultPage.redirectToHome();
        homePage.navigateToNoteTab();
        homePage.deleteNote();
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }

    @Test
    public void editNode() {
        homePage.addNote("title", "description");
        resultPage.redirectToHome();
        homePage.navigateToNoteTab();
        homePage.editNote("new title", "new description");
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }

    @Test
    public void uploadFile() throws Exception {
        try {
            homePage.uploadNewFile("./testFile.txt");
        } finally { Files.deleteIfExists(Paths.get("./testFile.txt")); }

        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }

    @Test
    public void deleteFile() throws Exception {
        try {
            homePage.uploadNewFile("./testFile2.txt");
        } finally {
            Files.deleteIfExists(Paths.get("./testFile2.txt"));
        }
        resultPage.redirectToHome();
        homePage.deleteFile();
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }

    @Test
    public void addNewCredential() {
        homePage.navigateToCredentialTab();
        homePage.addCredential("www.test.com", "jack", "secretPassword");
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }

    @Test
    public void deleteCredential() {
        homePage.navigateToCredentialTab();
        homePage.addCredential("www.test.com", "jack", "secretPassword");
        resultPage.redirectToHome();
        homePage.navigateToCredentialTab();
        homePage.deleteCredential();
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }

    @Test
    public void editCredential() {
        homePage.navigateToCredentialTab();
        homePage.addCredential("www.test.com", "jack", "secretPassword");
        resultPage.redirectToHome();
        homePage.navigateToCredentialTab();
        homePage.editCredential("www.new.com", "newUsername", "newPassword");
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertTrue(resultPage.getSuccessAlert().isDisplayed());
    }


    void signUpAndLogin() {
        String username = RandomStringUtils.randomAlphabetic(15);
        String password = RandomStringUtils.randomAlphabetic(15);
        driver.get("http://localhost:" + this.port + "/signup");
        signUpPage.fillInSignUpForm("Mario", "Rossi", username, password);
        logInPage.fillLoginForm(username, password);
    }


}
