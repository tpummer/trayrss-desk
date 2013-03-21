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

/**
 * Sammlung der Teststrings für den MessageResolver passend zum Propertiesbundle
 * "at.nullpointer.trayrss.messages.testmessages"
 * 
 * @author Thomas Pummer
 * 
 */
public final class MessageResolverTestStrings {

    /**
     * Pfad zu den Testmessages
     */
    public static final String MESSAGE_LOCATION = "at.nullpointer.trayrss.messages.testmessages";
    /**
     * Mögliches Testergebnis Deutsch
     */
    public static final String MESSAGE_RESULT_GER = "test ger";
    /**
     * Mögliches Testergebnis Englisch
     */
    public static final String MESSAGE_RESULT_ENG = "test eng";
    /**
     * Defaulteingabe für Testabfrage
     */
    public static final String MESSAGE_DEFAULT_VALUE = "testname";
    /**
     * Key für erfolgreiche Testabfrage
     */
    public static final String MESSAGE_TEST_ID = "test";
    /**
     * Key für missglückende Testabfrage
     */
    public static final String MESSAGE_TEST_ID_FAIL = "testError";


    private MessageResolverTestStrings() {

        super();
    }

}
