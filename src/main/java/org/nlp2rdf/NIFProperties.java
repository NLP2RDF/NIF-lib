package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.impl.NIFBean;


public interface NIFProperties extends NIFFormat {

    void add(Model model, NIFBean entity);
}
