package org.unileipzig.persistence.nif.impl;

import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.NIF20Format;
import org.unileipzig.persistence.nif.NIFResource;
import org.unileipzig.persistence.nif.NIFVisitor;

public class NIF20Resource implements NIFResource, NIF20Format {


    public void add(Model model, NIFContext context) {
        if (model != null && context != null) {
            model.createResource(context.context(CONTEXT_FORMAT));
        }
    }


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
