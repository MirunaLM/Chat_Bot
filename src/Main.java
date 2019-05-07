import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class Main {

	public static void main(String[] args)
			throws IOException, JDOMException, ParserConfigurationException, SAXException {
		
		Bot bot = new Bot();
		Human human = new Human();
		ChatBot chatbot = new ChatBot();

		File inputFile = new File("chatBot.xml");
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);

		xmlFile xmlFile = new xmlFile(document);

		bot.getBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				human.getHumanAsk(bot.getBox());
				human.printHumanAsk(bot.getChat(), bot.getBox());
				
				String raspuns = chatbot.searchRaspuns(xmlFile, human.getHumanText(), bot.getChat());
				
				if (raspuns != null) {
					chatbot.printRaspuns(bot.getChat(), raspuns);
				} else {
					xmlFile.addElement(human.getHumanText());
					xmlFile.xmlUpdate(document, inputFile);
				}
				
			}
			
		});
		
	}
		
}
