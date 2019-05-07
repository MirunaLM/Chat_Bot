import java.util.List;

import javax.swing.JTextArea;

import org.jdom2.Element;

public class ChatBot {
	
	private Element raspuns;
	private String xmlText;

	public String searchRaspuns(xmlFile xmlFile, String humanText,JTextArea jtextarea)
	{
		for (int temp = 0; temp < xmlFile.getChatList().size(); temp++) {
			Element message = xmlFile.getChatList().get(temp);
			xmlText = message.getText();
			List<Element> listaRaspunsuri = message.getChildren();
			raspuns = listaRaspunsuri.get(0);
			if (humanText.equals(xmlText)) 
			return raspuns.getText();
			
		}
		jtextarea.append("ChatBot : Nu stiu inca raspunsul la intrebarea ta, insa ma poti face mai destept \n daca scri tu unul in consola \n");
		return null;
	}
	
	public void printRaspuns( JTextArea jtextarea, String raspunss)
	{
			jtextarea.append("ChatBot : " + raspunss + "\n");	
	}
	
}
