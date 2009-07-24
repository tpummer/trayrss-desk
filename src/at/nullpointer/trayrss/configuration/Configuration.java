package at.nullpointer.trayrss.configuration;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import at.nullpointer.trayrss.gui.tray.ConfigFrameCaptions;

public class Configuration {
	Properties props;
	
	Configuration(Properties props){
		this.props = props;
	}
	
	public void load(){
		
		// main
		ReferenceCollection.LANGUAGE = props.getProperty("trayrss.lang");
		ReferenceCollection.DISPLAY_COUNT = Integer.parseInt(props.getProperty("trayrss.displaycount"));
		ReferenceCollection.DISPLAY_SECONDS = Integer.parseInt(props.getProperty("trayrss.displaysecond"));
		
		// time
		ReferenceCollection.CONFIG_TIMEFRAMES_VALUE = props.getProperty("trayrss.time.timeframe");
		ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE = props.getProperty("trayrss.time.mo");
		ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE = props.getProperty("trayrss.time.tu");
		ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE = props.getProperty("trayrss.time.we");
		ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE = props.getProperty("trayrss.time.th");
		ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE = props.getProperty("trayrss.time.fr");
		ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE = props.getProperty("trayrss.time.sa");
		ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE = props.getProperty("trayrss.time.so");
		ReferenceCollection.CONFIG_VACATION_START_VALUE = props.getProperty("trayrss.time.vacationstart");
		ReferenceCollection.CONFIG_VACATION_END_VALUE = props.getProperty("trayrss.time.vacationend");
		
		// Feeds
		//TODO
		
	}
	
	public void save(){
		props.setProperty("trayrss.lang", ReferenceCollection.LANGUAGE);
		props.setProperty("trayrss.displaycout", ""+ReferenceCollection.DISPLAY_COUNT);
		props.setProperty("trayrss.displaysecond", ""+ReferenceCollection.DISPLAY_SECONDS);
		// time
		props.setProperty("trayrss.time.timeframe", ReferenceCollection.CONFIG_TIMEFRAMES_VALUE);
		props.setProperty("trayrss.time.mo", ReferenceCollection.CONFIG_MONITORINGDAYSMO_VALUE);
		props.setProperty("trayrss.time.tu", ReferenceCollection.CONFIG_MONITORINGDAYSTU_VALUE);
		props.setProperty("trayrss.time.we", ReferenceCollection.CONFIG_MONITORINGDAYSWE_VALUE);
		props.setProperty("trayrss.time.th", ReferenceCollection.CONFIG_MONITORINGDAYSTH_VALUE);
		props.setProperty("trayrss.time.fr", ReferenceCollection.CONFIG_MONITORINGDAYSFR_VALUE);
		props.setProperty("trayrss.time.sa", ReferenceCollection.CONFIG_MONITORINGDAYSSA_VALUE);
		props.setProperty("trayrss.time.so", ReferenceCollection.CONFIG_MONITORINGDAYSSU_VALUE);
		props.setProperty("trayrss.time.vacationstart", ReferenceCollection.CONFIG_VACATION_START_VALUE);
		props.setProperty("trayrss.time.vacationend", ReferenceCollection.CONFIG_VACATION_END_VALUE);
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
