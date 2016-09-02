package org.nlp2rdf.nif20.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.nif20.NIF20Format;
import org.nlp2rdf.NIFPrefixes;
import org.nlp2rdf.NIFVisitor;

public class NIF20Prefixes implements NIFPrefixes, NIF20Format {


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }


    public void add(Model model) {
        if (model != null) {
            model.setNsPrefix("nif", NIF_CORE_PREFIX);
            model.setNsPrefix("itsrdf", RDF_PREFIX);
            model.setNsPrefix("xsd", XML_PREFIX);
        }
    }
}
