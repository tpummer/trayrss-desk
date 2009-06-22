import java.awt.SplashScreen;

/**
 * Offers the management of the SplashScreen
 * 
 * @author thefake
 *
 */
public class TrayRSSSplashScreen {
	
	private SplashScreen splash;
	
	
	
	
	
	public TrayRSSSplashScreen() {
		super();
		this.splash = SplashScreen.getSplashScreen();
	}


	public void show(long seconds){
		if (splash.isVisible()) {
			try {
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {

			}
		}
	}
	
	public void close(){
		splash.close();
	}
}
