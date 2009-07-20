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


import java.awt.Toolkit;

import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import configuration.ReferenceCollection;


public class ConfigFrame extends JFrame {
	
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel configurationframe;
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
    
    private ConfigFrameCaptions captions;

	public ConfigFrame() {
		
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
        deleteButton = new javax.swing.JButton();
        mainConfigPanel = new javax.swing.JPanel();
        displayCountLabel = new javax.swing.JLabel();
        displayCountField = new javax.swing.JTextField();
        displayTimeLabel = new javax.swing.JLabel();
        displayTimeField = new javax.swing.JTextField();

        configurationframe.setMinimumSize(new java.awt.Dimension(800, 400));
        configurationframe.setName("Configuration Frame"); // NOI18N

        timeframesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(ReferenceCollection.CONFIG_TIMEFRAMESPANEL_BORDER_TITLE)); // NOI18N
        timeframesPanel.setName("timeframesPanel"); // NOI18N

        timeframesLabel.setText(ReferenceCollection.CONFIG_TIMEFRAMESLABEL); // NOI18N
        timeframesLabel.setName("timeframesLabel"); // NOI18N

        timeframesField.setText(""); // NOI18N
        timeframesField.setName("timeframesField"); // NOI18N

        monitoringDaysLabel.setText(ReferenceCollection.CONFIG_MONITORINGDAYSLABEL); // NOI18N
        monitoringDaysLabel.setName("monitoringDaysLabel"); // NOI18N

        monitoringDaysMo.setText(ReferenceCollection.CONFIG_MONITORINGDAYSMO); // NOI18N
        monitoringDaysMo.setName("monitoringDaysMo"); // NOI18N

        monitoringDaysTu.setText(ReferenceCollection.CONFIG_MONITORINGDAYSTU); // NOI18N
        monitoringDaysTu.setName("monitoringDaysTu"); // NOI18N

        monitoringDaysWe.setText(ReferenceCollection.CONFIG_MONITORINGDAYSWE); // NOI18N
        monitoringDaysWe.setName("monitoringDaysWe"); // NOI18N

        monitoringDaysTh.setText(ReferenceCollection.CONFIG_MONITORINGDAYSTH); // NOI18N
        monitoringDaysTh.setName("monitoringDaysTh"); // NOI18N

        monitoringDaysFr.setText(ReferenceCollection.CONFIG_MONITORINGDAYSFR); // NOI18N
        monitoringDaysFr.setName("monitoringDaysFr"); // NOI18N

        monitoringDaysSa.setText(ReferenceCollection.CONFIG_MONITORINGDAYSSA); // NOI18N
        monitoringDaysSa.setName("monitoringDaysSa"); // NOI18N

        monitoringDaysSu.setText(ReferenceCollection.CONFIG_MONITORINGDAYSSU); // NOI18N
        monitoringDaysSu.setName("monitoringDaysSu"); // NOI18N

        vacationLabel.setText(ReferenceCollection.CONFIG_VACATIONLABEL); // NOI18N
        vacationLabel.setName("vacationLabel"); // NOI18N

        vacationStartLabel.setText(ReferenceCollection.CONFIG_VACATIONSTARTLABEL); // NOI18N
        vacationStartLabel.setName("vacationStartLabel"); // NOI18N

       // jTextField4.setText(""); // NOI18N
        startJCalendar.setDateFormatString("dd.MMM.yyyy");
        startJCalendar.setName("startJCalendar"); // NOI18N

        vacationEndLabel.setText(ReferenceCollection.CONFIG_VACATIONENDLABEL); // NOI18N
        vacationEndLabel.setName("vacationEndLabel"); // NOI18N

      //  jTextField5.setText(""); // NOI18N
        endJCalendar.setDateFormatString("dd.MMM.yyyy");
        endJCalendar.setName("endJCalendar"); // NOI18N

