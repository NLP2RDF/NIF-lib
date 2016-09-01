package org.nlp2rdf;


public interface NIFFormat {

    void accept(NIFVisitor visitor);

}
