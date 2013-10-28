package at.nullpointer.trayrss.service.xml.in;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.jdom2.JDOMException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import at.nullpointer.trayrss.domain.Feed;

/**
 * Class under test {@link TakeoutXMLImport}
 * 
 * @author Thomas Pummer
 * 
 * @since 1.6
 * 
 */
@ContextConfiguration
public class TakeoutXMLImportTest
        extends AbstractTestNGSpringContextTests {

    /**
     * Class under test
     */
    @Inject
    @Setter
    private TakeoutXMLImport takeoutXMLImport;


    /**
     * Method under test {@link TakeoutXMLImport#importFeedsFromXmlFile(String)}
     * 
     * @throws IOException
     * @throws JDOMException
     */
    @Test( groups = { "unit" } )
    public void testImportFeedsFromXMLFileWithNullValue()
            throws IOException, JDOMException {

        List<Feed> result = this.takeoutXMLImport.importFeedsFromXmlFile( null );

        Assert.assertEquals( result.size(), 0 );

    }


    /**
     * Method under test {@link TakeoutXMLImport#importFeedsFromXmlFile(String)}
     * 
     * @throws IOException
     * @throws URISyntaxException
     * @throws JDOMException
     */
    @Test( groups = { "integration" } )
    public void testImportFeedsFromXMLFileWithEmptyXml()
            throws IOException, URISyntaxException, JDOMException {

        String filename = "empty-example-takeout.xml";

        String fileResource = Paths.get( getClass().getResource( "/" + filename ).toURI() ).toString();

        List<Feed> result = this.takeoutXMLImport.importFeedsFromXmlFile( fileResource );

        Assert.assertEquals( result.size(), 0 );

    }


    /**
     * Method under test {@link TakeoutXMLImport#importFeedsFromXmlFile(String)}
     * 
     * @throws IOException
     * @throws URISyntaxException
     * @throws JDOMException
     */
    @Test( groups = { "integration" }, enabled = false )
    public void testImportFeedsFromXMLFile()
            throws IOException, URISyntaxException, JDOMException {

        String filename = "example-takeout.xml";

        String fileResource = Paths.get( getClass().getResource( "/" + filename ).toURI() ).toString();

        List<Feed> result = this.takeoutXMLImport.importFeedsFromXmlFile( fileResource );

        Assert.assertEquals( result.size(), 4 );

    }

}
