package monitor;

import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;

public class IconChanger {
	
	public static final String NORMALICON = "./img/icons/rssTrayIcon.PNG";
	public static final String WARNICON = "./img/icons/rssTrayIconWarn.PNG";
	public static final String NEWICON = "./img/icons/rssTrayIconWarn.PNG";
	
	public static void setIcon(TrayIcon trayIcon, String icon){
		
		// Image image = Toolkit.getDefaultToolkit().getImage(
		// getClass().getResource("/img/rsstrayicon.PNG"));
		
		Image image = Toolkit.getDefaultToolkit().getImage(
				icon);
		
		trayIcon.setImage(image);
	}

	public static TrayIcon createTrayIcon(PopupMenu popup) {
		Image image = Toolkit.getDefaultToolkit().getImage(
				NORMALICON);
		return new TrayIcon(image, "RSSTRAY", popup);
	}

}
