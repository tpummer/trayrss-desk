package at.nullpointer.trayrss.service.xml.in;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
            throws IOException, JDOMException {

        List<Feed> result = null;

        if ( path == null ) {
            result = new ArrayList<>();
        } else {

            List<Element> xmlResult = this.toXMLConverterService.parseFile( new File( path ) );

            List<FeedElement> extractableXmlResult = this.feedElementFactory.createList( xmlResult );

            result = this.feedElementListConverter.convertToFeedList( extractableXmlResult );
        }

        return result;

    }

}
