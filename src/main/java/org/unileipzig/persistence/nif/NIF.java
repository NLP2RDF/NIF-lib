package org.unileipzig.persistence.nif;

import org.apache.jena.rdf.model.Model;

public interface NIF {

    Model getModel();

    String getNTriples();

    String getRDFxml();

    String getTurtle();
}
