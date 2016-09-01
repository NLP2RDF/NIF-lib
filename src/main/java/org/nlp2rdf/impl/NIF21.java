package org.nlp2rdf.impl;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIF;
import org.nlp2rdf.NIF21Format;
import org.nlp2rdf.NIFFormat;
import org.nlp2rdf.NIFVisitor;


public class NIF21 extends Formats implements NIF21Format, NIF {

    NIFFormat[] elements;
    private NIFBean bean;

    public NIF21(NIFBean bean) {
        this.elements = new NIFFormat[]{new NIF21Model(), new NIF21Resource(), new NIF21Prefixes(), new NIF21Properties(), new NIF21Literal(), new NIF21AnnotationUnit()};
        this.bean = bean;
    }

    public Model getModel() {

        NIFVisitor nifVisitor = new NIF21CreateContext(bean.getContext(), bean);
        accept(nifVisitor);

        return nifVisitor.getModel();
    }

    public String getNTriples() {
        return super.getNTriples(getModel());
    }

    public String getRDFxml() {
        return super.getRDFxml(getModel());
    }

    public String getTurtle() {
        return super.getTurtle(getModel());
    }

    public String getJSONLD() {
        return null;
    }

    public void accept(NIFVisitor visitor) {
        for (NIFFormat elem : elements) {
            elem.accept(visitor);
        }
    }
}
