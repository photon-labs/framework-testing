package com.photon.phresco.Screens;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.photon.phresco.selenium.util.Constants;
import com.photon.phresco.selenium.util.ScreenActionFailedException;
import com.photon.phresco.selenium.util.ScreenException;
import com.photon.phresco.uiconstants.YUIWidgetConstants;
import com.thoughtworks.selenium.Selenium;

public class BaseScreen {

	public Selenium selenium;
	public WebDriver driver;
	private ChromeDriverService chromeService;
	private Log log = LogFactory.getLog("BaseScreen");
	private WebElement element;
	private By by;
	private YUIWidgetConstants yuiWidgetConstant;

	public BaseScreen() {

	}

	public BaseScreen(String selectedBrowser, String applicationURL,
			String applicationContext) throws AWTException, IOException,
			ScreenActionFailedException {
		initialize(selectedBrowser, applicationURL, applicationContext);
		this.yuiWidgetConstant = new YUIWidgetConstants();
	}

	public void initialize(String selectedBrowser, String applicationURL,
			String applicationContext)
			throws com.photon.phresco.selenium.util.ScreenActionFailedException,
			MalformedURLException {

		try {

			instantiateBrowser(selectedBrowser, applicationURL,
					applicationContext);
		} catch (ScreenException se) {
			se.printStackTrace();
		}

	}

	public void instantiateBrowser(String selectedBrowser,
			String applicationURL, String applicationContext)
			throws ScreenException, MalformedURLException {
		URL server = new URL("http://localhost:4444/wd/hub/");
		if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
			try {
				/*chromeService = new ChromeDriverService.Builder()
						.usingChromeDriverExecutable(
								new File(getChromeLocation()))
						.usingAnyFreePort().build();
				log.info("-------------***LAUNCHING GOOGLECHROME***--------------");
				chromeService.start();*/
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
				driver = new RemoteWebDriver(server, capabilities);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_IE)) {
			log.info("---------------***LAUNCHING INTERNET EXPLORE***-----------");
			driver = new InternetExplorerDriver();

		} else if (selectedBrowser.equalsIgnoreCase(Constants.BROWSER_FIREFOX)) {
			log.info("-------------***LAUNCHING FIREFOX***--------------");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
			driver = new RemoteWebDriver(server, capabilities);

		} else {
			throw new ScreenException(
					"------Only FireFox,InternetExplore and Chrome works-----------");
		}
		/**
		 *   These 3 steps common for all the browsers
		 */

		driver.get(applicationURL + applicationContext);
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	public By getTagByValue(String name) throws ScreenException {
		if (name != null) {
			this.by = By.tagName(name);

		} else {
			throw new ScreenException("Enter:---getClassNameByValue()-----");
		}
		return this.by;
	}

	/**
	 *   Once executed all the test methods this method will get executed
	 */
	public void closeBrowser() {
		log.info("-------------***BROWSER CLOSING***--------------");
		if (driver != null) {
			driver.close();
			//driver.quit();
			if (chromeService != null) {
				chromeService.stop();
			}
		}
	}

	public String getChromeLocation() {
		log.info("getChromeLocation:*****CHROME TARGET LOCATION FOUND***");
		String directory = System.getProperty("user.dir");
		String targetDirectory = getChromeFile();
		String location = directory + targetDirectory;
		return location;
	}

	public String getChromeFile() {
		if (System.getProperty("os.name").startsWith(Constants.WINDOWS_OS)) {
			log.info("*******WINDOWS MACHINE FOUND*************");
			// getChromeLocation("/chromedriver.exe");
			return Constants.WINDOWS_DIRECTORY + "/chromedriver.exe";
		} else if (System.getProperty("os.name").startsWith(Constants.LINUX_OS)) {
			log.info("*******LINUX MACHINE FOUND*************");
			return Constants.LINUX_DIRECTORY_64 + "/chromedriver";
		} else if (System.getProperty("os.name").startsWith(Constants.MAC_OS)) {
			log.info("*******MAC MACHINE FOUND*************");
			return Constants.MAC_DIRECTORY + "/chromedriver";
		} else {
			throw new NullPointerException("******PLATFORM NOT FOUND********");
		}

	}

