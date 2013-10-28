package at.nullpointer.trayrss.service.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;

/**
 * @see ToXMLConverterService
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 * 
 */
@Component
public class DefaultToXMLConverterService
        implements ToXMLConverterService {

    @Override
    public List<Element> parseFile( final File file )
            throws JDOMException, IOException {

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build( file );

        Element rootNode = document.getRootElement();

        Element body = rootNode.getChild( "body" );

        // got all xml elements into a list
        List<Element> outlineList = body.getChildren( "outline" );

        return outlineList;

    }
}
