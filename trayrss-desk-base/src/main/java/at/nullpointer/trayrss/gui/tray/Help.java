/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.gui.tray;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.messages.Messages;

public class Help
        extends JFrame {

    public static final String ICON_HELP = "./img/trayrss.png";

    /**
	 * 
	 */
    private static final long serialVersionUID = -5454404301398733509L;


    public Help() {

        this.setTitle( Messages.getMessageResolver( Messages.GUI ).getString( "help.frame.caption", "Help" ) );
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        JLabel helpIcon = new JLabel();
        ImageIcon imageIcon = new ImageIcon( Help.class.getResource( ICON_HELP.substring( 1 ) ) ); // load the image to
                                                                                                   // a imageIcon
        if ( ReferenceCollection.TRAYRSS_APP_TITLE.equals( "TrayRSS null" ) )
            imageIcon = new ImageIcon( ICON_HELP );
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance( 120, 120, java.awt.Image.SCALE_SMOOTH ); // scale it the smooth way
        imageIcon = new ImageIcon( newimg ); // transform it back
        helpIcon.setIcon( imageIcon );
        this.getContentPane().add( helpIcon, BorderLayout.WEST );

        JLabel helpMsg = new JLabel( "<html><center>" + ReferenceCollection.TRAYRSS_APP_TITLE + "<br/>"
                + "<a href=\"http://trayrss.sourceforge.net\">Trayrss</a>"
                + "<br/>by Thomas Pummer - thefake@users.sourceforge.net</center></html>" );
        this.getContentPane().add( helpMsg, BorderLayout.CENTER );

        JButton helpOkButton = new JButton();
        helpOkButton.setText( Messages.getMessageResolver( Messages.GUI ).getString( "help.button.caption", "OK" ) );
        helpOkButton.setSelected( true );
        helpOkButton.setToolTipText( Messages.getMessageResolver( Messages.GUI ).getString( "help.tooltip.text",
                "Closes the help window" ) );
        helpOkButton.setName( Messages.getMessageResolver( Messages.GUI ).getString( "help.button.caption", "OK" ) );

        HelpActionListener helpAction = new HelpActionListener( this );
        helpOkButton.addActionListener( helpAction );

        this.getContentPane().add( helpOkButton, BorderLayout.SOUTH );

        this.pack();
        this.setBounds( ( Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth() ) / 2, ( Toolkit
                .getDefaultToolkit().getScreenSize().height - this.getHeight() - 50 ) / 2, this.getWidth(),
                this.getHeight() + 50 );
        this.setVisible( true );
    }
}
