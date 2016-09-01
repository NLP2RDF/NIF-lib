package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;

public interface NIFModel extends NIFFormat {

    Model create();

}
