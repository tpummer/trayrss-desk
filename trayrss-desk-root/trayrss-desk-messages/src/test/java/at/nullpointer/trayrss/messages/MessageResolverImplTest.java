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

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * Class under test {@link MessageResolverImpl}
 * 
 * @author Thomas Pummer
 * 
 */
public class MessageResolverImplTest {

    /**
     * MessageResolver
     */
    private static MessageResolver messageResolver;


    @Before
    public void setUp()
            throws Exception {

        messageResolver = new MessageResolverImpl( MessageResolverTestStrings.MESSAGE_LOCATION );
    }


    /**
     * Tests {@link MessageResolver#getString(String, String)}
     */
    @Test
    public void testGetString() {

        assertEquals( "Try to retrieve a message", MessageResolverTestStrings.MESSAGE_RESULT_ENG,
                messageResolver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                        MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ) );
    }


    /**
     * Tests fallback of {@link MessageResolver#getString(String, String)}
     */
    @Test
    public void testGetNonExistingString() {

        assertEquals( "Try to retrieve a message, that should not exist.",
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE, messageResolver.getString(
                        MessageResolverTestStrings.MESSAGE_TEST_ID_FAIL,
                        MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ) );
    }


    /**
     * Tests {@link MessageResolver#chanceLocale(Locale)}
     */
    @Test
    public void testChanceLocale() {

        messageResolver.chanceLocale( Locale.GERMANY );
        assertEquals( "Try to retrieve a localized message.", MessageResolverTestStrings.MESSAGE_RESULT_GER,
                messageResolver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                        MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ) );
    }


    /**
     * Determinates if a new {@link MessageResolverImpl} is affected by a locale change of an other
     * {@link MessageResolverImpl}
     */
    @Test
    public void testMultiMessageResolverImpl() {

        final MessageResolver messageResolverTwo = new MessageResolverImpl(
                "at.nullpointer.trayrss.messages.testmessages" );
        messageResolver.chanceLocale( Locale.GERMANY );
        final MessageResolver messageResolverThree = new MessageResolverImpl(
                "at.nullpointer.trayrss.messages.testmessages" );
        assertEquals( "Validate the localization change of the messageResolver.",
                MessageResolverTestStrings.MESSAGE_RESULT_GER, messageResolver.getString(
                        MessageResolverTestStrings.MESSAGE_TEST_ID, MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ) );
        assertEquals( "Validate that there was no change of the localization of messageResolverTwo.",
                MessageResolverTestStrings.MESSAGE_RESULT_ENG, messageResolverTwo.getString(
                        MessageResolverTestStrings.MESSAGE_TEST_ID, MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ) );
        assertEquals( "Validate that a new messageResolverThree has the initial localization.",
                MessageResolverTestStrings.MESSAGE_RESULT_ENG, messageResolverThree.getString(
                        MessageResolverTestStrings.MESSAGE_TEST_ID, MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ) );
    }

}
