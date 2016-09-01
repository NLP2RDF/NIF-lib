package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.*;

public class NIF20CreateContext implements NIFVisitor {

    private Model model;

    private NIFContext context;

    private NIFBean bean;

    public NIF20CreateContext(NIFContext context, NIFBean entity) {
        this.context = context;
        this.bean = entity;
    }

    public void setBean(NIFBean bean) {
        this.bean = bean;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void visit(NIFModel model) {
        this.setModel(model.create());
    }

    public void visit(NIFPrefixes prefixes) {
        prefixes.add(getModel());
    }

    public void visit(NIFResource resource) {
        resource.add(getModel(), context);
    }

    public void visit(NIF21AnnotationUnit anotationUnit) {
    }

    public void visit(NIFProperties properties) {
        properties.add(getModel(), bean);
    }

    public void visit(NIFLiteral literal) {
        literal.add(getModel(), bean);
    }
}
