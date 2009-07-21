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
package gui.tray;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.util.ConfigHelper;

import configuration.ReferenceCollection;

public class ConfigFrameActionListener implements ActionListener {
	ConfigFrame configFrame;
	
	public ConfigFrameActionListener(ConfigFrame inputFrame){
		this.configFrame = inputFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ReferenceCollection.CONFIG_CANCELBUTTON_TEXT)){
			ReferenceCollection.CONFIG_WINDOW.dispose();
			
		} else if (e.getActionCommand().equals(ReferenceCollection.CONFIG_DELETEBUTTON_TEXT)){
			//TODO delete Feed from table
			System.out.println("Feed will be deleted");
			
		} else if (e.getActionCommand().equals(ReferenceCollection.CONFIG_SAVEBUTTON_TEXT)){
			//TODO
			ReferenceCollection.LANGUAGE = (String) this.configFrame.getLanguageSelectorComboBox().getSelectedItem();
			ReferenceCollection.DISPLAY_COUNT = Integer.parseInt(this.configFrame.getDisplayCountField().getText());
			ReferenceCollection.DISPLAY_SECONDS = Integer.parseInt(this.configFrame.getDisplayTimeField().getText());
			ReferenceCollection.CONFIGURATION.save();
			ReferenceCollection.CONFIG_WINDOW.dispose();
			
		} 
	}

}
