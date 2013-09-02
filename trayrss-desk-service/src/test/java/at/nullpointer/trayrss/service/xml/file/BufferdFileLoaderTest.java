package at.nullpointer.trayrss.service.xml.file;

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

/**
 * Class under test {@link BufferdFileLoader}
 * 
 */
@ContextConfiguration
public class BufferdFileLoaderTest
        extends AbstractTestNGSpringContextTests {

    /**
     * Class under test
     */
    @Inject
    @Setter
    private BufferdFileLoader bufferdFileLoader;


    /**
     * Method under test {@link BufferdFileLoader#loadFile(String)}
     * 
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test( groups = { "integration" } )
    public void testImportFeedsFromXMLFileWitEmptyXml()
            throws IOException, URISyntaxException {

        String filename = "empty-file.xml";

        String fileResource = Paths.get( getClass().getResource( "/" + filename ).toURI() ).toString();

        List<String> list = this.bufferdFileLoader.loadFile( fileResource );

        Assert.assertEquals( list.size(), 0 );

    }

}
