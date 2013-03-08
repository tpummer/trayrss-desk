package at.nullpointer.trayrss.gui.tray;

import at.nullpointer.trayrss.configuration.ChangeListener;

public class TrayIconChangeListener implements ChangeListener {

	@Override
	public void notifyChange() {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					//sleeps to let the change in the messageresolvers happen before load
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TrayIconPOJO trayIconPOJO = new TrayIconPOJO();
				trayIconPOJO.refreshTrayIcon();
				
			}
		};
		
		runnable.run();

	}

}
