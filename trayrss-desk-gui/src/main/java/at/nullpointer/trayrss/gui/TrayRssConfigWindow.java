package at.nullpointer.trayrss.gui;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class TrayRssConfigWindow {

	private JFrame frmTrayrss;
	private JTextField txtDisplaycount;
	private JTextField txtDisplaytime;
	private JTextField txtTimeframes;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrayRssConfigWindow window = new TrayRssConfigWindow();
					window.frmTrayrss.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frmTrayrss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTrayrss.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel pnlGeneral = new JPanel();
		tabbedPane.addTab(ConfigurationMessages.getString("config.general.tab.title", "General"), new ImageIcon(TrayRssConfigWindow.class.getResource("/images/general.png")), pnlGeneral, null); //$NON-NLS-1$ //$NON-NLS-2$
		pnlGeneral.setLayout(new BoxLayout(pnlGeneral, BoxLayout.PAGE_AXIS));
		
		JPanel pnlDisplayCount = new JPanel();
		FlowLayout fl_pnlDisplayCount = new FlowLayout();
		pnlDisplayCount.setLayout(fl_pnlDisplayCount);
		pnlGeneral.add(pnlDisplayCount);
		
		JLabel lblDisplayCount = new JLabel(ConfigurationMessages.getString("config.general.displaycount.label", "Display Count")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlDisplayCount.add(lblDisplayCount);
		
		txtDisplaycount = new JTextField();
		pnlDisplayCount.add(txtDisplaycount);
		txtDisplaycount.setText("DisplayCount");
		txtDisplaycount.setColumns(10);
		
		JPanel pnlDisplayTime = new JPanel();
		pnlGeneral.add(pnlDisplayTime);
		
		JLabel lblDisplayTime = new JLabel(ConfigurationMessages.getString("config.general.displaytime.label", "Display Time")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlDisplayTime.add(lblDisplayTime);
		
		txtDisplaytime = new JTextField();
		txtDisplaytime.setToolTipText(ConfigurationMessages.getString("config.window.displaytime.tooltip", "units are seconds")); //$NON-NLS-1$ //$NON-NLS-2$
		txtDisplaytime.setText("DisplayTime");
		pnlDisplayTime.add(txtDisplaytime);
		txtDisplaytime.setColumns(10);
		
		JPanel pnlLanguage = new JPanel();
		pnlGeneral.add(pnlLanguage);
		
		pnlGeneral.add(Box.createVerticalGlue());
		
		JLabel lblLanguage = new JLabel(ConfigurationMessages.getString("config.general.language.label", "Language")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlLanguage.add(lblLanguage);
		
		JComboBox cbbLanguage = new JComboBox();
		cbbLanguage.setModel(new DefaultComboBoxModel(new String[] {"de", "en"}));
		pnlLanguage.add(cbbLanguage);
		
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
		pnlTimeFrame.setLayout(new BoxLayout(pnlTimeFrame, BoxLayout.PAGE_AXIS));
		
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
		
		JSpinner spinnerStart = new JSpinner();
		pnlTimeFrameVacation.add(spinnerStart);
		
		JLabel lblStart = new JLabel(ConfigurationMessages.getString("config.timeframes.vacation.start.label", "Start")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlTimeFrameVacation.add(lblStart);
		
		JSpinner spinnerEnd = new JSpinner();
		pnlTimeFrameVacation.add(spinnerEnd);
		
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

}
