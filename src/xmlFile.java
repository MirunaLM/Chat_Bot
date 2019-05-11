import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class xmlFile {
	private Element root;
	private Element newElement;
	private Element newChildElement;
	private Element question;
	private Element answer2;
	private List<Element> chatList;

	public xmlFile(Document document) throws JDOMException, IOException {
		chatList = document.getRootElement().getChildren();
		root = document.getRootElement();
	}

	public void addElement(String humanText, String answer) {
		newElement = root;
		question = new Element("question").setText(humanText);
		newElement.addContent(question);

		newChildElement = question;
		answer2 = new Element("answer").setText(answer);
		newChildElement.addContent(answer2);
	}

	public void addElement(String humanText) {
		newElement = root;
		question = new Element("question").setText(humanText);
		newElement.addContent(question);

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti aici noul raspuns : ");
		String newWord = sc.nextLine();

		newChildElement = question;
		answare = new Element("answer").setText(newWord);
		newChildElement.addContent(answare);
	}

	public void xmlUpdate(Document document, File file) {
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getRawFormat());
		try {
			xmlOutput.output(document, new FileWriter(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public List<Element> getChatList() {
		return chatList;
	}

	public Element getRoot() {
		return root;
	}
}
