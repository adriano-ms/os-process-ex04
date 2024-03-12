package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RunController implements ActionListener{
	
	private JTextField textField;
	private JFrame frame;
	
	public RunController(JTextField textField, JFrame frame) {
		this.frame = frame;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean sucess = true;
		try {
			BufferedReader bReader;
			bReader = execCommand(textField.getText().trim());
			String line = bReader.readLine();
			while(line != null) {
				System.out.println(line);
				line = bReader.readLine();
			}
		} catch (Exception e1) {
			try {
				File file = new File(textField.getText().trim());
					if(file.exists())
						execCommand("explorer \"" + file.getPath() + "\"");
					else
						throw e1;
			} catch (Exception e2) {
				sucess = false;
				JOptionPane.showMessageDialog(frame, e2.getMessage().substring(0, e2.getMessage().indexOf(":")), textField.getText(), JOptionPane.ERROR_MESSAGE);
			}
		}
		if(sucess) frame.dispose();
	}
	
	private BufferedReader execCommand(String command) throws IOException {
		return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
	}
}