        javax.swing.GroupLayout timeframesPanelLayout = new javax.swing.GroupLayout(timeframesPanel);
        timeframesPanel.setLayout(timeframesPanelLayout);
        timeframesPanelLayout.setHorizontalGroup(
            timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeframesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timeframesPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(timeframesField, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                    .addComponent(timeframesLabel)
                    .addComponent(monitoringDaysLabel)
                    .addGroup(timeframesPanelLayout.createSequentialGroup()
                        .addComponent(monitoringDaysMo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monitoringDaysTu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(monitoringDaysWe))
                    .addComponent(vacationLabel)
                    .addGroup(timeframesPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startJCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vacationStartLabel)
                            .addComponent(vacationEndLabel)
                            .addComponent(endJCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, timeframesPanelLayout.createSequentialGroup()
                            .addComponent(monitoringDaysSa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(monitoringDaysSu))
                        .addGroup(timeframesPanelLayout.createSequentialGroup()
                            .addComponent(monitoringDaysTh)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(monitoringDaysFr)
                            .addGap(41, 41, 41))))
                .addContainerGap())
        );
        timeframesPanelLayout.setVerticalGroup(
            timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timeframesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeframesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timeframesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(monitoringDaysLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monitoringDaysMo)
                    .addComponent(monitoringDaysTu)
                    .addComponent(monitoringDaysWe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monitoringDaysTh)
                    .addComponent(monitoringDaysFr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(timeframesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monitoringDaysSa)
                    .addComponent(monitoringDaysSu))
                .addGap(18, 18, 18)
                .addComponent(vacationLabel)
                .addGap(13, 13, 13)
                .addComponent(vacationStartLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startJCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vacationEndLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endJCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        feedsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(ReferenceCollection.CONFIG_FEEDSPANEL_BORDER_TITLE)); // NOI18N
        feedsPanel.setName("feedsPanel"); // NOI18N

        feedsScrollPane.setName("feedsScrollPane"); // NOI18N

        feedsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            this.captions.getfeedsTableHeader()
        ));
        feedsTable.setName("feedsTable"); // NOI18N
        feedsScrollPane.setViewportView(feedsTable);

        saveButton.setText(ReferenceCollection.CONFIG_SAVEBUTTON_TEXT); // NOI18N
        saveButton.setName("saveButton"); // NOI18N

        cancelButton.setText(ReferenceCollection.CONFIG_CANCELBUTTON_TEXT); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        deleteButton.setText(ReferenceCollection.CONFIG_DELETEBUTTON_TEXT); // NOI18N
        deleteButton.setName("deleteButton"); // NOI18N

        javax.swing.GroupLayout feedsPanelLayout = new javax.swing.GroupLayout(feedsPanel);
        feedsPanel.setLayout(feedsPanelLayout);
        feedsPanelLayout.setHorizontalGroup(
            feedsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(feedsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(feedsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(feedsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(feedsPanelLayout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                        .addComponent(deleteButton)))
                .addContainerGap())
        );
        feedsPanelLayout.setVerticalGroup(
            feedsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(feedsPanelLayout.createSequentialGroup()
                .addComponent(feedsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(feedsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                    .addComponent(deleteButton))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        mainConfigPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(ReferenceCollection.CONFIG_MAINCONFIGPANEL_BORDER_TITLE)); // NOI18N
        mainConfigPanel.setName("mainConfigPanel"); // NOI18N

        displayCountLabel.setText(ReferenceCollection.CONFIG_DISPLAYCOUNTLABEL); // NOI18N
        displayCountLabel.setName("displayCountLabel"); // NOI18N

        displayCountField.setText(""); // NOI18N
        displayCountField.setName("displayCountField"); // NOI18N

        displayTimeLabel.setText(ReferenceCollection.CONFIG_DISPLAYTIMELABEL); // NOI18N
        displayTimeLabel.setName("displayTimeLabel"); // NOI18N

        displayTimeField.setName("displayTimeField"); // NOI18N

        javax.swing.GroupLayout mainConfigPanelLayout = new javax.swing.GroupLayout(mainConfigPanel);
        mainConfigPanel.setLayout(mainConfigPanelLayout);
        mainConfigPanelLayout.setHorizontalGroup(
            mainConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayCountLabel)
                    .addGroup(mainConfigPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(displayCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(mainConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayTimeLabel)
                    .addGroup(mainConfigPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(displayTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(392, Short.MAX_VALUE))
        );
        mainConfigPanelLayout.setVerticalGroup(
            mainConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainConfigPanelLayout.createSequentialGroup()
                .addGroup(mainConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayCountLabel)
                    .addComponent(displayTimeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout configurationframeLayout = new javax.swing.GroupLayout(configurationframe);
        configurationframe.setLayout(configurationframeLayout);
        configurationframeLayout.setHorizontalGroup(
            configurationframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configurationframeLayout.createSequentialGroup()
                .addGroup(configurationframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainConfigPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(feedsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeframesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        configurationframeLayout.setVerticalGroup(
            configurationframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configurationframeLayout.createSequentialGroup()
                .addComponent(mainConfigPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(timeframesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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

}
