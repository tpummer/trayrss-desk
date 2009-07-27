/**
    RSSTray - simply alerting at new Feed Information
    Copyright (C) 2009 Thomas Pummer
    conatct me fake (at) sprossenwanne.at

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package at.nullpointer.trayrss.gui.tray;


import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.Shutdown;


public class TrayMenuActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ReferenceCollection.TRAYMENU_MONITOR)){
			ReferenceCollection.TRAY_ICON.displayMessage("Useraction", "Handmade Monitoringintervall", 
					TrayIcon.MessageType.INFO);
			
		}else if(e.getActionCommand().equals(ReferenceCollection.TRAYMENU_CONFIG)){
			ConfigFrame conig = new ConfigFrame();
			
		}else if(e.getActionCommand().equals(ReferenceCollection.TRAYMENU_HELP)){
			Help help = new Help();
			
		}else if(e.getActionCommand().equals(ReferenceCollection.TRAYMENU_EXIT)){
			Shutdown shutdown = new Shutdown();
			shutdown.now();
			System.exit(0);
		}

	}

}