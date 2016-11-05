package org.nlp2rdf;


public interface NIFFormat {

    void accept(NIFVisitor visitor);

    String TEMPLATE_ROOT = "./src/main/resources/templates/";
}
