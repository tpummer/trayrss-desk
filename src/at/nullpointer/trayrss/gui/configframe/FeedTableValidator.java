package at.nullpointer.trayrss.gui.configframe;

import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FeedTableValidator {
	JFrame root;
	
	public FeedTableValidator(JFrame root){
		this.root = root;
	}
	
	public boolean checkURL(String url){
		
		try {
			URL test = new URL(url);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(root, "Not a valid URL", "Not a valid URL", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
