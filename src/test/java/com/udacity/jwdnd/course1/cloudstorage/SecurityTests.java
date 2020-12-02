package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private JavascriptExecutor jsExecutor;

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
		jsExecutor = (JavascriptExecutor) driver;

		this.signUpPage = new SignUpPage(driver, jsExecutor);
		this.logInPage = new LogInPage(driver, jsExecutor);
		this.homePage = new HomePage(driver, jsExecutor);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void getHomePageUnauthorized() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signUpNewUser_success() {
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage.fillInSignUpForm("Mario", "Rossi", "mariorossiii", "secretPassworddd");
		Assertions.assertTrue(signUpPage.getAlertSuccess().isDisplayed());
	}

	@Test
	public void signUpNewUser_usernameAlreadyTaken() {
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage.fillInSignUpForm("Jack", "Aubrey", "jackaubrey", "obrian");
		signUpPage.fillInSignUpForm("Jack", "Aubrey", "jackaubrey", "obrian");
		Assertions.assertTrue(signUpPage.getAlertError().isDisplayed());
	}

	@Test
	public void loginPositive() {
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage.fillInSignUpForm("Dr", "Maturin", "drmaturin", "doc");
		signUpPage.redirectToLoginAfterSignUp();
		Assertions.assertEquals("Login", driver.getTitle());

		logInPage.fillLoginForm("mariorossi", "secretPassword");
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void loginNegative() {
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage.fillInSignUpForm("Shan", "taram", "shantaram", "bestbook");
		signUpPage.redirectToLoginAfterSignUp();
		Assertions.assertEquals("Login", driver.getTitle());

		logInPage.fillLoginForm("Jack", "Aubrey");
		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertTrue(logInPage.getErrorAlert().isDisplayed());
	}

	@Test
	public void logout() {
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage.fillInSignUpForm("Mario", "Rossi", "mariorossi", "secretPassword");
		driver.get(signUpPage.getSuccessLoginLink().getAttribute("href"));
		logInPage.fillLoginForm("mariorossi", "secretPassword");
		homePage.logOut();
		Assertions.assertEquals("Login", driver.getTitle());
	}

}
