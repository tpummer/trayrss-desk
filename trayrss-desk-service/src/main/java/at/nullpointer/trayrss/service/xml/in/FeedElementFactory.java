package at.nullpointer.trayrss.service.xml.in;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.springframework.stereotype.Component;

@Component
public class FeedElementFactory {

    public List<FeedElement> createList( List<Element> elementList )
            throws IllegalArgumentException {

        List<FeedElement> result = new ArrayList<>();

        if ( elementList != null ) {

            if ( elementList.size() > 0 ) {
                isElementListFeedList( elementList );
            }

            for ( Element element : elementList ) {
                FeedElement feedElement = new FeedElement( element );
                result.add( feedElement );
            }
        }

        return result;
    }


    private void isElementListFeedList( List<Element> elementList ) {

        Element element = elementList.get( 0 );
        if ( !element.getName().equals( "outline" ) ) {
            throw new IllegalArgumentException( "Elements not of type feed" );
        }

    }
}
