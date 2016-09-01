package org.unileipzig.persistence.nif.impl;

import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.*;


public class NIF21CreateMention implements NIFVisitor {

    private Model model;

    private NIFContext context;

    private NIFBean entity;

    public NIF21CreateMention(NIFContext context, NIFBean entity) {
        this.context = context;
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
        resource.add(model, context);
    }


    public void visit(NIF21AnnotationUnit anotationUnit) {
        anotationUnit.add(model, entity);
    }


    public void visit(NIFProperties properties) {
        properties.add(model,  entity);

    }


    public void visit(NIFLiteral literal) {
        literal.add(model,  entity);
    }
}
