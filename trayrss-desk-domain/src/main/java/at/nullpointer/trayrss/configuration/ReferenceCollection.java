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
package at.nullpointer.trayrss.configuration;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Collection of all references
 * 
 * TODO remove
 * 
 * @author tpummer
 * @since 0.1
 * 
 */
public final class ReferenceCollection {

    private static ReferenceCollection instance;


    /**
     * Gives the singleton instance of the ConfigurationController
     * 
     * @return
     */
    public static synchronized ReferenceCollection getInstance() {

        if ( instance == null ) {
            instance = new ReferenceCollection();
        }
        return instance;
    }

    /**
     * Application Title to display TODO
     */
    @Setter
    @Getter
    private String trayrssAppTitle = "";

    /**
     * Context TODO
     */
    @Setter
    @Getter
    private ClassPathXmlApplicationContext context;


    private ReferenceCollection() {

    }

}
