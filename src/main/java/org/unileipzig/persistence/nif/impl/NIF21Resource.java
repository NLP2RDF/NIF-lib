package org.unileipzig.persistence.nif.impl;


import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.NIF21Format;
import org.unileipzig.persistence.nif.NIFResource;
import org.unileipzig.persistence.nif.NIFVisitor;

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
