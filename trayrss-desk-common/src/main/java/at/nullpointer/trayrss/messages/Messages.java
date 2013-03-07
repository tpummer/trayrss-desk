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
package at.nullpointer.trayrss.messages;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Cache for {@link MessageResolver}
 * 
 * @author Thomas Pummer
 *
 */
public class Messages {
	
	private static Logger log = Logger.getLogger(Messages.class);
	
	private static final String MESSAGES_ERRORMESSAGES = "at.nullpointer.trayrss.messages.errormessages";
	private static final String MESSAGES_CONFIGURATIONMESSAGES = "at.nullpointer.trayrss.messages.configurationmessages";
	private static final String MESSAGES_NOTIFICATIONMESSAGES = "at.nullpointer.trayrss.messages.notificationmessages";
	private static final String MESSAGES_GUIMESSAGES = "at.nullpointer.trayrss.messages.guimessages";
	public static final String CONFIG = "config";
	public static final String ERROR = "error";
	public static final String NOTIFICATION = "notification";
	public static final String GUI = "gui";
	
	public static Map<String, MessageResolver> messageResolverMap = new HashMap<String, MessageResolver>();
	
	public static void registerMessageResolver(String name, MessageResolver messageResolver){
		messageResolverMap.put(name, messageResolver);
	}
	
	public static MessageResolver getMessageResolver(String name){
		return messageResolverMap.get(name);
	}

	public static void setLanguage(String language) {
		log.debug("changing language to: "+language);
		for(String key : messageResolverMap.keySet()){
			MessageResolver mr = messageResolverMap.get(key);
			mr.chanceLocale(new Locale(language));
		}
		
	}
	
	public static void setup(String language){
		registerMessageResolver(Messages.CONFIG, new MessageResolverImpl(MESSAGES_CONFIGURATIONMESSAGES));
		registerMessageResolver(Messages.ERROR, new MessageResolverImpl(MESSAGES_ERRORMESSAGES));
		registerMessageResolver(Messages.NOTIFICATION, new MessageResolverImpl(MESSAGES_NOTIFICATIONMESSAGES));
		registerMessageResolver(Messages.GUI, new MessageResolverImpl(MESSAGES_GUIMESSAGES));
		setLanguage(language);
		
	}
	

}
