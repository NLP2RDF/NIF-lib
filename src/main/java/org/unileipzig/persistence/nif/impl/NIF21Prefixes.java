package org.unileipzig.persistence.nif.impl;

import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.NIF21Format;
import org.unileipzig.persistence.nif.NIFPrefixes;
import org.unileipzig.persistence.nif.NIFVisitor;

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
