package org.nlp2rdf.parser;

import org.junit.Test;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;
import org.nlp2rdf.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NIFParserTest extends ResourceLoader {

    @Test
    public void getPrefixesMustReturnPrefixesFromFile() throws Exception {

        //Init
        String text = getContent("example.nif");
        NIFParser parser = new NIFParser(text);


        //Act
        Map<String, String> map = parser.getPrefixes();

        //Assert
        assertEquals("http://creativecommons.org/ns#", map.get("cc"));
        assertEquals("https://term.tilde.com/terms/", map.get(""));
        assertEquals("http://rdfs.org/ns/void#", map.get("void"));
        assertEquals("http://www.w3.org/2001/XMLSchema#", map.get("xsd"));
        assertEquals("http://www.w3.org/2005/11/its", map.get("its"));
        assertEquals("http://www.w3.org/2005/11/its/rdf#", map.get("itsrdf"));
        assertEquals("http://www.w3.org/2004/02/skos/core#", map.get("skos"));
        assertEquals("http://www.w3.org/2000/01/rdf-schema#", map.get("rdfs"));
        assertEquals("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#", map.get("nif"));
        assertEquals("http://www.w3.org/ns/lemon/decomp#", map.get("decomp"));
        assertEquals("http://purl.org/dc/terms/", map.get("dct"));
        assertEquals("http://www.w3.org/1999/02/22-rdf-syntax-ns#", map.get("rdf"));
        assertEquals("http://www.w3.org/ns/lemon/ontolex#", map.get("ontolex"));
        assertEquals("http://purl.oclc.org/NET/ldr/ns#", map.get("ldr"));
        assertEquals("http://www.w3.org/ns/odrl/2/", map.get("odrl"));
        assertEquals("http://www.w3.org/ns/dcat#", map.get("dcat"));
        assertEquals("http://www.w3.org/ns/prov#", map.get("prov"));

    }

}
