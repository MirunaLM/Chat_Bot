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

public class Vreme {

	private Document documentVreme;

	public Document getDocument() {
		return documentVreme;
	}
	
	public String createURL()
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti aici Localitatea : ");
		String localitate = sc.next();
		String url = "http://api.apixu.com/v1/forecast.xml?key=da479f76ffb94335af2161350190905&q=" + localitate + "&days=7";
		
		return url;
	}

	public void createXML(File file, String urlString)
			throws IOException, ParserConfigurationException, SAXException, TransformerException {
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		documentVreme = builder.parse(conn.getInputStream());
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer xform = tfactory.newTransformer();
		xform.transform(new DOMSource(documentVreme), new StreamResult(file));
	}

	public void searchXML(List<Element> list, String str, JTextArea jtextarea, int contor, int zi) 
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getName().equals(str) && contor == zi)
			{
				jtextarea.append("ChatBot" + " : " + list.get(i).getName() + " : " + list.get(i).getText() + "\n");
			}
			List<Element> listChild = list.get(i).getChildren();
			if (listChild.size() != 0)
			{
				searchXML(listChild, str, jtextarea, contor++, zi);
			}
		}

	}

}
