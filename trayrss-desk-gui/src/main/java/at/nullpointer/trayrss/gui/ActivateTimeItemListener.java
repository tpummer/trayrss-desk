package at.nullpointer.trayrss.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ActivateTimeItemListener implements ItemListener {
	
	private TrayRssConfigWindow window;
	
	public ActivateTimeItemListener(TrayRssConfigWindow window){
		super();
		this.window = window;
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			this.window.setTimeEnabled(true);
		} else if(e.getStateChange() == ItemEvent.DESELECTED){
			this.window.setTimeEnabled(false);
		}

	}

}
