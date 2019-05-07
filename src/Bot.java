import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.paint.Color;

public class Bot {


	private JTextArea chatPrint = new JTextArea();
	private JScrollPane scroll = new JScrollPane(chatPrint,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JTextField chatWrite = new JTextField();

	public Bot() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLayout(null);
		frame.setSize(600, 600);
		frame.setTitle("ChatBot");
		frame.add(chatPrint);
		frame.add(scroll);
		frame.add(chatWrite);

		chatPrint.setSize(500, 400);
		chatPrint.setLocation(2, 2);
		chatWrite.setSize(500, 50);
		chatWrite.setLocation(2, 450);
		
	}

	public JTextArea getChat() {
		return chatPrint;
	}
	
	
	public JTextField getBox() {
		return chatWrite;
	}
	
}
