package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.*;

public class NIF21CreateContext implements NIFVisitor {

    private Model model;

    private NIFContext context;

    private NIFBean entity;

    public NIF21CreateContext(NIFContext context, NIFBean entity) {
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
        prefixes.add(model);
    }


    public void visit(NIFResource resource) {
        resource.add(model, context);
    }


    public void visit(NIF21AnnotationUnit anotationUnit) {
        anotationUnit.add(model, entity);
    }


    public void visit(NIFProperties properties) {
        properties.add(model, entity);

    }


    public void visit(NIFLiteral literal) {
        literal.add(model, entity);
    }


}
