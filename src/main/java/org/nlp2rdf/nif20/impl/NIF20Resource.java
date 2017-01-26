package org.nlp2rdf.nif20.impl;


import com.hp.hpl.jena.rdf.model.Model;
import org.nlp2rdf.NIFResource;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.bean.NIFContext;
import org.nlp2rdf.nif20.NIF20Format;

public class NIF20Resource implements NIFResource, NIF20Format {


    public void add(Model model, NIFContext context) {
        if (model != null && context != null) {
            model.createResource(context.getNIF20());
        }
    }


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
