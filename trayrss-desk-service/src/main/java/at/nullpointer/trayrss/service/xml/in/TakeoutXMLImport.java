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
package at.nullpointer.trayrss.service.xml.in;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;

import at.nullpointer.trayrss.domain.Feed;

/**
 * Offers functions for importing a google reader takeout file
 * 
 * @author Thomas Pummer
 * @since 1.6
 * 
 */
public interface TakeoutXMLImport {

    /**
     * Start the import process of a google takeout file
     * 
     * @param path
     * @return List of Feeds from XML
     * @throws IOException
     * @throws JDOMException
     */
    List<Feed> importFeedsFromXmlFile( String path )
            throws IOException, JDOMException;

}
