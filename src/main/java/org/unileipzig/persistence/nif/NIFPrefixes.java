package org.unileipzig.persistence.nif;


import org.apache.jena.rdf.model.Model;

public interface NIFPrefixes extends NIFFormat {

    void add(Model model);

}
