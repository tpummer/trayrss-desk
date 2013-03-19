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

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    private transient MessageResolver messageResolver;


    /**
     * Setup of the MessageResolver
     * 
     */
    @BeforeMethod
    public void setUp() {

        messageResolver = new MessageResolverImpl( MessageResolverTestStrings.MESSAGE_LOCATION );
    }


    /**
     * Tests {@link MessageResolver#getString(String, String)}
     */
    @Test( groups = { "unit" } )
    public void testGetString() {

        Assert.assertEquals( messageResolver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ), MessageResolverTestStrings.MESSAGE_RESULT_ENG,
                "Try to retrieve a message" );
    }


    /**
     * Tests fallback of {@link MessageResolver#getString(String, String)}
     */
    @Test( groups = { "unit" } )
    public void testGetNonExistingString() {

        Assert.assertEquals( messageResolver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID_FAIL,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ), MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE,
                "Try to retrieve a message, that should not exist." );
    }


    /**
     * Tests {@link MessageResolver#chanceLocale(Locale)}
     */
    @Test( groups = { "unit" } )
    public void testChanceLocale() {

        messageResolver.chanceLocale( Locale.GERMANY );
        Assert.assertEquals( messageResolver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ), MessageResolverTestStrings.MESSAGE_RESULT_GER,
                "Try to retrieve a localized message." );
    }


    /**
     * Determinates if a new {@link MessageResolverImpl} is affected by a locale change of an other
     * {@link MessageResolverImpl}
     */
    @Test( groups = { "unit" } )
    public void testMultiMessageResolverImpl() {

        final MessageResolver messageResolverTwo = new MessageResolverImpl(
                "at.nullpointer.trayrss.messages.testmessages" );
        messageResolver.chanceLocale( Locale.GERMANY );
        final MessageResolver messageResolverThree = new MessageResolverImpl(
                "at.nullpointer.trayrss.messages.testmessages" );
        Assert.assertEquals( messageResolver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ), MessageResolverTestStrings.MESSAGE_RESULT_GER,
                "Validate the localization change of the messageResolver." );
        Assert.assertEquals( messageResolverTwo.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ), MessageResolverTestStrings.MESSAGE_RESULT_ENG,
                "Validate that there was no change of the localization of messageResolverTwo." );
        Assert.assertEquals( messageResolverThree.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE ), MessageResolverTestStrings.MESSAGE_RESULT_ENG,
                "Validate that a new messageResolverThree has the initial localization." );
    }

}
