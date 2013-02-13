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
import static org.junit.Assert.fail;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

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
    private static MessageResolver messageResolver;


    @Before
    protected void setUp()
            throws Exception {

        messageResolver = new MessageResolverImpl( MessageResolverTestStrings.MESSAGE_LOCATION );
    }


    /**
     * Test {@link Messages#registerMessageResolver(String, MessageResolver)}
     */
    @Test
    public void testRegisterMessageResolver() {

        try {
            Messages.registerMessageResolver( Messages.CONFIG, messageResolver );
        } catch ( Throwable t ) {
            fail( "Register Message Resolver failed: " + t.getMessage() );
        }
    }


    /**
     * Test {@link Messages#getMessageResolver(String)}
     */
    @Test
    public void testGetMessageResolver() {

        Messages.registerMessageResolver( Messages.CONFIG, messageResolver );
        final MessageResolver mrresolved = Messages.getMessageResolver( Messages.CONFIG );
        assertEquals( "Try to get messageResolver that was registerd first.", messageResolver, mrresolved );

    }


    /**
     * Test {@link Messages#setLanguage(String)}
     */
    @Test
    public void testSetLanguage() {

        Messages.registerMessageResolver( Messages.CONFIG, messageResolver );
        Messages.setLanguage( Locale.GERMANY.getLanguage() );
        final MessageResolver retrievedMessageResollver = Messages.getMessageResolver( Messages.CONFIG );
        final String erg = retrievedMessageResollver.getString( MessageResolverTestStrings.MESSAGE_TEST_ID,
                MessageResolverTestStrings.MESSAGE_DEFAULT_VALUE );
        assertEquals( "Try to get the german String by setting the language in Messages.",
                MessageResolverTestStrings.MESSAGE_RESULT_GER, erg );
    }

}
