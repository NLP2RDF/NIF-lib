package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.nlp2rdf.NIFModel;

public class NIF20Model implements NIFModel {

    public Model create() {
        return ModelFactory.createDefaultModel();
    }
}
