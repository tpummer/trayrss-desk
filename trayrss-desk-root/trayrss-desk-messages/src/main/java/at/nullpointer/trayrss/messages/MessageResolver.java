/*
 * TrayRSS - simply notification of feed information (c) 2009-2011 TrayRSS Developement Team visit the project at
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

import java.util.Locale;

/**
 * offers access to localized messages
 * 
 * @author Thomas Pummer
 * 
 */
public interface MessageResolver {

    /**
     * Resolves a message
     * 
     * @param key Key of the message
     * @param defaultValue Value of the result, if key is not found
     * @return String of the message
     */
    public String getString( String key, String defaultValue );


    /**
     * Changes the locale of the resolver, to get an localized expression of the result
     * 
     * @param locale
     */
    public void chanceLocale( Locale locale );
}
