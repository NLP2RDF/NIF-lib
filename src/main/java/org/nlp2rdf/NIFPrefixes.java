package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;

public interface NIFPrefixes extends NIFFormat {

    void add(Model model);

}
