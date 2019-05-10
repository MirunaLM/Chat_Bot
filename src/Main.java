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
		File outputFile = new File("Vreme.xml");

		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);

		xmlFile xmlFile = new xmlFile(document);
		Bot bot = new Bot();
		Human human = new Human();
		ChatBot chatbot = new ChatBot();

		bot.getBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				human.getHumanAsk(bot.getBox());
				human.printHumanAsk(bot.getChat(), bot.getBox());

				String raspuns = chatbot.searchRaspuns(xmlFile, human.getHumanText(), bot.getChat());

				if (raspuns != null)
				{
					if (raspuns.equals("http://api.apixu.com/v1/current.xml?key=da479f76ffb94335af2161350190905&q="))
					{
						Vreme vreme = new Vreme();
						try {
							vreme.createXML(outputFile, vreme.createURL());
							chatbot.raspunsuriVreme(bot.getChat(), human.getHumanText(), vreme, saxBuilder, outputFile);
						} catch (IOException | ParserConfigurationException | SAXException | TransformerException e1) {
							bot.getChat().append("ChatBot: Nu am gasit localitatea! \n");
							// e1.printStackTrace();
						} catch (JDOMException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else
						chatbot.printRaspuns(bot.getChat(), raspuns);
				} else 
				{
					xmlFile.addElement(human.getHumanText());
					xmlFile.xmlUpdate(document, inputFile);
				}

			}

		});

	}

}
