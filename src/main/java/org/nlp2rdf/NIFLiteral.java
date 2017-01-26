package org.nlp2rdf;


import com.hp.hpl.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFBean;


public interface NIFLiteral extends NIFFormat {

    void add(Model model, NIFBean entity);

}
