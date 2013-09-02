package at.nullpointer.trayrss.service.xml.in;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

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
     */
    @Test( groups = { "integration" }, expectedExceptions = { IllegalArgumentException.class } )
    public void testImportFeedsFromXMLFileWithNullValue()
            throws IOException {

        takeoutXMLImport.importFeedsFromXmlFile( null );

    }


    /**
     * Method under test {@link TakeoutXMLImport#importFeedsFromXmlFile(String)}
     * 
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test( groups = { "integration" }, enabled = true )
    public void testImportFeedsFromXMLFileWitEmptyXml()
            throws IOException, URISyntaxException {

        String filename = "empty-example-takeout.xml";

        String fileResource = Paths.get( getClass().getResource( "/" + filename ).toURI() ).toString();

        List<Feed> importFeedsFromXmlFile = takeoutXMLImport.importFeedsFromXmlFile( fileResource );

        Assert.assertEquals( importFeedsFromXmlFile.size(), 0 );

    }

}
