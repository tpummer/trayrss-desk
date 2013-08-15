package at.nullpointer.trayrss.service.xml.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component( "fileLoader" )
public class BufferdFileLoader
        implements FileLoader {

    @Override
    public List<String> loadFile( String path )
            throws IOException {

        if ( path == null || path.length() == 0 ) {
            throw new IllegalArgumentException( "path should not be null" );
        }

        Path file = Paths.get( path );

        DosFileAttributes attrs = Files.readAttributes( file, DosFileAttributes.class );

        if ( !attrs.isRegularFile() ) {
            throw new IOException( "could not read file, invalid path" );
        }

        List<String> result = new ArrayList<String>();

        try (BufferedReader reader = Files.newBufferedReader( file, Charset.forName( "UTF-8" ) )) {
            String line = null;
            while ( ( line = reader.readLine() ) != null ) {
                result.add( line );
            }
        }

        return result;
    }
}
