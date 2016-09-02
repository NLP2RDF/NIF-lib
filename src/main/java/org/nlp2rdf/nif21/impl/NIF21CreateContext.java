package org.nlp2rdf.nif21.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.*;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFContext;

public class NIF21CreateContext implements NIFVisitor {

    private Model model;

    private NIFContext context;

    private NIFBean bean;

    public NIF21CreateContext(NIFContext context, NIFBean entity) {
        this.context = context;
        this.bean = entity;
    }


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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
        anotationUnit.add(model, bean);
    }

    public void visit(NIFProperties properties) {
        properties.add(model, bean);

    }

    public void visit(NIFLiteral literal) {
        literal.add(model, bean);
    }

    public void setBean(NIFBean bean) {
        this.bean = bean;
    }
}
