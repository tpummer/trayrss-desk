/*
    TrayRSS - simply notification of feed information
    (c) 2009-2011 TrayRSS Developement Team
    visit the project at http://trayrss.nullpointer.at/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.

 */
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
