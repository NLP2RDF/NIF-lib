package org.nlp2rdf;


import com.hp.hpl.jena.rdf.model.Model;

public interface NIFPrefixes extends NIFFormat {

    void add(Model model);

}
