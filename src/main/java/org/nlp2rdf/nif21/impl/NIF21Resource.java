package org.nlp2rdf.nif21.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIFResource;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.bean.NIFContext;
import org.nlp2rdf.nif21.NIF21Format;

public class NIF21Resource implements NIFResource, NIF21Format {

    public void add(Model model, NIFContext context) {
        if (model != null) {

            model.createResource(context.getCollection());

            model.createResource(context.getNIF21());
        }
    }

    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
