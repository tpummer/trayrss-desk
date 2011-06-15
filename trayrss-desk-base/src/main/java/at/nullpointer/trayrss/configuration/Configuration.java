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
package at.nullpointer.trayrss.configuration;


import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	final Properties props;
	
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
		
	}
	
	public void save(){
		props.setProperty("trayrss.lang", ReferenceCollection.LANGUAGE);
		props.setProperty("trayrss.displaycount", ""+ReferenceCollection.DISPLAY_COUNT);
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
			JOptionPane.showMessageDialog(null, "Config file not found", "Config file not found", JOptionPane.ERROR);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IO Error at saving", "IO Error at saving", JOptionPane.ERROR);
		}
	}
}
