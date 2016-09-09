package org.nlp2rdf.nif20.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.*;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFContext;
import org.nlp2rdf.nif21.impl.NIF21AnnotationUnit;

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
        return;
    }

    public void visit(NIFProperties properties) {
        properties.add(getModel(), bean);
    }

    public void visit(NIFLiteral literal) {
        literal.add(getModel(), bean);
    }
}
