package at.nullpointer.trayrss.service.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;

/**
 * Converts different input types to XML
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 */
public interface ToXMLConverterService {

    /**
     * parses a list of strings
     * 
     * @param file
     * @return List&lt;Element&gt;
     * @throws IOException
     * @throws JDOMException
     */
    List<Element> parseFile( File file )
            throws JDOMException, IOException;

}
