package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIF21Format;
import org.nlp2rdf.NIFResource;
import org.nlp2rdf.NIFVisitor;

public class NIF21Resource implements NIFResource, NIF21Format {

    public void add(Model model, NIFContext context) {
        if (model != null) {

            model.createResource(context.getCollection());

            model.createResource(context.context(CONTEXT_FORMAT));
        }
    }

    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
