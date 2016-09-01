package org.unileipzig.persistence.nif.impl;


import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.*;

public class NIF20CreateContext implements NIFVisitor {

    private Model model;

    private NIFContext context;

    private NIFBean entity;

    public NIF20CreateContext(NIFContext context, NIFBean entity) {
        this.context = context;
        this.entity = entity;
    }


    public Model getModel() {
        return model;
    }


    public void visit(NIFModel model) {
        this.model =model.create();
    }


    public void visit(NIFPrefixes prefixes) {
        prefixes.add(model);
    }


    public void visit(NIFResource resource) {
        resource.add(model, context);
    }


    public void visit(NIF21AnnotationUnit anotationUnit) {
    }


    public void visit(NIFProperties properties) {
        properties.add(model,  entity);
    }


    public void visit(NIFLiteral literal) {
        literal.add(model,  entity);
    }


}
