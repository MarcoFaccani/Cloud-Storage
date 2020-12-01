package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private SignUpPage signUpPage;
    private LogInPage logInPage;
    private HomePage homePage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        this.signUpPage = new SignUpPage(driver);
        this.logInPage = new LogInPage(driver);
        this.homePage = new HomePage(driver);

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
    public void uploadFile() {
        homePage.getUploadFileButton().click();
    }


}
