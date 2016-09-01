package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.impl.NIFContext;


public interface NIFResource extends NIFFormat {

    void add(Model model, NIFContext context);
}
