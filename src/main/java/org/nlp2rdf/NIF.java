package org.nlp2rdf;


import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFBean;

import java.util.List;

public interface NIF {

    Model getModel();

    String getNTriples();

    String getRDFxml();

    String getTurtle();

    String getTurtle(Model model);

    String getJSONLD(String context);

}
