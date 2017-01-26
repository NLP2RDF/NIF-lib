package org.nlp2rdf;


import com.hp.hpl.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.nif21.NIF21Format;

public interface NIFAnnotationUnit extends NIF21Format {

    void add(Model model, NIFBean entity);

}
