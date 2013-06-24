package at.nullpointer.trayrss.service.xml.file;

import java.io.IOException;
import java.util.List;

public interface FileLoader {

    public List<String> loadFile( String path )
            throws IOException;

}
