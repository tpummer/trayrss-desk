package at.nullpointer.trayrss.gui.tray;

import at.nullpointer.trayrss.configuration.ReferenceCollection;

import javax.swing.*;
import java.awt.*;


public class Help extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5454404301398733509L;

	public Help(){
		ReferenceCollection.HELP_WINDOW = this;
		this.setTitle(ReferenceCollection.HELP_TITLE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel helpIcon = new JLabel();
		helpIcon.setIcon(new ImageIcon(ReferenceCollection.ICON_HELP));
		this.getContentPane().add(helpIcon, BorderLayout.WEST);
		
		JLabel helpMsg = new JLabel("<html><center>" + ReferenceCollection.TRAYRSS_APP_TITLE + "<br/>"+
								  "<a href=\"http://trayrss.sourceforge.net\">Trayrss</a>" +
								  "<br/>by Thomas Pummer - thefake@users.sourceforge.net</center></html>");
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
