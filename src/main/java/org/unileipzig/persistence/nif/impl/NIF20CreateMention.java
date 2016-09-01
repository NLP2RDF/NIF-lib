package org.unileipzig.persistence.nif.impl;

import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.*;


public class NIF20CreateMention implements NIFVisitor {

    private Model model;

    private NIFBean entity;

    public NIF20CreateMention(NIFBean entity) {
        this.entity = entity;
    }


    public Model getModel() {
        return model;
    }


    public void visit(NIFModel model) {
        this.model = model.create();
    }


    public void visit(NIFPrefixes prefixes) {
    }


    public void visit(NIFResource resource) {
        resource.add(model, entity.getContext());
    }


    public void visit(NIF21AnnotationUnit anotationUnit) {
    }


    public void visit(NIFProperties properties) {
        properties.add(model, entity);

    }


    public void visit(NIFLiteral literal) {
        literal.add(model, entity);
    }
}
