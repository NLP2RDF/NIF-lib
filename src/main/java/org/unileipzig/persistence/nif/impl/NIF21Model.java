package org.unileipzig.persistence.nif.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.unileipzig.persistence.nif.NIFModel;
import org.unileipzig.persistence.nif.NIFVisitor;

public class NIF21Model implements NIFModel {

    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }

    public Model create() {
        return ModelFactory.createDefaultModel();
    }
}
