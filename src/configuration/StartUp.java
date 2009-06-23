package configuration;

import java.io.IOException;

import org.apache.log4j.FileAppender;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class StartUp {
	Logger log;
	
	public StartUp(){
		startLogger();
	}
	
	private void startLogger(){
		log = Logger.getRootLogger();

		SimpleLayout layout = new SimpleLayout();
		ConsoleAppender consoleAppender = new ConsoleAppender(layout);
		log.addAppender(consoleAppender);
		FileAppender fileAppender;
		try {
			fileAppender = new FileAppender(layout,
					"logs/TrayRSS.log", false);
			log.addAppender(fileAppender);
			// ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
			log.setLevel(Level.WARN);
		} catch (IOException e) {
			System.err.println("Logdatei kann nicht geöffnet werden!");
		}
	}

}
