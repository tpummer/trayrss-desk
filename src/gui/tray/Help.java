package gui.tray;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import configuration.ReferenceCollection;

public class Help extends JFrame {

	public Help(){
		ReferenceCollection.HELP_WINDOW = this;
		this.setTitle(ReferenceCollection.HELP_TITLE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel helpIcon = new JLabel();
		helpIcon.setIcon(new ImageIcon(ReferenceCollection.ICON_HELP));
		this.getContentPane().add(helpIcon, BorderLayout.WEST);
		
		JLabel helpMsg = new JLabel("<html><center>" + ReferenceCollection.TRAYRSS_APP_TITLE + "<br/>"+
								  "<a href=\"http://trayrss.sourceforge.net\">Trayrss</a>" +
								  "<br/>by thefake - fake@sprossenwanne.at</center></html>");
		this.getContentPane().add(helpMsg, BorderLayout.CENTER);
		
		JButton helpOkButton = new JButton();
		helpOkButton.setText(ReferenceCollection.HELP_OK);
		helpOkButton.setSelected(true);
		helpOkButton.setToolTipText("Closes the help window");
		helpOkButton.setName(ReferenceCollection.HELP_OK);
		
		HelpActionListener helpAction = new HelpActionListener();
		helpOkButton.addActionListener(helpAction);
		
		this.getContentPane().add(helpOkButton, BorderLayout.SOUTH);
		
		this.pack();
		this.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight() - 50) / 2, 
				this.getWidth(), this.getHeight() + 50);
		this.setVisible(true);
	}
}
