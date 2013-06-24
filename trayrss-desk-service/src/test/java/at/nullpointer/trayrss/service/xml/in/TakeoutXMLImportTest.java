package at.nullpointer.trayrss.service.xml.in;

import java.io.IOException;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

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

}
