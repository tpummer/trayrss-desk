package at.nullpointer.trayrss.service.xml.in;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import lombok.Setter;

import org.springframework.stereotype.Component;

import at.nullpointer.trayrss.domain.Feed;
import at.nullpointer.trayrss.service.xml.file.FileLoader;

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
     * FileLoader
     */
    @Inject
    @Setter
    private FileLoader fileLoader;


    @Override
    public List<Feed> importFeedsFromXmlFile( String path )
            throws IOException {

        List<String> loadedLines = Collections.emptyList();

        loadedLines = fileLoader.loadFile( path );

        parseXml( loadedLines );

        return null;

    }


    private void parseXml( List<String> loadedLines ) {

        // TODO Auto-generated method stub

    }

}