	public void waitForElementPresent(String locator, String name)
			throws InterruptedException {
		log.info("Entering:*********waitForElementPresent()******");
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		log.info("Waiting:*************One second***********");
		wait.until(presenceOfElementLocated(by));

	}
	public boolean isTextPresent(String name) throws ScreenException{
//		String sourceCode=driver.getPageSource();
		System.out.println("-------textvalue"+name);
		WebElement ele=driver.findElement(getTagByValue("body"));
		String name1=ele.getText();
		String[] name2=name1.split(",");
		
		for(String name3:name2){
			name3.trim();			
			System.out.println("pagesource code---------->"+name3);
			if(name3.endsWith(name)){
				System.out.println("-------------BREAK-------------");
				break;
			}
			
		}
		return true;
	}

	Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		log.info("Entering:*********presenceOfElementLocated()******Start");
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				log.info("Entering:*********presenceOfElementLocated()******End");
				return driver.findElement(locator);

			}

		};

	}

	public void getXpathWebElement(String xpath) throws ScreenException {
		try {
			element = driver.findElement(By.xpath(xpath));
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public void getIdWebElement(String id) throws ScreenException {
		try {
			this.element = driver.findElement(By.id(id));
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public void click() throws ScreenException {

		try {
			element.click();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public void type(String text) throws ScreenException {
		try {
			clear();
			this.element.sendKeys(text);
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	public void clear() throws ScreenException {
		try {
			this.element.clear();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
	
	/********
	 Registration functionality ScreenActions
	 * @throws Exception 
	 ********/
	public void checkEmptyRegistration(String methodName) throws Exception {
		waitForElementPresent(yuiWidgetConstant.SIGNUPTAB, methodName);
		getXpathWebElement(yuiWidgetConstant.SIGNUPTAB);
		click();
		getXpathWebElement(yuiWidgetConstant.REGISTER_FIRSTNAME);
		click();
		type(yuiWidgetConstant.FIRSTNAME_VALUE);
		getXpathWebElement(yuiWidgetConstant.REGISTER_LASTNAME);
		click();
		type(yuiWidgetConstant.LASTNAME_VALUE);
		getXpathWebElement(yuiWidgetConstant.REGISTER_EMAIL);
		click();
		type(yuiWidgetConstant.EMAIL_VALUE); 
		getXpathWebElement(yuiWidgetConstant.REGISTER_PHONENUMBER);
		click();
		type(yuiWidgetConstant.PHONENUMBER_VALUE);
		getXpathWebElement(yuiWidgetConstant.SUBMIT);
		click();
		isTextPresent(yuiWidgetConstant.REGISTER_SUCCESSMSG);
	}

	public void checkInvalidRegistration(String methodName) throws Exception {
		waitForElementPresent(yuiWidgetConstant.SIGNUPTAB, methodName);
		getXpathWebElement(yuiWidgetConstant.SIGNUPTAB);
		click();
		getXpathWebElement(yuiWidgetConstant.REGISTER_FIRSTNAME);
		click();
		type(yuiWidgetConstant.FIRSTNAME_VALUE);
		getXpathWebElement(yuiWidgetConstant.REGISTER_LASTNAME);
		click();
		type(yuiWidgetConstant.LASTNAME_VALUE);
		getXpathWebElement(yuiWidgetConstant.REGISTER_EMAIL);
		click();
		type(yuiWidgetConstant.EMAIL_VALUE); 
		getXpathWebElement(yuiWidgetConstant.REGISTER_PHONENUMBER);
		click();
		type(yuiWidgetConstant.PHONENUMBER_VALUE);
		getXpathWebElement(yuiWidgetConstant.SUBMIT);
		click();
		isTextPresent(yuiWidgetConstant.REGISTER_SUCCESSMSG);
		
	}

	public void checkValidRegistration(String methodName) throws Exception {
		waitForElementPresent(yuiWidgetConstant.SIGNUPTAB, methodName);
		getXpathWebElement(yuiWidgetConstant.SIGNUPTAB);
		click();
		getXpathWebElement(yuiWidgetConstant.REGISTER_FIRSTNAME);
		click();
		type(yuiWidgetConstant.FIRSTNAME_VALUE);
		getXpathWebElement(yuiWidgetConstant.REGISTER_LASTNAME);
		click();
		type(yuiWidgetConstant.LASTNAME_VALUE);
		getXpathWebElement(yuiWidgetConstant.REGISTER_EMAIL);
		click();
		type(yuiWidgetConstant.EMAIL_VALUE); 
		getXpathWebElement(yuiWidgetConstant.REGISTER_PHONENUMBER);
		click();
		type(yuiWidgetConstant.PHONENUMBER_VALUE);
		getXpathWebElement(yuiWidgetConstant.SUBMIT);
		click();
		isTextPresent(yuiWidgetConstant.REGISTER_SUCCESSMSG);
		
		
		
	}
	/********
	 Login functionality ScreenActions
	 * @throws Exception 
	 ********/
	
	public void checkEmptyLogin(String methodName) throws Exception {
		waitForElementPresent(yuiWidgetConstant.LOGINTAB, methodName);
		getXpathWebElement(yuiWidgetConstant.LOGINTAB);
		click();
		getXpathWebElement(yuiWidgetConstant.LOGIN_EMAIL);
		click();
		getXpathWebElement(yuiWidgetConstant.LOGIN_PASSWORD);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.SUBMIT, methodName);
		getXpathWebElement(yuiWidgetConstant.SUBMIT);
		click();
		isTextPresent(yuiWidgetConstant.EMPTY_EMAIL_ALERT);
	}
	public void checkInvalidLogin(String methodName) throws Exception {
		waitForElementPresent(yuiWidgetConstant.LOGINTAB, methodName);
		getXpathWebElement(yuiWidgetConstant.LOGINTAB);
		click();
		getXpathWebElement(yuiWidgetConstant.LOGIN_EMAIL);
		click();
		type(yuiWidgetConstant.INVALID_EMAILVALUE);
		getXpathWebElement(yuiWidgetConstant.LOGIN_PASSWORD);
		click();
		type(yuiWidgetConstant.INVALID_PASSWORDVALUE);
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.SUBMIT, methodName);
		getXpathWebElement(yuiWidgetConstant.SUBMIT);
		click();
		isTextPresent(yuiWidgetConstant.LOGIN_FAILUREMSG);
	}

	public void checkValidLogin(String methodName) throws Exception {
		waitForElementPresent(yuiWidgetConstant.LOGINTAB, methodName);
		getXpathWebElement(yuiWidgetConstant.LOGINTAB);
		click();
		getXpathWebElement(yuiWidgetConstant.LOGIN_EMAIL);
		click();
		type(yuiWidgetConstant.EMAIL_VALUE);
		getXpathWebElement(yuiWidgetConstant.LOGIN_PASSWORD);
		click();
		type(yuiWidgetConstant.PASSWORD_VALUE);
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.SUBMIT, methodName);
		getXpathWebElement(yuiWidgetConstant.SUBMIT);
		click();
		isTextPresent(yuiWidgetConstant.LOGIN_SUCCESSMSG);
		
	}
	
	/********
	 Products CheckOut functionality ScreenActions
	 ********/

	public void billingInfo(String methodName) throws Exception {
        Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.EMAIL, methodName);
		getXpathWebElement(yuiWidgetConstant.EMAIL);
		// System.out.println("----element ---------->1" + element);
		type(yuiWidgetConstant.EMAIL_VALUE);
		getIdWebElement(yuiWidgetConstant.FIRSTNAME);
		// System.out.println("----element-------------> 2" + element);
		type(yuiWidgetConstant.FIRSTNAME_VALUE);
		getIdWebElement(yuiWidgetConstant.LASTNAME);
		type(yuiWidgetConstant.LASTNAME_VALUE);
		getIdWebElement(yuiWidgetConstant.COMPANY);
		type(yuiWidgetConstant.COMPANY);
		getIdWebElement(yuiWidgetConstant.ADDRESS1);
		type(yuiWidgetConstant.ADDRESS1_VALUE);
		getIdWebElement(yuiWidgetConstant.ADDRESS2);
		type(yuiWidgetConstant.ADDRESS2_VALUE);
		getIdWebElement(yuiWidgetConstant.CITY);
		type(yuiWidgetConstant.CITY_VALUE);
		getIdWebElement(yuiWidgetConstant.STATE);
		type(yuiWidgetConstant.STATE_VALUE);
		getIdWebElement(yuiWidgetConstant.POSTALCODE);
		type(yuiWidgetConstant.POSTALCODE_VALUE);
		getIdWebElement(yuiWidgetConstant.PHONENUMBER);
		type(yuiWidgetConstant.PHONENUMBER_VALUE);
		getIdWebElement(yuiWidgetConstant.CARDNUMBER);
		type(yuiWidgetConstant.CARDNUMBER_VALUE);
		getIdWebElement(yuiWidgetConstant.SECURITYNUMBER);
		type(yuiWidgetConstant.SECURITYNUMBER_VALUE);
		getIdWebElement(yuiWidgetConstant.NAMEONCARD);
		type(yuiWidgetConstant.NAMEONCARD_VALUE);
		waitForElementPresent(yuiWidgetConstant.REVIEWORDER, methodName);
		getXpathWebElement(yuiWidgetConstant.REVIEWORDER);
		click();
		waitForElementPresent(yuiWidgetConstant.SUBMITORDER, methodName);
		getXpathWebElement(yuiWidgetConstant.SUBMITORDER);
		click();

	}

	public void checkAccessoriesProducts(String methodName) throws Exception {
		try {
			Thread.sleep(3000);
			waitForElementPresent(yuiWidgetConstant.MORE, methodName);
			getXpathWebElement(yuiWidgetConstant.MORE);
			click();
			waitForElementPresent(yuiWidgetConstant.ACCESSORIES, methodName);
			getXpathWebElement(yuiWidgetConstant.ACCESSORIES);
			click();
			waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
			getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
			click();
			Thread.sleep(3000);
			waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
			getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
			click();
			waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
			getXpathWebElement(yuiWidgetConstant.CHECKOUT);
			click();
			// driver.findElement(By.xpath(phrsc.EMAIL)).sendKeys(phrsc.EMAIL_VALUE);
			// System.out.println("----element ---------->1" + element);
			billingInfo(methodName);
		} catch (Throwable th) {
			th.printStackTrace();
		}

	}

	public void checkAudioDevicesProducts(String methodName) throws Exception {

		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.AUDIO_DEVICES, methodName);		
		getXpathWebElement(yuiWidgetConstant.AUDIO_DEVICES);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);
	}

	public void Cameras(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.CAMERAS, methodName);
		getXpathWebElement(yuiWidgetConstant.CAMERAS);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);
	}

	public void Computers(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.COMPUTERS, methodName);
		getXpathWebElement(yuiWidgetConstant.COMPUTERS);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		element.click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		element.click();
		billingInfo(methodName);

	}

	public void MobilePhones(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.MOBILE, methodName);
		getXpathWebElement(yuiWidgetConstant.MOBILE);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);

	}

	public void MoviesnMusic(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.MOVIESnMUSIC, methodName);
		getXpathWebElement(yuiWidgetConstant.MOVIESnMUSIC);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);

	}

	public void MP3Players(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.MP3PLAYERS, methodName);
		getXpathWebElement(yuiWidgetConstant.MP3PLAYERS);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		element.click();
		billingInfo(methodName);
	}

	public void Tablets(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.TABLETS, methodName);
		getXpathWebElement(yuiWidgetConstant.TABLETS);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);

	}

	public void Television(String methodName) throws Exception {

		log.info("Entering :***************Television()***********Start:");
		waitForElementPresent(yuiWidgetConstant.TELEVISION, methodName);
		getXpathWebElement(yuiWidgetConstant.TELEVISION);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);
	}

	public void VideoGames(String methodName) throws Exception {

		waitForElementPresent(yuiWidgetConstant.VIDEOGAMES, methodName);
		getXpathWebElement(yuiWidgetConstant.VIDEOGAMES);
		click();
		waitForElementPresent(yuiWidgetConstant.PROD1_DETAILS, methodName);
		getXpathWebElement(yuiWidgetConstant.PROD1_DETAILS);
		click();
		Thread.sleep(3000);
		waitForElementPresent(yuiWidgetConstant.DET_ADDTOCART, methodName);
		getXpathWebElement(yuiWidgetConstant.DET_ADDTOCART);
		click();
		waitForElementPresent(yuiWidgetConstant.CHECKOUT, methodName);
		getXpathWebElement(yuiWidgetConstant.CHECKOUT);
		click();
		billingInfo(methodName);
	}
}
