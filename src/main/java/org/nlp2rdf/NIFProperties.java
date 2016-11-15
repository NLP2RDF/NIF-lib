package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFBean;


public interface NIFProperties extends NIFFormat {

    void add(Model model, NIFBean entity);
}
