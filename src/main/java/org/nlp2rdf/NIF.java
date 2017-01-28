package org.nlp2rdf;


import com.hp.hpl.jena.rdf.model.Model;
import org.nlp2rdf.bean.NIFBean;

import java.util.List;

public interface NIF {

    Model getModel();

    String getNTriples();

    String getRDFxml();

    String getTurtle(List<NIFBean> beans);

    String getTurtle(List<NIFBean> beans, Model model);

    String getJSONLD(String context);

}
