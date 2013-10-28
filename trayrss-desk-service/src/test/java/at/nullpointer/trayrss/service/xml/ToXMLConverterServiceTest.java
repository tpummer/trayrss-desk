package at.nullpointer.trayrss.service.xml;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.inject.Inject;

import lombok.Setter;

import org.jdom2.JDOMException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Class under test {@link ToXMLConverterService}
 * 
 */
@ContextConfiguration
public class ToXMLConverterServiceTest
        extends AbstractTestNGSpringContextTests {

    /**
     * Class under test
     */
    @Inject
    @Setter
    private ToXMLConverterService toXMLConverterService;


    /**
     * Method under test {@link ToXMLConverterService#parseFile(File)}
     * 
     * @throws JDOMException
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test( groups = { "integration" } )
    public void testImportFeedsFromXMLFileWithSingleLineXml()
            throws JDOMException, URISyntaxException, IOException {

        String filename = "example-one-line-takeout.xml";

        String fileResource = Paths.get( getClass().getResource( "/" + filename ).toURI() ).toString();

        this.toXMLConverterService.parseFile( new File( fileResource ) );

    }


    /**
     * Method under test {@link ToXMLConverterService#parseFile(File)}
     * 
     * @throws JDOMException
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test( groups = { "integration" } )
    public void testImportFeedsFromXMLFileWithMultiLineXml()
            throws JDOMException, URISyntaxException, IOException {

        String filename = "example-takeout.xml";

        String fileResource = Paths.get( getClass().getResource( "/" + filename ).toURI() ).toString();

        this.toXMLConverterService.parseFile( new File( fileResource ) );

    }

}
