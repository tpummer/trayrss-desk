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

import at.nullpointer.trayrss.checks.CheckLib;
import at.nullpointer.trayrss.checks.DateValidator;
import at.nullpointer.trayrss.checks.FeedTableValidator;
import at.nullpointer.trayrss.checks.TimeFrameValidator;
import at.nullpointer.trayrss.configuration.ReferenceCollection;

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigFrameActionListener implements ActionListener {
	ConfigFrame configFrame;

	public ConfigFrameActionListener(ConfigFrame inputFrame) {
		this.configFrame = inputFrame;
	}

	public void performConfigSave() {
		// main
		ReferenceCollection.LANGUAGE = (String) this.configFrame
				.getLanguageSelectorComboBox().getSelectedItem();
		ReferenceCollection.DISPLAY_COUNT = Integer.parseInt(this.configFrame
				.getDisplayCountField().getText());
		ReferenceCollection.DISPLAY_SECONDS = Integer.parseInt(this.configFrame
				.getDisplayTimeField().getText());

		// time
		ReferenceCollection.CONFIG_TIMEFRAMES_VALUE = this.configFrame
				.getTimeframesField().getText();

		if (this.configFrame.getMonitoringDaysMo().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE = "false";

		if (this.configFrame.getMonitoringDaysTu().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE = "false";

		if (this.configFrame.getMonitoringDaysWe().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE = "false";

		if (this.configFrame.getMonitoringDaysTh().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE = "false";

		if (this.configFrame.getMonitoringDaysFr().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE = "false";

		if (this.configFrame.getMonitoringDaysSa().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE = "false";

		if (this.configFrame.getMonitoringDaysSu().isSelected())
			ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE = "true";
		else
			ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE = "false";

		if (this.configFrame.getStartJCalendar().getDate() != null)
			ReferenceCollection.CONFIG_VACATION_START_VALUE = ""
					+ this.configFrame.getStartJCalendar().getDate().getTime();
		else
			ReferenceCollection.CONFIG_VACATION_START_VALUE = "";

		if (this.configFrame.getEndJCalendar().getDate() != null)
			ReferenceCollection.CONFIG_VACATION_END_VALUE = ""
					+ this.configFrame.getEndJCalendar().getDate().getTime();
		else
			ReferenceCollection.CONFIG_VACATION_END_VALUE = "";

		ReferenceCollection.CONFIGURATION.save();
	}

	/**
	 * To store the entire feed information
	 */
	public void performFeedSave() {
		// TODO FeedDelete

		TableModel table = ReferenceCollection.CONFIG_WINDOW.getFeedsTable()
				.getModel();

		int length = table.getRowCount();

		for (int i = length - 1; i >= 0; i--) {
			ReferenceCollection.FEED_TABLE.getTable()[i][0] = table.getValueAt(
					i, 0);
			ReferenceCollection.FEED_TABLE.getTable()[i][1] = table.getValueAt(
					i, 1);
			ReferenceCollection.FEED_TABLE.getTable()[i][2] = table.getValueAt(
					i, 2);
			ReferenceCollection.FEED_TABLE.getTable()[i][3] = table.getValueAt(
					i, 3);
			ReferenceCollection.FEED_TABLE.getTable()[i][4] = table.getValueAt(
					i, 4);
		}

		ReferenceCollection.FEED_TABLE.store();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(
				ReferenceCollection.CONFIG_CANCELBUTTON_TEXT)) {
			ReferenceCollection.CONFIG_WINDOW.dispose();

		} else if (e.getActionCommand().equals(
				ReferenceCollection.CONFIG_SAVEBUTTON_TEXT)) {
			// Validation
			boolean valid = true;

			valid = valid
					&& FeedTableValidator.checkURL(
							ReferenceCollection.FEED_TABLE.getTable(), 2);

			valid = valid
					&& DateValidator.checkDates(this.configFrame
							.getStartJCalendar().getDate(), this.configFrame
							.getEndJCalendar().getDate());

			valid = valid
					&& CheckLib.longMessage(CheckLib.checkLong(this.configFrame
							.getDisplayCountField().getText()),
							this.configFrame.getDisplayCountField().getName());

			valid = valid
					&& CheckLib.longMessage(CheckLib.checkLong(this.configFrame
							.getDisplayTimeField().getText()), this.configFrame
							.getDisplayTimeField().getName());

			valid = valid
					&& TimeFrameValidator.timeFramesMessage(TimeFrameValidator
							.checkTimeFrames(this.configFrame
									.getTimeframesField().getText()),
							this.configFrame.getTimeframesField().getName());
			
			/*
			 * TODO Validierung nach Text
			 * System.out.println(this.configFrame.getStartJCalendar().getText());
			 */

			if (valid) {
				// valid
				performConfigSave();
				performFeedSave();
				ReferenceCollection.MONITOR.feedChanged();
				ReferenceCollection.CONFIG_WINDOW.dispose();
			}

		} else if (e.getActionCommand().equals(
				ReferenceCollection.CONFIG_ADDBUTTON_TEXT)) {
			ReferenceCollection.FEED_TABLE.addEmptyRow();
			FeedTableModel feedTableModel = new FeedTableModel();
			ReferenceCollection.CONFIG_WINDOW
					.refreshTableWithoutDB(feedTableModel);

		} else if (e.getActionCommand().equals(
				ReferenceCollection.CONFIG_DELETEBUTTON_TEXT)) {
			int delete = ReferenceCollection.CONFIG_WINDOW.getFeedsTable()
					.getSelectedRow();
			ReferenceCollection.FEED_TABLE.deleteRow(delete);
			FeedTableModel feedTableModel = new FeedTableModel();
			ReferenceCollection.CONFIG_WINDOW
					.refreshTableWithoutDB(feedTableModel);

		}
	}

}
