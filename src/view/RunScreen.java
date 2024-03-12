package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CancelController;
import controller.RunController;
import controller.SearchController;

public class RunScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pane;
	private JTextField textField;
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private JButton browseButton = new JButton("Browse");

	public static void main(String[] args) {
		if(System.getProperty("os.name").contains("Windows")) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						RunScreen frame = new RunScreen();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			JOptionPane.showMessageDialog(null, "This system is not a Windows OS", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	public RunScreen() {
		setTitle("Run - " + System.getProperty("os.name"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 171);
		pane = new JPanel();
		pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pane);
		
		textField = new JTextField();
		textField.setBounds(65, 49, 283, 20);
		textField.setColumns(10);
		
		JLabel description = new JLabel("Type the path of a executable or click browse");
		description.setBounds(65, 22, 283, 14);
		description.setForeground(new Color(128, 128, 128));
		
		JLabel fieldLabel = new JLabel("Open:");
		fieldLabel.setBounds(15, 52, 32, 14);
		fieldLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		fieldLabel.setForeground(new Color(105, 105, 105));
		
		JLabel icon = new JLabel(">_");
		icon.setBounds(15, 13, 32, 25);
		icon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		pane.setLayout(null);
		pane.add(fieldLabel);
		pane.add(icon);
		okButton.setBounds(65, 75, 85, 23);
		pane.add(okButton);
		cancelButton.setBounds(164, 75, 85, 23);
		pane.add(cancelButton);
		browseButton.setBounds(263, 75, 85, 23);
		pane.add(browseButton);
		pane.add(description);
		pane.add(textField);
		
		RunController runController = new RunController(this.textField, this);
		SearchController searchController = new SearchController(this.textField);
		CancelController cancelController = new CancelController(this);
		
		browseButton.addActionListener(searchController);
		okButton.addActionListener(runController);
		cancelButton.addActionListener(cancelController);
	}
}
