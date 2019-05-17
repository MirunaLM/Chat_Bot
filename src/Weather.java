import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*
 * The WEATHER class is responsible for the questions about the weather. Create a XML file depending on the introduced location 
 */

public class Weather {

	private Document documentWeather;

	public Document getDocument() {
		return documentWeather;
	}

	/*
	 * depending of the city introduced in the console an URLis generated which
	 * corresponds to *a XML type page which shows the weather in the respective
	 * city for 7 days
	 */
	public String createURL() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Introduceti aici Localitatea : ");
		String city = scanner.next();
		String url = "http://api.apixu.com/v1/forecast.xml?key=da479f76ffb94335af2161350190905&q=" + city + "&days=7";

		return url;
	}

	/*
	 * Every time there is a new location introduced, the XML file updates itself
	 * depending on the new URL
	 * 
	 * @param urlString = The URL address connected to the API weather website
	 */
	public void createXML(File file, String urlString)
			throws IOException, ParserConfigurationException, SAXException, TransformerException {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		documentWeather = builder.parse(connection.getInputStream());
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer xform = tfactory.newTransformer();
		xform.transform(new DOMSource(documentWeather), new StreamResult(file));
	}

	/*
	 * This is a recursive which goes through all the tags of the XML file and which
	 * returns the specific tag text for a certain date
	 * 
	 * @param list = the list of the copies of the root
	 * 
	 * @param contor = counts the repetition of the tags. The tags repeats themself
	 * due to the fact that the XML file contains 7 days with identical tags, except
	 * the ones general
	 * 
	 * @param day = the day when the weather is checked
	 * 
	 * @param tagName = the name of the sought tag
	 */
	public void searchXML(List<Element> list, String tagName, JTextArea jtextarea, int contor, int day) {
		for (int i = 0; i < list.size(); i++) {

			// if the sought tag is found in the tagList of the XML file and the number of
			// repeats equals with the day inserted

			if (list.get(i).getName().equals(tagName) && contor == day) {
				jtextarea.append("ChatBot" + " : " + list.get(i).getName() + " : " + list.get(i).getText() + "\n");
			}
			List<Element> listChild = list.get(i).getChildren();
			// if the tag has itself other childTags than the function searchXML is called
			// again

			if (listChild.size() != 0) {
				searchXML(listChild, tagName, jtextarea, contor++, day);
			}
		}

	}

}
