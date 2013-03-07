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
