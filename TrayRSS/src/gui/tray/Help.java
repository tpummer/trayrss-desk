package gui.tray;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import configuration.ReferenceCollection;

public class Help extends JFrame {

	public Help(){
		this.setTitle(ReferenceCollection.HELP_TITLE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel test = new JLabel("<html><center>" + ReferenceCollection.TRAYRSS_APP_TITLE + "<br/>"+
								  "<a href=\"http://trayrss.sourceforge.net\">Trayrss</a>" +
								  "<br/>by thefake - fake@sprossenwanne.at</center></html>");
		this.getContentPane().add(test, BorderLayout.CENTER);
		this.pack();
		this.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight() - 50) / 2, 
				this.getWidth(), this.getHeight() + 50);
		this.setVisible(true);
	}
}
