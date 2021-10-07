package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	public String baseURL;
	public String firstName = "Aleyna";
	public String lastName = "Guner";
	public String username = "aleynag";
	public String password = "123";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(baseURL+ "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test void getSignupPage() {
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void getLoginPageWithUnauthorizeUserWhenAnotherEndpointRequest() {
		driver.get(baseURL+ "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void successfullSignupWithSignupSuccessMessage() throws InterruptedException {
		driver.get(baseURL+"/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void unsuccessfullSignupWithSignupErrorMessage() {
		driver.get(baseURL+"/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		driver.get(baseURL+"/signup");
		signupPage.signup(firstName, lastName, username, password);
		Assertions.assertTrue(signupPage.signupError());
	}

	@Test
	public void unsuccessfullLoginWithLoginErrorMessage() {
		driver.get(baseURL+"/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		driver.get(baseURL + "/login");
		username = "AleynaGuner";

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertTrue(loginPage.loginError());
	}

	@Test
	public void successfulLogoutWithLogoutMesssage() {
		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);
		homePage.logout();

		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getHomePageWithAuthorizedUser() {

		driver.get(baseURL + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		driver.get(baseURL + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");

		Assertions.assertEquals("Home", driver.getTitle());
	}

}
