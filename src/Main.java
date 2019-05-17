import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args)
			throws IOException, JDOMException, ParserConfigurationException, SAXException {

		File inputFile = new File("chatBot.xml");
		File outputFile = new File("Weather.xml");

		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);

		XmlFile xmlFile = new XmlFile(document);
		Bot bot = new Bot();
		Human human = new Human();
		ChatBot chatbot = new ChatBot();

		bot.getBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				human.getHumanAsk(bot.getBox());
				human.printHumanAsk(bot.getChat(), bot.getBox());
				// Here the question is searched in the chatBot.xml file and it is saved in the
				// "answer" variable
				String answer = chatbot.searchAnswer(xmlFile, human.getHumanText(), bot.getChat());

				if (answer != null) // If the answer exists in the chatBot.xml file
				{
					chatbot.printAnswer(bot.getChat(), answer);
				} else
				{
					if (chatbot.checkWeather(human.getHumanText())) // If specific words were found in the structure of
																	// the question
					{
						Weather weather = new Weather();
						try 
						{
							weather.createXML(outputFile, weather.createURL());
							// The answer are searched and then are printed
							chatbot.answerWeather(bot.getChat(), weather, saxBuilder, outputFile, human.getHumanText());
						} catch (IOException | ParserConfigurationException | SAXException | TransformerException e1) {
							bot.getChat().append("ChatBot: Nu am gasit localitatea! \n");
							// e1.printStackTrace();
						} catch (JDOMException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else // If specific words were not found in the structure of the question
					{
						bot.getChat().append(
								"ChatBot : Nu stiu inca raspunsul la intrebarea ta, insa ma poti face mai destept \n daca scri tu unul scrie in consola \n");
						// An new element is added in chatBot.xml
						xmlFile.addElement(human.getHumanText());
						xmlFile.xmlUpdate(document, inputFile);
					}
				}
			}
		});
	}
}
