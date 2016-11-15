package org.nlp2rdf.nif20.impl;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.nlp2rdf.NIFModel;

public class NIF20Model implements NIFModel {

    public Model create() {
        return ModelFactory.createDefaultModel();
    }
}
