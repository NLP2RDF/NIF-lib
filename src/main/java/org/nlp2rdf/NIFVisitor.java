package org.nlp2rdf;

import com.hp.hpl.jena.rdf.model.Model;
import org.nlp2rdf.nif21.impl.NIF21AnnotationUnit;


public interface NIFVisitor {

    Model getModel();

    void visit(NIFModel model);

    void visit(NIFPrefixes prefixes);

    void visit(NIFProperties properties);

    void visit(NIFLiteral literal);

    void visit(NIFResource resource);

    void visit(NIF21AnnotationUnit anotationUnit);
}
