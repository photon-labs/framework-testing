package com.photon.phresco.testcases;

import java.io.IOException;

import junit.framework.TestCase;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.photon.phresco.Screens.WelcomeScreen;
import com.photon.phresco.uiconstants.PhrescoHTML5widgUiConstants;

/**
 * This is the class to check all the products in the YUIWidget applicaion.
 * 
 * @author Phresco QA Team
 * 
 */
public class LoginTestCase extends TestCase {

	private PhrescoHTML5widgUiConstants phrscoEnviroments;
	private WelcomeScreen welcomeScreen;
	public String testMethodName;
	public String applicationURL;

	/**
	 * Loading the Widget Enviroments
	 */
	@Parameters(value = "browser")
	@BeforeTest
	public void setUp(String browser) throws Exception {
		this.phrscoEnviroments = new PhrescoHTML5widgUiConstants();
		launchingBrowser(browser);

	}

	/**
	 * This method will Launch the browser based on the @Parameters value
	 * 
	 * @param browser
	 * @throws Exception
	 */
	public void launchingBrowser(String browser) throws Exception {
		try {
			String selectedBrowser = browser;
			Reporter.log("Selected Browser to execute testcases--->>"
					+ selectedBrowser);
			String applicationContext = phrscoEnviroments.CONTEXT;
			applicationURL = phrscoEnviroments.PROTOCOL + "://"
					+ phrscoEnviroments.HOST + ":" + phrscoEnviroments.PORT
					+ "/";
			welcomeScreen = new WelcomeScreen("*" + selectedBrowser,
					applicationURL, applicationContext);
			Assert.assertNotNull(welcomeScreen);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Test(description = "Validates the Login functionality with Valid login credentials")
	public void testValidLogin() throws InterruptedException,
			IOException, Exception {

		try {

			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.checkValidLogin(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Validates the Login functionality with Invalid login credentials")
	public void testInvalidLogin() throws InterruptedException,
			IOException, Exception {

		try {

			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.checkInvalidLogin(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}

	}

	@Test(description = "Validates the Login functionality with empty login")
	public void testEmptyLogin() throws InterruptedException,
			IOException, Exception {

		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.checkEmptyLogin(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}


	/**
	 * This will clean all the objects after execution of all the testcases.
	 */
	@AfterTest
	public void tearDown() {
		welcomeScreen.closeBrowser();
	}

}