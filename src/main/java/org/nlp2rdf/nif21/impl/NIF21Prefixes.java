package org.nlp2rdf.nif21.impl;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIFPrefixes;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.nif21.NIF21Format;

public class NIF21Prefixes implements NIFPrefixes, NIF21Format {


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
