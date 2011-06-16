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
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FeedEditorInternalFrame extends JInternalFrame {
	private JTextField txtFeedName;
	private JTextField txtFeedUrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedEditorInternalFrame frame = new FeedEditorInternalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FeedEditorInternalFrame() {
		setTitle(ConfigurationMessages.getString("config.feedinfo.title", "TrayRSS - Feed Info")); //$NON-NLS-1$ //$NON-NLS-2$
		setBounds(100, 100, 580, 200);
		
		JPanel feedInfoPanel = new JPanel();
		getContentPane().add(feedInfoPanel, BorderLayout.CENTER);
		feedInfoPanel.setLayout(new BoxLayout(feedInfoPanel, BoxLayout.PAGE_AXIS));
		
		JPanel pnlPosOne = new JPanel();
		feedInfoPanel.add(pnlPosOne);
		
		JLabel lblFeedName = new JLabel(ConfigurationMessages.getString("config.feedinfo.feedName.label", "Feed Name")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlPosOne.add(lblFeedName);
		
		JPanel pnlPosTwo = new JPanel();
		feedInfoPanel.add(pnlPosTwo);
		
		txtFeedName = new JTextField();
		pnlPosTwo.add(txtFeedName);
		txtFeedName.setColumns(60);
		
		JPanel pnlPosThree = new JPanel();
		feedInfoPanel.add(pnlPosThree);
		
		JLabel lblFeedUrl = new JLabel(ConfigurationMessages.getString("config.feedinfo.feedUrl.label", "Feed URL")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlPosThree.add(lblFeedUrl);
		
		JPanel pnlPosFour = new JPanel();
		feedInfoPanel.add(pnlPosFour);
		
		txtFeedUrl = new JTextField();
		pnlPosFour.add(txtFeedUrl);
		txtFeedUrl.setColumns(60);
		
		JPanel pnlPosFive = new JPanel();
		feedInfoPanel.add(pnlPosFive);
		
		JLabel lblMonitorIntervall = new JLabel(ConfigurationMessages.getString("config.feedinfo.monitorIntervall.label", "Monitor Intervall")); //$NON-NLS-1$ //$NON-NLS-2$
		pnlPosFive.add(lblMonitorIntervall);
		
		JComboBox cbbMonitorIntervall = new JComboBox();
		cbbMonitorIntervall.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "5", "10", "15", "30", "60", "120", "240", "300"}));
		pnlPosFive.add(cbbMonitorIntervall);
		
		JPanel pnlPosSix = new JPanel();
		feedInfoPanel.add(pnlPosSix);
		
		JButton btnSaveFeedInfo = new JButton(ConfigurationMessages.getString("config.feedinfo.save.button.save", "Save")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSaveFeedInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlPosSix.add(btnSaveFeedInfo);
		
		JButton btnCancelFeedInfo = new JButton(ConfigurationMessages.getString("config.feedinfo.cancel.button", "Cancel")); //$NON-NLS-1$ //$NON-NLS-2$
		btnCancelFeedInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pnlPosSix.add(btnCancelFeedInfo);

	}

}
