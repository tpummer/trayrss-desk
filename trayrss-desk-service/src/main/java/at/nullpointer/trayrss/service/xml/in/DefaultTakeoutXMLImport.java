package at.nullpointer.trayrss.service.xml.in;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.service.xml.ToXMLConverterService;

/**
 * see {@link TakeoutXMLImport}
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 */
@Component
public class DefaultTakeoutXMLImport
        implements TakeoutXMLImport {

    /**
     * ToXMLConverterService
     */
    @Inject
    @Setter
    private ToXMLConverterService toXMLConverterService;

    /**
     * {@link FeedElementFactory}
     */
    @Inject
    @Setter
    private FeedElementFactory feedElementFactory;

    /**
     * {@link FeedElementListConverter}
     */
    @Inject
    @Setter
    private FeedElementListConverter feedElementListConverter;


    @Override
    public List<Feed> importFeedsFromXmlFile( String path )
            throws IOException, JDOMException, NullPointerException {

        if ( path == null ) {
            throw new NullPointerException( "null path" );
        }

        List<Element> xmlResult = this.toXMLConverterService.parseFile( new File( path ) );

        List<FeedElement> extractableXmlResult = feedElementFactory.createList( xmlResult );

        List<Feed> result = feedElementListConverter.convertToFeedList( extractableXmlResult );

        return result;

    }

}
