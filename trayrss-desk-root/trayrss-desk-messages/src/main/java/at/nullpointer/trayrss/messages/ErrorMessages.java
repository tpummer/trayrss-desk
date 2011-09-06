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

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorMessages implements MessageResolver {
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	private ErrorMessages() {
		// do not instantiate
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Bundle access
	//
	////////////////////////////////////////////////////////////////////////////
	private static final String BUNDLE_NAME = "at.nullpointer.trayrss.messages.errormessages"; //$NON-NLS-1$
	private static MessageResolver instance = null;
	private static Locale LOCALE = new Locale("en"); 
	private static ResourceBundle RESOURCE_BUNDLE = loadBundle();
	public static String TrayRssConfigWindow_frmTrayrss_title;
	public static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME, LOCALE);
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Strings access
	//
	////////////////////////////////////////////////////////////////////////////
	public String getString(String key, String defaultValue) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return defaultValue;
		}
	}
	
	public void chanceLocale(Locale locale){
		LOCALE = locale;
		RESOURCE_BUNDLE = loadBundle();
	}
	
	public static MessageResolver getInstance(){
		if(instance == null){
			instance = new ErrorMessages();
		}
		return instance;
	}
}
