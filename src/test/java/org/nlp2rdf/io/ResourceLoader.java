package org.nlp2rdf.io;


import java.net.URL;
import java.nio.file.Path;

public class ResourceLoader {

    protected String getContent(String path) throws Exception {
        URL url = this.getClass().getClassLoader().getResource(path);
        Path resPath = java.nio.file.Paths.get(url.toURI());
        return new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
    }

}
