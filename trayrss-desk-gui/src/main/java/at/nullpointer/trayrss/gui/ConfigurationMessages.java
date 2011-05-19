package at.nullpointer.trayrss.gui;

import java.beans.Beans;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationMessages {
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	private ConfigurationMessages() {
		// do not instantiate
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Bundle access
	//
	////////////////////////////////////////////////////////////////////////////
	private static final String BUNDLE_NAME = "at.nullpointer.trayrss.gui.configurationmessages"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = loadBundle();
	public static String TrayRssConfigWindow_frmTrayrss_title;
	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME);
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Strings access
	//
	////////////////////////////////////////////////////////////////////////////
	public static String getString(String key, String defaultValue) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return defaultValue;
		}
	}
}
