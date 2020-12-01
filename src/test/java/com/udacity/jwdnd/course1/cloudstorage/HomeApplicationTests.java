package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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

        //Sign up & Login
        driver.get("http://localhost:" + this.port + "/signup");
        signUpPage.fillInSignUpForm("Mario", "Rossi", "mariorossi", "secretPassword");
        driver.get(signUpPage.getSuccessLoginLink().getAttribute("href"));
        logInPage.fillLoginForm("mariorossi", "secretPassword");
    }

    @AfterEach
    public void afterEach() {
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
    public void uploadFile() {
        //homePage.uploadNewFile();
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


}
