package org.unileipzig.persistence.nif;


import org.apache.jena.rdf.model.Model;

public interface NIFModel extends NIFFormat {

    Model create();

}
