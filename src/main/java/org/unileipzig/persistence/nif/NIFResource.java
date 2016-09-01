package org.unileipzig.persistence.nif;


import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.impl.NIFContext;


public interface NIFResource extends NIFFormat {

    void add(Model model, NIFContext context);
}
