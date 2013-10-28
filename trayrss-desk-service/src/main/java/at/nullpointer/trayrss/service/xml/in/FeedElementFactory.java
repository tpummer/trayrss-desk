package at.nullpointer.trayrss.service.xml.in;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.springframework.stereotype.Component;

/**
 * Provides Methods to create an {@link FeedElement}
 * 
 * @author Thomas Pummer
 * @since 1.6
 */
@Component
public class FeedElementFactory {

    /**
     * Creates a List containing the given elements
     * 
     * @param elementList
     * @return List<FeedElement>
     * @throws IllegalArgumentException
     */
    public List<FeedElement> createList( final List<Element> elementList ) {

        List<FeedElement> result = new ArrayList<>();

        if ( elementList != null ) {

            if ( !elementList.isEmpty() ) {
                isElementListFeedList( elementList );
            }

            for ( Element element : elementList ) {
                FeedElement feedElement = createInstance( element );
                result.add( feedElement );
            }
        }

        return result;
    }


    /**
     * Creates an Instance of FeedElemnt containing the data of an Element
     * 
     * @param feedElement
     * @return FeedElement
     */
    public FeedElement createInstance( final Element feedElement ) {

        return new FeedElement( feedElement );
    }


    private void isElementListFeedList( final List<Element> elementList ) {

        Element element = elementList.get( 0 );
        if ( !element.getName().equals( "outline" ) ) {
            throw new IllegalArgumentException( "Elements not of type feed" );
        }

    }
}
