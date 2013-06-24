package at.nullpointer.trayrss.service.xml.in;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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
     */
    @Test( groups = { "integration" } )
    public void testImportFeedsFromXMLFileWithNullValue() {

        if ( takeoutXMLImport == null ) {
            Assert.fail();
        }

    }

}
