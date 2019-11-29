package com.pickyourTrip.stepdefinitions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.pickyourTrip.utility.JsonUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefinitions {
	private WebDriver driver = null;
	Properties prop;
	JsonUtility jsonUtility = new JsonUtility();

	public Stepdefinitions() {
		try {
			FileInputStream fis = new FileInputStream(
					new File("F:\\java\\com.pickyourTrip.sample\\src\\test\\resources\\config.properties"));

			prop = new Properties();
			prop.load(fis);
			// System.out.println(prop.getProperty("driverPath"));

		} catch (IOException e) {
			System.out.println("properties file not found");
		}
	}

	String parentWindow = "";
	@Given("^Open Browser in chrome$")
	public void open_Browser_in_chrome() {
		System.setProperty("webdriver.chrome.driver", prop.getProperty("driverPath"));
		driver = new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String string : windows) {
			if (!parentWindow.equals(string)) {
				driver.switchTo().window(string);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	@Then("^switch to new element '(.+)'")
	public void movetoElement(String elementName) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", jsonUtility.getWebElement(driver, elementName));

	}
@When("Switch to new tab")
public void windowhandling(){
	Set<String> windows = driver.getWindowHandles();
	for (String string : windows) {
		if (!parentWindow.equals(string)) {
			driver.switchTo().window(string);
			
		}
	}
}
	@When("^Click on '(.+)' button")
	public void clickOn(String element) {

		jsonUtility.getWebElement(driver, element).click();
	}

	@When("^Enter '(.+)' into '(.+)'$")
	public void enterthekeys(String value, String element) {
		jsonUtility.getWebElement(driver, element).sendKeys(value);
	}

	@When("^Clear the value '(.+)'$")
	public void clearText(String element) {
		int length = jsonUtility.getWebElement(driver, element).getText().length();
		// for (int i = 0; i < length; i++) {
		jsonUtility.getWebElement(driver, element).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	@Then("^Assert the title with entered Value as '(.+)'")
	public void Assetfunction(String value) {
		System.out.println(driver.getTitle());
		if (driver.getTitle().contains(value)) {
			System.out.println("PAss");
		} else {
			System.out.println("Fail");
		}
	}
	@Then("^Assert that '(.+)' is visible")
	public void Assetbutton(String value) {
		
		if (jsonUtility.getWebElement(driver,value).isDisplayed()) {
			System.out.println("PAss");
		} else {
			System.out.println("Fail");
		}
	}

	@Then("Wait for Element to present '(.+)'")
	public void elementToBePresent(String webElement) {
		new WebDriverWait(driver, 300)
				.until(ExpectedConditions.visibilityOf(jsonUtility.getWebElement(driver, webElement)));
	}

	@Then("Select place for '(.+)' from suggestion for '(.+)'")
	public void selectautosuggestedvalue(String value, String element) {
		List<WebElement> list = jsonUtility.getWebElements(driver, element);
		for (WebElement webElement : list) {
			System.out.println(webElement.getAttribute("innerText"));
			if (webElement.getAttribute("innerText").equals(value)) {
				webElement.click();

				break;
			}

		}
	}

	@Then("Wait for (.+) seconds")
	public void waitsec(String seconds) throws Exception {
		Thread.sleep(Integer.parseInt(seconds) * 1000);
	}

	@Then("^Select '(.+)' for '(.+)' with '(.+)' for month$")
	public void selectradioButton(String value, String element, String elementvalue) {
		List<WebElement> list = jsonUtility.getWebElements(driver, element);
		List<WebElement> list1 = jsonUtility.getWebElements(driver, elementvalue);
		System.out.println("list" + list.size() + "list1" + list1.size());
		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i).getAttribute("value") +
			// list1.get(i).getAttribute("value"));
			if (list1.get(i).getAttribute("value").equals(value)) {

				list.get(i).click();
				break;
			}
		}

	}

	@Then("^Select number of days as '(.+)' for '(.+)' with '(.+)'$")
	public void selectradioButtondays(String value, String element, String elementvalue) {
		List<WebElement> list = jsonUtility.getWebElements(driver, element);
		List<WebElement> list1 = jsonUtility.getWebElements(driver, elementvalue);
		System.out.println("list" + list.size() + "list1" + list1.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getAttribute("innerText") + list1.get(i).getAttribute("innerText"));
			if (list1.get(i).getAttribute("innerText").equals(value)) {
				// ((JavascriptExecutor)driver).executeScript("return
				// getComputedStyle(arguments[0],
				// ':before').click();",list.get(i));
				list.get(i).click();
				break;
			}
		}

	}

	@Then("^Select like to visit '(.+)' for '(.+)' with '(.+)'$")
	public void select_checkboxes(String value, String element, String elementvalue) {
		String[] valuesplit = value.split(",");
		List<WebElement> list = jsonUtility.getWebElements(driver, element);
		List<WebElement> list1 = jsonUtility.getWebElements(driver, elementvalue);
		for (String string : valuesplit) {
			// System.out.println(string);//System.out.println("list"+list.size()+"list1"+list1.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.println("String :" + string + ":" + list1.get(i).getAttribute("innerText"));
				if (string.equals(list1.get(i).getAttribute("innerText"))) {
					System.out.println("cam fr " + list1.get(i).getAttribute("innerText"));
					// ((JavascriptExecutor)driver).executeScript("return
					// getComputedStyle(arguments[0],':before').click();",list.get(i));
					list.get(i).click();
					break;
				}
			}

		}

	}

	@When("Try to handle notification popup")
	public void popup() {
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
		}
	}


 
}
