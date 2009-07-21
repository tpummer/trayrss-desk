package configuration;

import gui.tray.ConfigFrameCaptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {
	Properties props;
	
	Configuration(Properties props){
		this.props = props;
	}
	
	public void load(){
		ReferenceCollection.LANGUAGE = props.getProperty("trayrss.lang");
		ReferenceCollection.DISPLAY_COUNT = Integer.parseInt(props.getProperty("trayrss.displaycount"));
		ReferenceCollection.DISPLAY_SECONDS = Integer.parseInt(props.getProperty("trayrss.displaysecond"));
		
	}
	
	public void save(){
		props.setProperty("trayrss.lang", ReferenceCollection.LANGUAGE);
		props.setProperty("trayrss.displaycout", ""+ReferenceCollection.DISPLAY_COUNT);
		props.setProperty("trayrss.displaysecond", ""+ReferenceCollection.DISPLAY_SECONDS);
		try {
			props.storeToXML(new FileOutputStream(ReferenceCollection.CONFIG),"TrayRSS Configuration");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
