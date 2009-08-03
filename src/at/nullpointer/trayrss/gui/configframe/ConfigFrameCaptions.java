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
package at.nullpointer.trayrss.gui.configframe;

import java.util.Properties;

import at.nullpointer.trayrss.configuration.ReferenceCollection;


public class ConfigFrameCaptions {

	public static void load(Properties props) {
		String pre = "trayrss." + ReferenceCollection.LANGUAGE;

		ReferenceCollection.CONFIG_TITLE = props.getProperty(pre
				+ ".config_title");
		ReferenceCollection.CONFIG_TIMEFRAMESLABEL = props.getProperty(pre
				+ ".config_timeframesLabel.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSLABEL = props.getProperty(pre
				+ ".config_monitoringDaysLabel.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSMO = props.getProperty(pre
				+ ".config_monitoringDaysMo.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSWE = props.getProperty(pre
				+ ".config_monitoringDaysWe.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSFR = props.getProperty(pre
				+ ".config_monitoringDaysFr.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSSA = props.getProperty(pre
				+ ".config_monitoringDaysSa.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSTH = props.getProperty(pre
				+ ".config_monitoringDaysTh.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSTU = props.getProperty(pre
				+ ".config_monitoringDaysTu.text");
		ReferenceCollection.CONFIG_MONITORINGDAYSSU = props.getProperty(pre
				+ ".config_monitoringDaysSu.text");
		ReferenceCollection.CONFIG_VACATIONSTARTLABEL = props.getProperty(pre
				+ ".config_vacationStartLabel.text");
		ReferenceCollection.CONFIG_VACATIONLABEL = props.getProperty(pre
				+ ".config_vacationLabel.text");
		ReferenceCollection.CONFIG_VACATIONENDLABEL = props.getProperty(pre
				+ ".config_vacationEndLabel.text");
		ReferenceCollection.CONFIG_TIMEFRAMESPANEL_BORDER_TITLE = props
				.getProperty(pre + ".config_timeframesPanel.border.title");
		ReferenceCollection.CONFIG_FEEDSPANEL_BORDER_TITLE = props
				.getProperty(pre + ".config_feedsPanel.border.title");
		ReferenceCollection.CONFIG_SAVEBUTTON_TEXT = props.getProperty(pre
				+ ".config_saveButton.text");
		ReferenceCollection.CONFIG_ADDBUTTON_TEXT = props.getProperty(pre
				+ ".config_addButton.text");
		ReferenceCollection.CONFIG_DELETEBUTTON_TEXT = props.getProperty(pre
				+ ".config_deleteButton.text");
		ReferenceCollection.CONFIG_CANCELBUTTON_TEXT = props.getProperty(pre
				+ ".config_cancelButton.text");
		ReferenceCollection.CONFIG_MAINCONFIGPANEL_BORDER_TITLE = props
				.getProperty(pre + ".config_mainConfigPanel.border.title");
		ReferenceCollection.CONFIG_DISPLAYCOUNTLABEL = props.getProperty(pre
				+ ".config_displayCountLabel.text");
		ReferenceCollection.CONFIG_DISPLAYTIMELABEL = props.getProperty(pre
				+ ".config_displayTimeLabel.text");
		ReferenceCollection.CONFIG_LANGUAGESELECTORLABEL = props.getProperty(pre
				+ ".config_languageSelectorLabel.text");
		ReferenceCollection.CONFIG_LANGUAGESELECTORFIELD = props.getProperty(pre
				+ ".config_languageSelectorField.text");
		String[] configTableHeader = {
				props.getProperty(pre + ".config_table.header.column_id"),
				props.getProperty(pre + ".config_table.header.column_one"),
				props.getProperty(pre + ".config_table.header.column_two"),
				props.getProperty(pre + ".config_table.header.column_three"),
				props.getProperty(pre + ".config_table.header.column_four") };
		ReferenceCollection.CONFIG_TABLE_HEADER = configTableHeader;
	}

}
