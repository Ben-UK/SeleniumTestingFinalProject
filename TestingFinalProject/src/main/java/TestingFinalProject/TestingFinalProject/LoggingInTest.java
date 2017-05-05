package TestingFinalProject.TestingFinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoggingInTest {

	static WebDriver driver;
	static WebDriverWait wait;
	BufferedReader br;
	static long startTime = System.currentTimeMillis();
	static ExtentReports report;
	static ExtentTest test;
	File f;

	public static void login() {
		// Log In
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");

		driver = new ChromeDriver();
		System.out.println("Opening Reddit Home Page...");
		driver.get("https://www.reddit.com");
		System.out.println("Navigating to login page...");
		driver.get("https://www.reddit.com/login");

		String user = "cookiestest2017";
		String passwd = "cookiestest2017";

		WebElement userBox = driver.findElement(By.xpath("//*[@id=\"user_login\"]"));
		WebElement passwdBox = driver.findElement(By.xpath("//*[@id=\"passwd_login\"]"));
		WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[5]/button"));

		userBox.sendKeys(user);
		passwdBox.sendKeys(passwd);
		saveBtn.click();

		driver.get("https://www.reddit.com/login");

		userBox = driver.findElement(By.xpath("//*[@id=\"user_login\"]"));
		passwdBox = driver.findElement(By.xpath("//*[@id=\"passwd_login\"]"));
		saveBtn = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[5]/button"));

		userBox.sendKeys(user);
		passwdBox.sendKeys(passwd);
		saveBtn.click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		boolean testElement = false;
		try {
			System.out.println("Logging In...");
			testElement = wait.until(ExpectedConditions
					.textToBePresentInElementLocated(By.xpath("//*[@id=\"header-bottom-right\"]/form/a"), "logout"));
		} catch (Exception e) {
			e.printStackTrace();
			testElement = false;
		}

		finally {
			// where to create the report file
			report = new ExtentReports("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\automationreport2.html",
					true);
			// init/start the test
			test = report.startTest("Verify application Title");

			// add a note to the test
			test.log(LogStatus.INFO, "Logged into Reddit");
			String title = driver.getTitle();
			System.out.println(title);
			if (title.equals("cookiestest2017")) {
				// report the test as a pass
				test.log(LogStatus.PASS, "verify Title of the page");
			} else {
				test.log(LogStatus.FAIL, "verify Title of the page");
			}
		}

		if (testElement) {

			System.out.println("You have logged in succesfully");
		}

		else
			System.out.println("Failed to log in");

		// to account
		System.out.println("Opening Reddit Account Page...");
		WebElement accountButton = driver.findElement(By.xpath("//*[@id=\"header-bottom-right\"]/span[1]/a"));
		accountButton = driver.findElement(By.xpath("//*[@id=\"header-bottom-right\"]/span[1]/a"));
		accountButton.click();
		// LOG OUT
		System.out.println("Logging Out...");
		driver.get("https://www.reddit.com/r/popular/");

		WebElement logout = driver.findElement(By.xpath("//*[@id=\"header-bottom-right\"]/form/a"));

		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		boolean testElement3 = false;
		try {
			testElement3 = wait.until(ExpectedConditions
					.textToBePresentInElementLocated(By.xpath("//*[@id=\"header-bottom-right\"]/form/a"), "logout"));
			logout.click();

		} catch (Exception e) {
			e.printStackTrace();
			testElement3 = false;
		}

		finally {
			test = report.startTest("Verify you have logged into Reddit");
			WebElement src = driver.findElement(By.xpath("//*[@id=\"header-img\"]"));
			if (src != null) {
				test.log(LogStatus.PASS, "verify logo");
			} else {
				test.log(LogStatus.FAIL, "verify logo");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile,
							new File("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\img2.jpg"));
				} catch (IOException e) {

					e.printStackTrace();
				}
				String image = test
						.addScreenCapture("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\img2.jpg");
				test.log(LogStatus.FAIL, "verify logo of the application", image);
			}

			if (testElement3)
				System.out.println("You have logged out succesfully");
			else
				System.out.println("Failed to log out");

			driver.get("https://www.reddit.com");

			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;

			System.out.println("This took: " + (totalTime / 1000) + " Seconds");
			System.out.println("Exiting...");
			driver.close();

		}

	}
}
