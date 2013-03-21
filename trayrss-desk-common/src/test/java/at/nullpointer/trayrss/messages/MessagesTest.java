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

import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Class under test {@link Messages}
 * 
 * @author Thomas Pummer
 * 
 */
public class MessagesTest {

    /**
     * MessageResolver
     */
    private transient MessageResolver messageResolver;


    /**
     * set up the MessageResolver for the tests
     * 
     */
    @BeforeTest
    public void setUp() {

        messageResolver = new MessageResolverImpl( MessageResolverTestStrings.MESSAGE_LOCATION );
    }


    /**
     * Test {@link Messages#registerMessageResolver(String, MessageResolver)}
     */
    @Test( groups = { "unit" } )
    public void testRegisterMessageResolver() {

        try {
            Messages.registerMessageResolver( Messages.CONFIG, messageResolver );
        } catch ( Exception e ) { // NOPMD
            Assert.fail( "Register Message Resolver failed: " + e.getMessage() );
        }
    }


    /**
     * Test {@link Messages#getMessageResolver(String)}
     */
    @Test( groups = { "unit" } )
    public void testGetMessageResolver() {

        Messages.registerMessageResolver( Messages.CONFIG, messageResolver );
        final MessageResolver mrresolved = Messages.getMessageResolver( Messages.CONFIG );
        Assert.assertEquals( mrresolved, messageResolver, "Try to get messageResolver that was registerd first." );

    }


    /**
     * Test {@link Messages#setLanguage(String)}
     */
    @Test( groups = { "unit" } )
    public void testSetLanguage() {

        Messages.registerMessageResolver( Messages.CONFIG, messageResolver );
        Messages.setLanguage( Locale.GERMANY.getLanguage() );
        final MessageResolver retrievedMessageResollver = Messages.getMessageResolver( Messages.CONFIG );
        final String erg = retrievedMessageResollver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE );
        Assert.assertEquals( erg, MessageResolverTestStrings.MESSAGE_RESULT_GER,
                "Try to get the german String by setting the language in Messages." );
    }

}
