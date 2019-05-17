import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * The Human class takes care of the questions addressed by the users.
 */

public class Human {

	private String humanText;

	public void getHumanAsk(JTextField jtextfield) {
		humanText = jtextfield.getText();
	}

	public void printHumanAsk(JTextArea jtextarea, JTextField jtextfield) {
		jtextarea.append("Tu : " + humanText + "\n");
		jtextfield.setText("");
	}

	public String getHumanText() {
		return humanText;
	}

}
