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

import java.awt.Toolkit;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.hibernate.Session;
import org.hibernate.Transaction;

import at.nullpointer.trayrss.configuration.ReferenceCollection;
import at.nullpointer.trayrss.configuration.feeds.FeedDAO;
import at.nullpointer.trayrss.configuration.feeds.FeedTable;
import at.nullpointer.trayrss.configuration.feeds.db.Feed;

import com.toedter.calendar.JDateChooser;

public class ConfigFrame extends JFrame {

	private javax.swing.JButton cancelButton;
	private javax.swing.JPanel configurationframe;
	private javax.swing.JButton addButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JTextField displayCountField;
	private javax.swing.JLabel displayCountLabel;
	private javax.swing.JTextField displayTimeField;
	private javax.swing.JLabel displayTimeLabel;
	private javax.swing.JPanel feedsPanel;
	private javax.swing.JScrollPane feedsScrollPane;
	private javax.swing.JTable feedsTable;
	private JDateChooser startJCalendar;
	private JDateChooser endJCalendar;
	private javax.swing.JPanel mainConfigPanel;
	private javax.swing.JCheckBox monitoringDaysFr;
	private javax.swing.JLabel monitoringDaysLabel;
	private javax.swing.JCheckBox monitoringDaysMo;
	private javax.swing.JCheckBox monitoringDaysSa;
	private javax.swing.JCheckBox monitoringDaysSu;
	private javax.swing.JCheckBox monitoringDaysTh;
	private javax.swing.JCheckBox monitoringDaysTu;
	private javax.swing.JCheckBox monitoringDaysWe;
	private javax.swing.JButton saveButton;
	private javax.swing.JTextField timeframesField;
	private javax.swing.JLabel timeframesLabel;
	private javax.swing.JPanel timeframesPanel;
	private javax.swing.JLabel vacationEndLabel;
	private javax.swing.JLabel vacationLabel;
	private javax.swing.JLabel vacationStartLabel;
	private javax.swing.JLabel languageSelectorLabel;
	private javax.swing.JComboBox languageSelectorComboBox;

	private ConfigFrameCaptions captions;

