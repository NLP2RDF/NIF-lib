package org.nlp2rdf;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.impl.NIFBean;

public interface NIFAnnotationUnit extends NIF21Format {

    void add(Model model, NIFBean entity);

}
