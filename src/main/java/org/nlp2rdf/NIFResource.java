package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFContext;


public interface NIFResource extends NIFFormat {

    void add(Model model, NIFContext context);
}
