package configuration;

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
		
	}
}
