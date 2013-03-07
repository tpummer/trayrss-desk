/*
 * TrayRSS - simply notification of feed information (c) 2009-2013 TrayRSS Developement Team visit the project at
 * http://trayrss.nullpointer.at/
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package at.nullpointer.trayrss.messages;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Implementation of {@link MessageResolver}
 * 
 * @author Thomas Pummer
 */
public class MessageResolverImpl
        implements MessageResolver {

    /**
     * Bundle-Verweis
     */
    private String bundleName = "at.nullpointer.trayrss.messages.configurationmessages";
    /**
     * Locale der Anwendung
     */
    private Locale locale = Locale.ENGLISH;
    /**
     * ResourceBundle
     */
    private ResourceBundle resourceBundle;


    /**
     * Creates an {@link MessageResolverImpl} and loads the Resource Bundle with a default {@link Locale}: ENGLISH
     * 
     * @param bundleName
     */
    public MessageResolverImpl( String bundleName ) {

        this.bundleName = bundleName;
        this.resourceBundle = loadBundle();
        locale = Locale.ENGLISH;
    }


    private ResourceBundle loadBundle() {

        return ResourceBundle.getBundle( bundleName, locale );
    }


    public String getString( String key, String defaultValue ) {

        String result;

        try {
            ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : this.resourceBundle;
            result = bundle.getString( key );
        } catch ( MissingResourceException e ) {
            result = defaultValue;
        }

        return result;
    }


    public void chanceLocale( Locale locale ) {

        this.locale = locale;
        resourceBundle = loadBundle();
    }
}
