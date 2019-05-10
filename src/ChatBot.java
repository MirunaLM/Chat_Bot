import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ChatBot {

	private Element raspuns;
	private String xmlText;

	public String searchRaspuns(xmlFile xmlFile, String humanText, JTextArea jtextarea) {
		for (int temp = 0; temp < xmlFile.getChatList().size(); temp++) {
			Element message = xmlFile.getChatList().get(temp);
			xmlText = message.getText();
			List<Element> listaRaspunsuri = message.getChildren();
			raspuns = listaRaspunsuri.get(0);
			if (humanText.equals(xmlText))
				return raspuns.getText();
		}
		jtextarea.append(
				"ChatBot : Nu stiu inca raspunsul la intrebarea ta, insa ma poti face mai destept \n daca scri tu unul scrie in consola \n");
		return null;
	}

	public void printRaspuns(JTextArea jtextarea, String raspunss) {
		jtextarea.append("ChatBot : " + raspunss + "\n");

	}

	public void raspunsuriVreme(JTextArea jtextarea, String question, Vreme vreme, SAXBuilder saxBuilder, File file)
			throws JDOMException, IOException {
		switch (question) {
		case "cum este vremea acum?":
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "name", jtextarea, 0, 0);
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "localtime", jtextarea, 0, 0);
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "text", jtextarea, 0, 1);
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "temp_c", jtextarea, 0, 1);
			break;
		case "cum este vremea maine?":
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "name", jtextarea, 0, 0);
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "date", jtextarea, 0, 3);
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "text", jtextarea, 0, 3);
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "maxtemp_c", jtextarea, 0, 3);
			break;
		case "care este temperatura maxima maine?":
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "maxtemp_c", jtextarea, 0, 3);
			break;
		case "care este temperatura maxima in 2 zile?":
			vreme.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "maxtemp_c", jtextarea, 0, 4);
			break;
		}
	}

}
