package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.nlp2rdf.NIFModel;
import org.nlp2rdf.NIFVisitor;

public class NIF21Model implements NIFModel {

    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }

    public Model create() {
        return ModelFactory.createDefaultModel();
    }
}
