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
 * This is the class to check the all the products in the YUIWidget applicaion.
 * 
 * @author Phresco - QA
 * 
 */
public class ProductsCheckOutTestCase extends TestCase {

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

	@Test(description = "Select and checksout Accessories products")
	public void testAccessoriesProducts() throws InterruptedException,
			IOException, Exception {

		try {

			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.checkAccessoriesProducts(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Select and checksout Accessories products")
	public void testAudioDevicesProducts() throws InterruptedException,
			IOException, Exception {

		try {

			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.checkAudioDevicesProducts(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}

	}

	@Test(description = "Select and checksout Accessories products")
	public void testCameraAddcartProducts() throws InterruptedException,
			IOException, Exception {

		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.Cameras(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Select and checksout Accessories products")
	public void testComputersAddcartProducts() throws InterruptedException,
			IOException, Exception {

		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.Computers(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Select and checksout Accessories products")
	public void testMobilesPhonesAddcartProducts() throws InterruptedException,
			IOException, Exception {

		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.MobilePhones(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Checks the  different MoviesMusicAddcart products")
	public void testMoviesMusicAddcartProducts() throws InterruptedException,
			IOException, Exception {
		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.MoviesnMusic(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	@Test(description = "Checks the  different MP3PlayersAddcart products")
	public void testMP3PlayersAddcartProducts() throws InterruptedException,
			IOException, Exception {
		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.MP3Players(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Checks the  different TabletsAddcart products")
	public void testTabletsAddcartProducts() throws InterruptedException,
			IOException, Exception {
		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.Tablets(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Checks the  different TelevisionAddcart products")
	public void testTelevisionAddcartProducts() throws InterruptedException,
			IOException, Exception {
		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.Television(testMethodName);
		} catch (Exception t) {
			t.printStackTrace();

		}
	}

	@Test(description = "Checks the  different VideoGamesAddcart products")
	public void testVideoGamesAddcartProducts() throws InterruptedException,
			IOException, Exception {
		try {
			testMethodName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			assertNotNull("TestMethodname should not be null", testMethodName);
			welcomeScreen.VideoGames(testMethodName);
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