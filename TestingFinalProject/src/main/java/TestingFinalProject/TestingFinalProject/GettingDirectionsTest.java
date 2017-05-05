package TestingFinalProject.TestingFinalProject;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GettingDirectionsTest {

	public static WebDriver fd1 = null;
	static WebDriver driver;
	static WebDriverWait wait;
	static ExtentReports report;
	static ExtentTest test;
	static long startTime = System.currentTimeMillis();

	public static void directions() {

		report = new ExtentReports("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\automationreport.html", true);

		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");

			driver = new ChromeDriver();
			System.out.println("Opening Google Maps...");
			driver.get("https://www.google.co.uk/maps");
			System.out.println("finding your location...");

			// finding location

			String location = "M50 3YW to M50 2EQ";

			WebElement search = driver.findElement(By.name("q"));

			search = driver.findElement(By.name("q"));
			System.out.println("Searching...");
			search.sendKeys(location);
			TimeUnit.SECONDS.sleep(3);
			search.sendKeys(Keys.RETURN);

			System.out.println("Route to the Dockyard found!");
		}

		catch (Exception e) {

			System.out.println("Unable to find a route");

		}

		finally {

			// where to create the report file
			report = new ExtentReports("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\automationreport.html", true);
			// init/start the test
			test = report.startTest("Verify application Title");
			
			// add a note to the test
			test.log(LogStatus.INFO, "Directions Found");
			String title = driver.getTitle();
			System.out.println(title);
			if (title.equals("M50 3YW to M50 2EQ - Google Maps")) {
				// report the test as a pass
				test.log(LogStatus.PASS, "verify Title of the page");
			} else {
				test.log(LogStatus.FAIL, "verify Title of the page");
			}
			
		}

	}

	public static void print(){

		try {
			TimeUnit.SECONDS.sleep(10);
			System.out.println("Printing...");

			String selectAll = Keys.chord(Keys.CONTROL, "p");
			driver.findElement(By.tagName("html")).sendKeys(selectAll);

		
		} catch (Exception e) {

			System.out.println("Unable to print");

		} finally {

			// Fail scenario
			// make this fail to see the screenshot output

			test = report.startTest("Verify correct directions have been found");
			WebElement src = driver.findElement(By.xpath("//*[@id=\"omnibox-directions\"]/div/div[1]/button"));
			if (src != null) {
				test.log(LogStatus.PASS, "verify logo");
			} else {
				test.log(LogStatus.FAIL, "verify logo");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile,
							new File("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\img.jpg"));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				String image = test.addScreenCapture("C:\\Users\\Administrator\\Desktop\\FinalProjectReports\\img.jpg");
				test.log(LogStatus.FAIL, "verify logo of the application", image);
				
		
				long endTime = System.currentTimeMillis();
				
				long totalTime = endTime - startTime;

				System.out.println("This took: " + (totalTime / 1000) + " Seconds");
				System.out.println("Exiting...");
				driver.close();
				
				report.endTest(test);
				report.flush();
				System.exit(0);
			}
		}
	}

}