	public ConfigFrame() {

		ReferenceCollection.CONFIG_WINDOW = this;
		this.captions = new ConfigFrameCaptions();

		configurationframe = new javax.swing.JPanel();
		timeframesPanel = new javax.swing.JPanel();
		timeframesLabel = new javax.swing.JLabel();
		timeframesField = new javax.swing.JTextField();
		monitoringDaysLabel = new javax.swing.JLabel();
		monitoringDaysMo = new javax.swing.JCheckBox();
		monitoringDaysTu = new javax.swing.JCheckBox();
		monitoringDaysWe = new javax.swing.JCheckBox();
		monitoringDaysTh = new javax.swing.JCheckBox();
		monitoringDaysFr = new javax.swing.JCheckBox();
		monitoringDaysSa = new javax.swing.JCheckBox();
		monitoringDaysSu = new javax.swing.JCheckBox();
		vacationLabel = new javax.swing.JLabel();
		vacationStartLabel = new javax.swing.JLabel();
		startJCalendar = new JDateChooser();
		vacationEndLabel = new javax.swing.JLabel();
		endJCalendar = new JDateChooser();
		feedsPanel = new javax.swing.JPanel();
		feedsScrollPane = new javax.swing.JScrollPane();
		feedsTable = new javax.swing.JTable();
		saveButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		addButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();
		mainConfigPanel = new javax.swing.JPanel();
		displayCountLabel = new javax.swing.JLabel();
		displayCountField = new javax.swing.JTextField();
		displayTimeLabel = new javax.swing.JLabel();
		displayTimeField = new javax.swing.JTextField();
		languageSelectorLabel = new javax.swing.JLabel();
		languageSelectorComboBox = new JComboBox();

		this.setTitle(ReferenceCollection.CONFIG_TITLE);

		languageSelectorLabel
				.setText(ReferenceCollection.CONFIG_LANGUAGESELECTORLABEL);
		languageSelectorLabel.setName("languageSelectorLabel");

		languageSelectorComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				ReferenceCollection.CONFIG_LANGUAGESELECTORFIELD.split(",")));
		languageSelectorComboBox.setName("languageSelectorComboBox");
		languageSelectorComboBox.setSelectedItem(ReferenceCollection.LANGUAGE);

		configurationframe.setMinimumSize(new java.awt.Dimension(800, 400));
		configurationframe.setName("Configuration Frame"); // NOI18N

		timeframesPanel
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder(ReferenceCollection.CONFIG_TIMEFRAMESPANEL_BORDER_TITLE)); // NOI18N
		timeframesPanel.setName("timeframesPanel"); // NOI18N

		timeframesLabel.setText(ReferenceCollection.CONFIG_TIMEFRAMESLABEL); // NOI18N
		timeframesLabel.setName("timeframesLabel"); // NOI18N

		timeframesField.setText(ReferenceCollection.CONFIG_TIMEFRAMES_VALUE); // NOI18N
		timeframesField.setName("timeframesField"); // NOI18N

		monitoringDaysLabel
				.setText(ReferenceCollection.CONFIG_MONITORINGDAYSLABEL); // NOI18N
		monitoringDaysLabel.setName("monitoringDaysLabel"); // NOI18N

		monitoringDaysMo.setText(ReferenceCollection.CONFIG_MONITORINGDAYSMO); // NOI18N
		monitoringDaysMo.setName("monitoringDaysMo"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE.equals("true"))
			monitoringDaysMo.setSelected(true);
		else
			monitoringDaysMo.setSelected(false);

		monitoringDaysTu.setText(ReferenceCollection.CONFIG_MONITORINGDAYSTU); // NOI18N
		monitoringDaysTu.setName("monitoringDaysTu"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE.equals("true"))
			monitoringDaysTu.setSelected(true);
		else
			monitoringDaysTu.setSelected(false);

		monitoringDaysWe.setText(ReferenceCollection.CONFIG_MONITORINGDAYSWE); // NOI18N
		monitoringDaysWe.setName("monitoringDaysWe"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE.equals("true"))
			monitoringDaysWe.setSelected(true);
		else
			monitoringDaysWe.setSelected(false);

		monitoringDaysTh.setText(ReferenceCollection.CONFIG_MONITORINGDAYSTH); // NOI18N
		monitoringDaysTh.setName("monitoringDaysTh"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE.equals("true"))
			monitoringDaysTh.setSelected(true);
		else
			monitoringDaysTh.setSelected(false);

		monitoringDaysFr.setText(ReferenceCollection.CONFIG_MONITORINGDAYSFR); // NOI18N
		monitoringDaysFr.setName("monitoringDaysFr"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE.equals("true"))
			monitoringDaysFr.setSelected(true);
		else
			monitoringDaysFr.setSelected(false);

		monitoringDaysSa.setText(ReferenceCollection.CONFIG_MONITORINGDAYSSA); // NOI18N
		monitoringDaysSa.setName("monitoringDaysSa"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE.equals("true"))
			monitoringDaysSa.setSelected(true);
		else
			monitoringDaysSa.setSelected(false);

		monitoringDaysSu.setText(ReferenceCollection.CONFIG_MONITORINGDAYSSU); // NOI18N
		monitoringDaysSu.setName("monitoringDaysSu"); // NOI18N
		if (ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE.equals("true"))
			monitoringDaysSu.setSelected(true);
		else
			monitoringDaysSu.setSelected(false);

		vacationLabel.setText(ReferenceCollection.CONFIG_VACATIONLABEL); // NOI18N
		vacationLabel.setName("vacationLabel"); // NOI18N

		vacationStartLabel
				.setText(ReferenceCollection.CONFIG_VACATIONSTARTLABEL); // NOI18N
		vacationStartLabel.setName("vacationStartLabel"); // NOI18N

		startJCalendar.setDateFormatString("dd.MMM.yyyy");
		startJCalendar.setName("startJCalendar"); // NOI18N
		try {
			startJCalendar
					.setDate(new Date(
							Long
									.parseLong(ReferenceCollection.CONFIG_VACATION_START_VALUE)));
		} catch (NumberFormatException e) {
			ReferenceCollection.LOG.debug("No Vacation End Date set");
		}

		vacationEndLabel.setText(ReferenceCollection.CONFIG_VACATIONENDLABEL); // NOI18N
		vacationEndLabel.setName("vacationEndLabel"); // NOI18N

		endJCalendar.setDateFormatString("dd.MMM.yyyy");
		endJCalendar.setName("endJCalendar"); // NOI18N
		try {
			endJCalendar.setDate(new Date(Long
					.parseLong(ReferenceCollection.CONFIG_VACATION_END_VALUE)));
		} catch (NumberFormatException e) {
			ReferenceCollection.LOG.debug("No Vacation End Date set");
		}

		javax.swing.GroupLayout timeframesPanelLayout = new javax.swing.GroupLayout(
				timeframesPanel);
		timeframesPanel.setLayout(timeframesPanelLayout);
		timeframesPanelLayout
				.setHorizontalGroup(timeframesPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								timeframesPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												timeframesPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																timeframesPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addComponent(
																				timeframesField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				137,
																				Short.MAX_VALUE))
														.addComponent(
																timeframesLabel)
														.addComponent(
																monitoringDaysLabel)
														.addGroup(
																timeframesPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				monitoringDaysMo)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				monitoringDaysTu)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				monitoringDaysWe))
														.addComponent(
																vacationLabel)
														.addGroup(
																timeframesPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addGroup(
																				timeframesPanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								startJCalendar,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								vacationStartLabel)
																						.addComponent(
																								vacationEndLabel)
																						.addComponent(
																								endJCalendar,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																timeframesPanelLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				timeframesPanelLayout
																						.createSequentialGroup()
																						.addComponent(
																								monitoringDaysSa)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								monitoringDaysSu))
																		.addGroup(
																				timeframesPanelLayout
																						.createSequentialGroup()
																						.addComponent(
																								monitoringDaysTh)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								monitoringDaysFr)
																						.addGap(
																								41,
																								41,
																								41))))
										.addContainerGap()));
		timeframesPanelLayout
				.setVerticalGroup(timeframesPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								timeframesPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(timeframesLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												timeframesField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(26, 26, 26)
										.addComponent(monitoringDaysLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												timeframesPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																monitoringDaysMo)
														.addComponent(
																monitoringDaysTu)
														.addComponent(
																monitoringDaysWe))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												timeframesPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																monitoringDaysTh)
														.addComponent(
																monitoringDaysFr))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												timeframesPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																monitoringDaysSa)
														.addComponent(
																monitoringDaysSu))
										.addGap(18, 18, 18)
										.addComponent(vacationLabel)
										.addGap(13, 13, 13)
										.addComponent(vacationStartLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												startJCalendar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(vacationEndLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												endJCalendar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(62, Short.MAX_VALUE)));

		feedsPanel
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder(ReferenceCollection.CONFIG_FEEDSPANEL_BORDER_TITLE)); // NOI18N
		feedsPanel.setName("feedsPanel");

		feedsScrollPane.setName("feedsScrollPane");

		loadTable();
		feedsTable.setName("feedsTable");
		feedsScrollPane.setViewportView(feedsTable);

		saveButton.setText(ReferenceCollection.CONFIG_SAVEBUTTON_TEXT);
		saveButton.setName("saveButton");

		cancelButton.setText(ReferenceCollection.CONFIG_CANCELBUTTON_TEXT);
		cancelButton.setName("cancelButton");

		addButton.setText(ReferenceCollection.CONFIG_ADDBUTTON_TEXT);
		addButton.setName("addButton");

		deleteButton.setText(ReferenceCollection.CONFIG_DELETEBUTTON_TEXT);
		deleteButton.setName("deleteButton");

		ConfigFrameActionListener configFrameActionListener = new ConfigFrameActionListener(
				this);
		cancelButton.addActionListener(configFrameActionListener);
		saveButton.addActionListener(configFrameActionListener);
		addButton.addActionListener(configFrameActionListener);
		deleteButton.addActionListener(configFrameActionListener);

		javax.swing.GroupLayout feedsPanelLayout = new javax.swing.GroupLayout(
				feedsPanel);
		feedsPanel.setLayout(feedsPanelLayout);
		feedsPanelLayout
				.setHorizontalGroup(feedsPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								feedsPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												feedsPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																feedsScrollPane,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																618,
																Short.MAX_VALUE)
														.addGroup(
																feedsPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				saveButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				cancelButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				380,
																				Short.MAX_VALUE)
																		.addComponent(
																				addButton)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				deleteButton)))
										.addContainerGap()));
		feedsPanelLayout
				.setVerticalGroup(feedsPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								feedsPanelLayout
										.createSequentialGroup()
										.addComponent(
												feedsScrollPane,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												233,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												feedsPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																saveButton)
														.addComponent(
																cancelButton)
														.addComponent(
																addButton)
														.addComponent(
																deleteButton))
										.addContainerGap(13, Short.MAX_VALUE)));

		mainConfigPanel
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder(ReferenceCollection.CONFIG_MAINCONFIGPANEL_BORDER_TITLE));
		mainConfigPanel.setName("mainConfigPanel");

		displayCountLabel.setText(ReferenceCollection.CONFIG_DISPLAYCOUNTLABEL);
		displayCountLabel.setName("displayCountLabel");

		displayCountField.setText("" + ReferenceCollection.DISPLAY_COUNT);
		displayCountField.setName("displayCountField");

		displayTimeLabel.setText(ReferenceCollection.CONFIG_DISPLAYTIMELABEL);
		displayTimeLabel.setName("displayTimeLabel");

		displayTimeField.setText("" + ReferenceCollection.DISPLAY_SECONDS);
		displayTimeField.setName("displayTimeField"); // NOI18N

		javax.swing.GroupLayout mainConfigPanelLayout = new javax.swing.GroupLayout(
				mainConfigPanel);
		mainConfigPanel.setLayout(mainConfigPanelLayout);

		mainConfigPanel.setLayout(mainConfigPanelLayout);
		mainConfigPanelLayout
				.setHorizontalGroup(mainConfigPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainConfigPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												mainConfigPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																displayCountLabel)
														.addGroup(
																mainConfigPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addComponent(
																				displayCountField,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				68,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(49, 49, 49)
										.addGroup(
												mainConfigPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																displayTimeLabel)
														.addGroup(
																mainConfigPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addComponent(
																				displayTimeField,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				68,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(37, 37, 37)
										.addGroup(
												mainConfigPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																mainConfigPanelLayout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addComponent(
																				languageSelectorComboBox,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																languageSelectorLabel))
										.addContainerGap(254, Short.MAX_VALUE)));
		mainConfigPanelLayout
				.setVerticalGroup(mainConfigPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainConfigPanelLayout
										.createSequentialGroup()
										.addGroup(
												mainConfigPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																displayCountLabel)
														.addComponent(
																displayTimeLabel)
														.addComponent(
																languageSelectorLabel))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												mainConfigPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																displayCountField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																displayTimeField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																languageSelectorComboBox,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(25, Short.MAX_VALUE)));

		javax.swing.GroupLayout configurationframeLayout = new javax.swing.GroupLayout(
				configurationframe);
		configurationframe.setLayout(configurationframeLayout);
		configurationframeLayout
				.setHorizontalGroup(configurationframeLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								configurationframeLayout
										.createSequentialGroup()
										.addGroup(
												configurationframeLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																mainConfigPanel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																feedsPanel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												timeframesPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		configurationframeLayout
				.setVerticalGroup(configurationframeLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								configurationframeLayout
										.createSequentialGroup()
										.addComponent(
												mainConfigPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												feedsPanel,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)).addComponent(
								timeframesPanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));

		this.getContentPane().add(configurationframe);

		this.pack();
		this.setBounds(
				(Toolkit.getDefaultToolkit().getScreenSize().width - this
						.getWidth()) / 2, (Toolkit.getDefaultToolkit()
						.getScreenSize().height
						- this.getHeight() - 50) / 2, this.getWidth(), this
						.getHeight() + 50);
		this.setVisible(true);
	}

	public javax.swing.JTextField getDisplayCountField() {
		return displayCountField;
	}

	public void setDisplayCountField(javax.swing.JTextField displayCountField) {
		this.displayCountField = displayCountField;
	}

	public javax.swing.JTextField getDisplayTimeField() {
		return displayTimeField;
	}

	public void setDisplayTimeField(javax.swing.JTextField displayTimeField) {
		this.displayTimeField = displayTimeField;
	}

	public javax.swing.JTable getFeedsTable() {
		return feedsTable;
	}

	public void setFeedsTable(javax.swing.JTable feedsTable) {
		this.feedsTable = feedsTable;
	}

	public JDateChooser getStartJCalendar() {
		return startJCalendar;
	}

	public void setStartJCalendar(JDateChooser startJCalendar) {
		this.startJCalendar = startJCalendar;
	}

	public JDateChooser getEndJCalendar() {
		return endJCalendar;
	}

	public void setEndJCalendar(JDateChooser endJCalendar) {
		this.endJCalendar = endJCalendar;
	}

	public javax.swing.JCheckBox getMonitoringDaysFr() {
		return monitoringDaysFr;
	}

	public void setMonitoringDaysFr(javax.swing.JCheckBox monitoringDaysFr) {
		this.monitoringDaysFr = monitoringDaysFr;
	}

	public javax.swing.JCheckBox getMonitoringDaysMo() {
		return monitoringDaysMo;
	}

	public void setMonitoringDaysMo(javax.swing.JCheckBox monitoringDaysMo) {
		this.monitoringDaysMo = monitoringDaysMo;
	}

	public javax.swing.JCheckBox getMonitoringDaysSa() {
		return monitoringDaysSa;
	}

	public void setMonitoringDaysSa(javax.swing.JCheckBox monitoringDaysSa) {
		this.monitoringDaysSa = monitoringDaysSa;
	}

	public javax.swing.JCheckBox getMonitoringDaysSu() {
		return monitoringDaysSu;
	}

	public void setMonitoringDaysSu(javax.swing.JCheckBox monitoringDaysSu) {
		this.monitoringDaysSu = monitoringDaysSu;
	}

	public javax.swing.JCheckBox getMonitoringDaysTh() {
		return monitoringDaysTh;
	}

	public void setMonitoringDaysTh(javax.swing.JCheckBox monitoringDaysTh) {
		this.monitoringDaysTh = monitoringDaysTh;
	}

	public javax.swing.JCheckBox getMonitoringDaysTu() {
		return monitoringDaysTu;
	}

	public void setMonitoringDaysTu(javax.swing.JCheckBox monitoringDaysTu) {
		this.monitoringDaysTu = monitoringDaysTu;
	}

	public javax.swing.JCheckBox getMonitoringDaysWe() {
		return monitoringDaysWe;
	}

	public void setMonitoringDaysWe(javax.swing.JCheckBox monitoringDaysWe) {
		this.monitoringDaysWe = monitoringDaysWe;
	}

	public javax.swing.JTextField getTimeframesField() {
		return timeframesField;
	}

	public void setTimeframesField(javax.swing.JTextField timeframesField) {
		this.timeframesField = timeframesField;
	}

	public javax.swing.JComboBox getLanguageSelectorComboBox() {
		return languageSelectorComboBox;
	}

	public void setLanguageSelectorComboBox(
			javax.swing.JComboBox languageSelectorComboBox) {
		this.languageSelectorComboBox = languageSelectorComboBox;
	}

	public void loadTable() {
		
		JTable feedsTable = new JTable();

		Session session = ReferenceCollection.SESSION_FACTORY.openSession();

		FeedDAO feedDAO = new FeedDAO();
		List<Feed> feedList = (List<Feed>) feedDAO.getFeeds(session);

		
		ReferenceCollection.FEED_TABLE = new FeedTable();

		for (Iterator<Feed> it = feedList.iterator(); it.hasNext();) {
			Feed current = (Feed) it.next();
			ReferenceCollection.FEED_TABLE.addRow(current);
		}
		
//		feedsTable.setModel(new javax.swing.table.DefaultTableModel(ReferenceCollection.FEED_TABLE.getTable(),
//				ReferenceCollection.CONFIG_TABLE_HEADER));
		
		FeedTableModel feedTableModel = new FeedTableModel();
		
		feedsTable.setModel(feedTableModel);

		this.setFeedsTable(feedsTable);

		session.close();
	}
	
	public void refreshTableWithoutDB(FeedTableModel feedTableModel){
		this.getFeedsTable().setModel(feedTableModel);
		this.getFeedsTable().repaint();
	}

}
