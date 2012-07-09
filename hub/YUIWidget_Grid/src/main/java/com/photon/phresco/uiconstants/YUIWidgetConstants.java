package com.photon.phresco.uiconstants;

import java.lang.reflect.Field;

public class YUIWidgetConstants {

	private ReadXMLFile readXml;
	public String TELEVISION = "televisiontab";
	public String COMPUTERS = "computerstab";
	public String MOBILE = "mobiletab";
	public String AUDIO_DEVICES = "audioDevicestab";
	public String CAMERAS = "camerastab";
	public String TABLETS = "tabletstab";
	public String MOVIESnMUSIC = "moviesNmusictab";
	public String VIDEOGAMES = "videoGamestab";
	public String MP3PLAYERS = "mp3Playerstab";
	public String ACCESSORIES = "accessoriestab";
	public String MORE = "moretab";
	// public String SEARCH = "search";
	public String HOME = "hometab";
	public String LOGINTAB = "logintab";
	public String SIGNUPTAB = "signuptab";
	public String PROD1_DETAILS = "prod1Details";
	// public String PROD1_AUDIO="prod1AudioDevices";
	public String UPDATECART = "updateCart";
	public String ADDTOCART = "addToCart";
	public String DET_ADDTOCART = "prod1DetailAddToCart";
	public String CHECKOUT = "checkout";
	public String REVIEWORDER = "reviewOrder";
	public String SUBMITORDER = "submitOrder";
	public String EMAIL = "billInfoEmail";
	public String LOGIN_EMAIL = "emailxpath";
	public String LOGIN_PASSWORD = "passwordxpath";
	public String REGISTER_PASSWORD = "regPassword";
	public String PASSWORD_VALUE = "passwordvalue";
	public String REGISTER_EMAIL = "regEmail";
	public String EMAIL_VALUE = "billInfoEmailValue";
	public String SUBMIT = "submitxpath";
	public String REGISTER_PHONENUMBER = "regPhoneNumber";
	public String REGISTER_SUCCESSMSG = "regSuccessMessage";
	public String LOGIN_SUCCESSMSG = "loginSuccessmsg";
	public String LOGIN_FAILUREMSG = "loginFailuremsg";
	public String EMPTY_EMAIL_ALERT = "emptyEmailAlert";
	public String FIRSTNAME = "billInfoFirstName";
	public String REGISTER_FIRSTNAME = "regFirstName";
	public String REGISTER_LASTNAME = "regLastName";
	public String INVALID_EMAILVALUE = "invalidemailvalue";
	public String INVALID_PASSWORDVALUE = "invalidpasswordvalue";
	public String FIRSTNAME_VALUE = "billInfoFirstNameValue";
	public String LASTNAME = "billInfoLastName";
	public String LASTNAME_VALUE = "billInfoLastNameValue";
	public String COMPANY = "billInfoCompany";
	public String COMPANY_VALUE = "billInfoCompanyValue";
	public String ADDRESS1 = "billInfoAddress1";
	public String ADDRESS1_VALUE = "billInfoAddress1Value";
	public String ADDRESS2 = "billInfoAddress2";
	public String ADDRESS2_VALUE = "billInfoAddress2Value";
	public String CITY = "billInfoCity";
	public String CITY_VALUE = "billInfoCityValue";
	public String STATE = "billInfoState";
	public String STATE_VALUE = "billInfoStateValue";
	public String POSTALCODE = "billInfoPostCode";
	public String POSTALCODE_VALUE = "billInfoPostCodeValue";
	public String PHONENUMBER = "billInfoPhoneNumber";
	public String PHONENUMBER_VALUE = "billInfoPhoneNumberValue";
	public String CARDTYPE = "cardInfoCardType";
	public String CARDNUMBER = "cardInfoCardNumber";
	public String CARDNUMBER_VALUE = "cardInfoCardNumberValue";
	public String SECURITYNUMBER = "cardInfoSecurityNumber";
	public String SECURITYNUMBER_VALUE = "cardInfoSecurityNumberValue";
	public String NAMEONCARD = "cardInfoNameOnCard";
	public String NAMEONCARD_VALUE = "cardInfoNameOnCardValue";
	public String COMMENTS = "billInfoComments";
	public String COMMENTS_VALUE = "billInfoCommentsValue";

	public YUIWidgetConstants() {
		try {
			readXml = new ReadXMLFile();
			readXml.loadYUIWidgetConstants();
			Field[] arrayOfField1 = super.getClass().getFields();
			Field[] arrayOfField2 = arrayOfField1;
			int i = arrayOfField2.length;
			for (int j = 0; j < i; ++j) {
				Field localField = arrayOfField2[j];
				Object localObject = localField.get(this);
				if (localObject instanceof String) {
					String constantValue = readXml.getValue((String) localObject);
					if (constantValue != null && constantValue.length() > 0) {
						localField.set(this, constantValue);
					} else {
						throw new Exception(
								"The given constant value should not be zero");
					}

				}
			}
		} catch (Exception localException) {
			throw new RuntimeException("Loading "
					+ super.getClass().getSimpleName() + " failed",
					localException);
		}
	}
}
