package com.photon.phresco.uiconstants;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.photon.phresco.selenium.util.ScreenException;

public class ReadXMLFile {

	private Element eElement;
	private Log log = LogFactory.getLog(getClass());
	private final String phrscoEnvironments = "./src/main/resources/phresco-env-config.xml";
	private final String YUIWigetEnvironments = "./src/main/resources/YUIWidget-Constants.xml";

	public ReadXMLFile() throws ScreenException {

	}

	public void loadPhrescoConstansts(String properties) throws ScreenException {

		try {
			File fXmlFile = new File(properties);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName("environment");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					eElement = (Element) nNode;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue(String elementName) {

		NodeList nlList = eElement.getElementsByTagName(elementName).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		try {
			if (nValue.getNodeValue().length() > 0
					&& nValue.getNodeValue() != null) {

				
			} else {
				log.info("-------Element value zero------" + "element name-->>"
						+ elementName);
			}
		} catch (Throwable th) {
			th.printStackTrace();
		}
		return nValue.getNodeValue();
	}

	public void loadPhrescoEnvironmentConfig() throws ScreenException {
		log.info("------------Enviroments get loaded-------");
		loadPhrescoConstansts(phrscoEnvironments);
	}

	public void loadYUIWidgetConstants() throws ScreenException {
		log.info("------------Enviroments get loaded-------");
		loadPhrescoConstansts(YUIWigetEnvironments);
	}
}