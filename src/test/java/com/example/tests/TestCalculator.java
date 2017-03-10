package com.example.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCalculator {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static String OS = System.getProperty("os.name").toLowerCase();

  @BeforeMethod
public void setUp() throws Exception {
	if (OS.indexOf("win") >= 0)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver_win32-2.27\\chromedriver.exe");
	}
	else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 )
	{
		System.setProperty("webdriver.chrome.driver", "/tools/chromedriver/chromedriver");
	}
    driver = new ChromeDriver();
    baseUrl = "http://citius-app.westus2.cloudapp.azure.com/CalculatorApplication/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTt() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.name("num1")).clear();
    driver.findElement(By.name("num1")).sendKeys("20");
    driver.findElement(By.name("num2")).clear();
    driver.findElement(By.name("num2")).sendKeys("30");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    //AssertJUnit.assertEquals("Addition of two numbers: 50", driver.findElement(By.cssSelector("h2..firepath-matching-node")).getText());
  }

  @AfterMethod
public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
