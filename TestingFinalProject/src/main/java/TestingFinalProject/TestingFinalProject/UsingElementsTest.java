package TestingFinalProject.TestingFinalProject;

import java.io.BufferedReader;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsingElementsTest {

	static WebDriver driver;
	static WebDriverWait wait;
	BufferedReader br;
	static long startTime = System.currentTimeMillis();
	File f;
	static boolean testElement;

	public static void elementsTest() {
		// Load Homepage
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");

		driver = new ChromeDriver();
		System.out.println("Opening Home Page...");

		{

			// clicking already have an account
			driver.get("http://way2automation.com/way2auto_jquery/selectable.php");
			WebDriverWait wait = new WebDriverWait(driver, 15);
			WebElement gotAccount = driver.findElement(By.linkText("Signin"));

			gotAccount = driver.findElement(By.linkText("Signin"));
			gotAccount.click();

			// logging in

			System.out.println("Logging In...");
			WebDriverWait wait2 = new WebDriverWait(driver, 15);
			// wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"load_form\"]/div/div[1]/p"),
			// "Dont Have an Account? | Signup"));

			String user = "x";
			String passwd = "x";

			WebElement userBox = driver.findElement(By.name("Username"));
			WebElement passwdBox = driver.findElement(By.name("Password"));
			WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"load_form\"]/div/div[2]/input"));

			userBox.sendKeys(user);
			passwdBox.sendKeys(passwd);
			saveBtn.click();

			driver.get("http://way2automation.com/way2auto_jquery/selectable.php");

			userBox = driver.findElement(By.name("Username"));
			passwdBox = driver.findElement(By.name("Password"));
			saveBtn = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[5]/button"));

			userBox.sendKeys(user);
			passwdBox.sendKeys(passwd);
			saveBtn.click();

			WebDriverWait wait5 = new WebDriverWait(driver, 5);
			boolean testElement = false;

			System.out.println("Logging In...");
			testElement = wait.until(ExpectedConditions
					.textToBePresentInElementLocated(By.xpath("//*[@id=\"header-bottom-right\"]/form/a"), "logout"));

			if (testElement) {

				System.out.println("You have logged in succesfully");
			}

			else
				System.out.println("Failed to log in");

			// click selectable box
			System.out.println("Navigating to selectable page...");

			WebElement selectableBtn = driver.findElement(By.name("images/selectable.jpg"));

			selectableBtn = driver.findElement(By.name("images/selectable.jpg"));

			selectableBtn.click();

			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			boolean testElement1 = false;
			try {
				System.out.println("Loading Page...");
				testElement1 = wait1.until(ExpectedConditions.textToBePresentInElementLocated(
						By.xpath("//*[@id=\"wrapper\"]/div"), "Allow elements to be moved using the mouse"));
			} catch (Exception e) {
				e.printStackTrace();
				testElement1 = false;
			}

			if (testElement1) {

				System.out.println("You have succesfully loaded the page");
			}

			else
				System.out.println("Page load has failed");

			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;

			System.out.println("This took: " + (totalTime / 1000) + " Seconds");
			System.out.println("Exiting...");
			driver.close();
			System.exit(0);
		}
	}
}
