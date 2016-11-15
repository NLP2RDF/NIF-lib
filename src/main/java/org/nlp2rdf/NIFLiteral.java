package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFBean;


public interface NIFLiteral extends NIFFormat {

    void add(Model model, NIFBean entity);

}
