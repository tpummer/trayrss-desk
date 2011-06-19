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
package at.nullpointer.trayrss.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class TrayRssConfigWindow {

	private JFrame frmTrayrss;
	private JTextField txtDisplaycount;
	private JTextField txtDisplaytime;
	private JTextField txtTimeframes;
	private JTable table_1;

	/**
	 * Create the application.
	 */
	public TrayRssConfigWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrayrss = new JFrame();
		frmTrayrss.setIconImage(Toolkit.getDefaultToolkit().getImage(TrayRssConfigWindow.class.getResource("/images/rss-icon.png")));
		frmTrayrss.setTitle(ConfigurationMessages.getString("config.window.title", "TrayRSS - Configuration")); //$NON-NLS-1$ //$NON-NLS-2$
		frmTrayrss.setBounds(100, 100, 551, 473);
		frmTrayrss.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTrayrss.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlGeneral = new JPanel();
		tabbedPane.addTab(ConfigurationMessages.getString("config.general.tab.title", "General"), new ImageIcon(TrayRssConfigWindow.class.getResource("/images/general.png")), pnlGeneral, null);
		pnlGeneral.setLayout(new BoxLayout(pnlGeneral, BoxLayout.PAGE_AXIS));
		
		Component verticalGlueAbove = Box.createVerticalGlue();
		pnlGeneral.add(verticalGlueAbove);
		
		JPanel pnlDisplayCount = new JPanel();
		pnlGeneral.add(pnlDisplayCount);
		pnlDisplayCount.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDisplayCount = new JLabel(ConfigurationMessages.getString("config.general.displaycount.label", "Display Count"));
		pnlDisplayCount.add(lblDisplayCount);
		
		txtDisplaycount = new JTextField();
		pnlDisplayCount.add(txtDisplaycount);
		txtDisplaycount.setText("DisplayCount");
		txtDisplaycount.setColumns(10);
		
		JPanel pnlDisplayTime = new JPanel();
		pnlGeneral.add(pnlDisplayTime);
		pnlDisplayTime.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDisplayTime = new JLabel(ConfigurationMessages.getString("config.general.displaytime.label", "Display Time"));
		pnlDisplayTime.add(lblDisplayTime);
		
		txtDisplaytime = new JTextField();
		txtDisplaytime.setToolTipText(ConfigurationMessages.getString("config.window.displaytime.tooltip", "units are seconds")); //$NON-NLS-1$ //$NON-NLS-2$
		txtDisplaytime.setText("DisplayTime");
		pnlDisplayTime.add(txtDisplaytime);
		txtDisplaytime.setColumns(10);
		
		JPanel pnlLanguage = new JPanel();
		pnlGeneral.add(pnlLanguage);
		pnlLanguage.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblLanguage = new JLabel(ConfigurationMessages.getString("config.general.language.label", "Language"));
		pnlLanguage.add(lblLanguage);
		
		JComboBox cbbLanguage = new JComboBox();
		cbbLanguage.setModel(new DefaultComboBoxModel(new String[] {"de", "en"}));
		pnlLanguage.add(cbbLanguage);
		
		Component verticalGlueBelow = Box.createVerticalGlue();
		pnlGeneral.add(verticalGlueBelow);
		
		JPanel pnlFeed = new JPanel();
		tabbedPane.addTab("Feeds", new ImageIcon(TrayRssConfigWindow.class.getResource("/images/feed.png")), pnlFeed, null);
		pnlFeed.setLayout(new BoxLayout(pnlFeed, BoxLayout.PAGE_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		pnlFeed.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table_1.setFillsViewportHeight(true);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{new Long(1), "test", "test", new Integer(3), Boolean.TRUE},
			},
			new String[] {
					ConfigurationMessages.getString("config.feeds.table.column.id", "Id"),//"Id", 
					ConfigurationMessages.getString("config.feeds.table.column.feedname", "Feed Name"),//"Feed Name", 
					ConfigurationMessages.getString("config.feeds.table.column.feedurl", "Feed Url"),//"Feed Url", 
					ConfigurationMessages.getString("config.feeds.table.column.intervall", "Intervall"),//"Intervall", 
					ConfigurationMessages.getString("config.feeds.table.column.monitored", "Monitored")//"Monitored"
			}
		) {
			Class[] columnTypes = new Class[] {
				Long.class, String.class, String.class, Integer.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(table_1);
		
		JPanel pnlFeedActions = new JPanel();
		pnlFeed.add(pnlFeedActions);
		
		JButton btnAddFeed = new JButton(ConfigurationMessages.getString("config.feeds.button.addfeed", "Add Feed")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlFeedActions.add(btnAddFeed);
		
		JButton btnRemoveFeed = new JButton(ConfigurationMessages.getString("config.feeds.button.removefeed", "Remove Feed")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlFeedActions.add(btnRemoveFeed);
		
		JPanel pnlTimeFrame = new JPanel();
		tabbedPane.addTab("TimeFrames", new ImageIcon(TrayRssConfigWindow.class.getResource("/images/timeframe.png")), pnlTimeFrame, null);
		pnlTimeFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel pnlTimeFrameActive = new JPanel();
		FlowLayout fl_pnlTimeFrameActive = (FlowLayout) pnlTimeFrameActive.getLayout();
		fl_pnlTimeFrameActive.setAlignment(FlowLayout.LEFT);
		pnlTimeFrame.add(pnlTimeFrameActive);
		
		JCheckBox chckbxActivateTimeframes = new JCheckBox(ConfigurationMessages.getString("config.timeframes.toggle.label", "activate TimeFrames")); //$NON-NLS-1$ //$NON-NLS-2$
		chckbxActivateTimeframes.setHorizontalAlignment(SwingConstants.LEFT);
		pnlTimeFrameActive.add(chckbxActivateTimeframes);
		
		JPanel pnlTimeFrameOptions = new JPanel();
		pnlTimeFrameOptions.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		pnlTimeFrameOptions.setBorder(new TitledBorder(null, ConfigurationMessages.getString("config.timeframes.border.label", "Time Options"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimeFrame.add(pnlTimeFrameOptions);
		pnlTimeFrameOptions.setLayout(new BoxLayout(pnlTimeFrameOptions, BoxLayout.PAGE_AXIS));
		
		JPanel pnlTimeFrameDays = new JPanel();
		pnlTimeFrameOptions.add(pnlTimeFrameDays);
		
		JCheckBox chckbxMonday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.mo.label", "Monday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxMonday);
		
		JCheckBox chckbxTuesday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.tu.label", "Tuesday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxTuesday);
		
		JCheckBox chckbxWednesday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.we.label", "Wednesday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxWednesday);
		
		JCheckBox chckbxThursday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.th.label", "Thursday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxThursday);
		
		JCheckBox chckbxFriday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.fr.label", "Friday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxFriday);
		
		JCheckBox chckbxSaturday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.sa.label", "Saturday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxSaturday);
		
		JCheckBox chckbxSunday = new JCheckBox(ConfigurationMessages.getString("config.timeframes.checkbox.su.label", "Sunday")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameDays.add(chckbxSunday);
		
		JPanel pnlTimeFrameFrames = new JPanel();
		pnlTimeFrameOptions.add(pnlTimeFrameFrames);
		
		JLabel lblTimeframes = new JLabel(ConfigurationMessages.getString("config.timeframes.timeframes.label", "Timeframes")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameFrames.add(lblTimeframes);
		
		txtTimeframes = new JTextField();
		txtTimeframes.setText("Timeframes");
		pnlTimeFrameFrames.add(txtTimeframes);
		txtTimeframes.setColumns(10);
		
		JPanel pnlTimeFrameVacation = new JPanel();
		pnlTimeFrameOptions.add(pnlTimeFrameVacation);
		
		JLabel lblVacation = new JLabel(ConfigurationMessages.getString("config.timeframes.vacation.label", "Vacation")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameVacation.add(lblVacation);
		
		JDateChooser dacVacStart = new JDateChooser();
		dacVacStart.setDateFormatString("dd.MM.yyyy");
		pnlTimeFrameVacation.add(dacVacStart);
		
		JLabel lblStart = new JLabel(ConfigurationMessages.getString("config.timeframes.vacation.start.label", "Start")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameVacation.add(lblStart);
		
		JDateChooser dacVacEnd = new JDateChooser();
		dacVacEnd.setDateFormatString("dd.MM.yyyy");
		pnlTimeFrameVacation.add(dacVacEnd);
		
		JLabel lblEnd = new JLabel(ConfigurationMessages.getString("config.timeframes.vacation.end.label", "End")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameVacation.add(lblEnd);
		
		Component verticalGlue = Box.createVerticalGlue();
		pnlTimeFrameOptions.add(verticalGlue);
		
		JPanel saveExitPanel = new JPanel();
		frmTrayrss.getContentPane().add(saveExitPanel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton(ConfigurationMessages.getString("config.window.button.save", "Save")); //$NON-NLS-1$ //$NON-NLS-2$
		saveExitPanel.add(btnSave);
		
		JButton btnCancel = new JButton(ConfigurationMessages.getString("config.window.button.cancel", "Cancel")); //$NON-NLS-1$ //$NON-NLS-2$
		saveExitPanel.add(btnCancel);
	}
	
	public JFrame getJFrame(){
		return this.frmTrayrss;
	}

}
