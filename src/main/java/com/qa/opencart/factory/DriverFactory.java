package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.contants.Appcontants;
import com.qa.opencart.ecpections.BrowserExpection;
import com.qa.opencart.ecpections.FramworkExpection;
import com.qa.opencart.errors.AppErrors;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * this is used to init the driver on the basis on given browser name
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();

		System.out.println("browser name is " + browserName);
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

			break;
		case "firefox":
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

			break;
		case "edge":
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			break;
		default:

			System.out.println("please pass the right browser name" + browserName);
			throw new BrowserExpection(AppErrors.BROWSER_NOT_FOUND);

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * get the local thread copy of the driver
	 * 
	 * @vivekSingh
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method used to init the properties from the .properties file
	 * 
	 * @return this returns properties (prop)
	 */

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn cleaninstall -Denv="qa"
		String envName = System.getProperty("env");

		System.out.println("eunning test suite on env -- + " + envName);
		if (envName == null) {
			System.out.println("env name is Null, hance it on QA enviroment........");
			try {
				ip = new FileInputStream(Appcontants.CONFIF_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {

			try {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(Appcontants.CONFIF_QA_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(Appcontants.CONFIF_dEV_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(Appcontants.CONFIF_UAT_FILE_PATH);
					break;
				case "prod":
					ip = new FileInputStream(Appcontants.CONFIF_FILE_PATH);
					break;
				default:

					System.out.println("please pass the right env name :" + envName);
					throw new FramworkExpection("WRONG ENV PASSED");

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * Take Screenshot
	 */

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); /// temp location

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}

}
