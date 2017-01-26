package org.nlp2rdf;


import com.hp.hpl.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFContext;


public interface NIFResource extends NIFFormat {

    void add(Model model, NIFContext context);
}
