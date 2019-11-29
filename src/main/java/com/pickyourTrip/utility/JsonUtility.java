package com.pickyourTrip.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonUtility {
	public By getLocator(WebDriver driver,String elementName){
		By locator=null;
		try {
			//	System.out.println(System.getProperty("user.dir")+"/src/test/resources/elements/elements.json");
				DocumentContext documentContext=JsonPath.parse(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\elements\\elements.json"));
				ArrayList<String> name=new ArrayList<String>();
				name=documentContext.read("$..name");
				String value = null,findBy = null;
				for (int i = 0; i < name.size(); i++) {
					if (name.get(i).equals(elementName)) {
						findBy=documentContext.read("$.["+i+"].lookupdetails.findBy");
						value=documentContext.read("$.["+i+"].lookupdetails.value");
					}
				}
				
				if (findBy.equalsIgnoreCase("xpath")) {
					locator=By.xpath(value);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return locator;
	}
public WebElement getWebElement(WebDriver driver,String elementName){
	WebElement webElement=null;
	try {
	//	System.out.println(System.getProperty("user.dir")+"/src/test/resources/elements/elements.json");
		DocumentContext documentContext=JsonPath.parse(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\elements\\elements.json"));
		ArrayList<String> name=new ArrayList<String>();
		name=documentContext.read("$..name");
		String value = null,findBy = null;
		for (int i = 0; i < name.size(); i++) {
			if (name.get(i).equals(elementName)) {
				findBy=documentContext.read("$.["+i+"].lookupdetails.findBy");
				value=documentContext.read("$.["+i+"].lookupdetails.value");
			}
		}
		
		if (findBy.equalsIgnoreCase("xpath")) {
			webElement=driver.findElement(By.xpath(value));
		}
		else if (findBy.equalsIgnoreCase("id")) {
			webElement=driver.findElement(By.id(value));
		}
		else if (findBy.equalsIgnoreCase("name")) {
			webElement=driver.findElement(By.name(value));
		}
		else if (findBy.equalsIgnoreCase("classname")) {
			webElement=driver.findElement(By.className(value));
		}
		else if (findBy.equalsIgnoreCase("linktext")) {
			webElement=driver.findElement(By.linkText(value));
		}else if (findBy.equalsIgnoreCase("tagName")) {
			webElement=driver.findElement(By.tagName(value));
		}
		else if (findBy.equalsIgnoreCase("cssselector")) {
			webElement=driver.findElement(By.cssSelector(value));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return webElement;
}
public List<WebElement> getWebElements(WebDriver driver,String elementName){
	List<WebElement> webElements=null;
	try {
		//System.out.println(System.getProperty("user.dir")+"/src/test/resources/elements/elements.json");
		DocumentContext documentContext=JsonPath.parse(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\elements\\elements.json"));
		ArrayList<String> name=new ArrayList<String>();
		name=documentContext.read("$..name");
		String value = null,findBy = null;
		for (int i = 0; i < name.size(); i++) {
			if (name.get(i).equals(elementName)) {
				
				findBy=documentContext.read("$.["+i+"].lookupdetails.findBy");System.out.println(findBy);
				value=documentContext.read("$.["+i+"].lookupdetails.value");System.out.println(value);
			}
		}
		
		if (findBy.equalsIgnoreCase("xpath")) {
			webElements=driver.findElements(By.xpath(value));
		}
		else if (findBy.equalsIgnoreCase("id")) {
			webElements=driver.findElements(By.id(value));
		}
		else if (findBy.equalsIgnoreCase("name")) {
			webElements=driver.findElements(By.name(value));
		}
		else if (findBy.equalsIgnoreCase("classname")) {
			webElements=driver.findElements(By.className(value));
		}
		else if (findBy.equalsIgnoreCase("linktext")) {
			webElements=driver.findElements(By.linkText(value));
		}else if (findBy.equalsIgnoreCase("tagName")) {
			webElements=driver.findElements(By.tagName(value));
		}
		else if (findBy.equalsIgnoreCase("cssselector")) {
			webElements=driver.findElements(By.cssSelector(value));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return webElements;
}
}
