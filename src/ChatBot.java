import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/*
 * The ChatBot class takes care of the chat's actions.It can replay to questions and can offer with precision weather info.
 * It is capable to recognize questions about weather by certain words
 */

public class ChatBot {

	private Element answer;
	private String xmlText;

	/*
	 * This method seeks the question addressed by the user in the XML file.
	 * 
	 * @return the extracted answer from the ChildTag of the specific question or
	 * NULL if the question does not exist in the XML file
	 */
	public String searchAnswer(xmlFile xmlFile, String humanText, JTextArea jtextarea) {
		for (int temp = 0; temp < xmlFile.getChatList().size(); temp++) {
			Element message = xmlFile.getChatList().get(temp);
			xmlText = message.getText();
			List<Element> listaRaspunsuri = message.getChildren();
			answer = listaRaspunsuri.get(0);
			if (humanText.equals(xmlText)) {
				return answer.getText();
			}
		}
		return null;
	}

	public void printAnswer(JTextArea jtextarea, String answer) {
		jtextarea.append("ChatBot : " + answer + "\n");
	}

	/*
	 * If the checkWeather function is TRUE, then is executed the function which
	 * shows the weather in a certain day in conformity with which certain
	 * specific words were found in the structure of the question(eg acum)
	 * 
	 * @param humanText = The question addressed by the user
	 */

	public boolean checkWeather(String humanText) {
		for (String word : humanText.split(" ")) {
			if ((word.equals("acum") || word.equals("temperatura") || word.equals("maine?") || word.equals("maine")
					|| word.equals("rasaritul") || word.equals("acum?") || word.equals("azi") || word.equals("acum")
					|| word.equals("azi?"))) {
				return true;
			}
		}

		return false;
	}

	/*
	 * If the checkWeather function is TRUE, then it executes the function which
	 * shows the weather during a certain day in conformity with which certain
	 * specific words were found in the structure of the question
	 */

	public void answerWeather(JTextArea jtextarea, Weather weather, SAXBuilder saxBuilder, File file, String humanText)
			throws JDOMException, IOException {

		for (String word : humanText.split(" ")) {
			if ((word.equals("acum") || word.equals("azi") || word.equals("ploua?") || word.equals("azi?"))) {
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "name", jtextarea, 0, 0);
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "localtime", jtextarea, 0, 0);
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "text", jtextarea, 0, 1);
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "temp_c", jtextarea, 0, 1);
			}
			if ((word.equals("maine") || word.equals("maine?"))) {
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "name", jtextarea, 0, 0);
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "date", jtextarea, 0, 3);
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "text", jtextarea, 0, 3);
				weather.searchXML(saxBuilder.build(file).getRootElement().getChildren(), "maxtemp_c", jtextarea, 0, 3);
			}
		}
	}

}
