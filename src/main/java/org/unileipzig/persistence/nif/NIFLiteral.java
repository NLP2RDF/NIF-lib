package org.unileipzig.persistence.nif;


import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.impl.NIFContext;
import org.unileipzig.persistence.nif.impl.NIFBean;


public interface NIFLiteral extends NIFFormat {

    void add(Model model, NIFBean entity);

}
