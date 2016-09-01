package org.nlp2rdf.impl;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIF;
import org.nlp2rdf.NIF20Format;
import org.nlp2rdf.NIFFormat;
import org.nlp2rdf.NIFVisitor;


public class NIF20 extends Formats implements NIF20Format, NIF {


    private NIFFormat[] elements;

    private NIFBean bean;

    public NIF20(NIFBean bean) {
        this.elements = new NIFFormat[]{new NIF20Model(), new NIF20Resource(), new NIF20Prefixes(), new NIF20Properties(), new NIF20Literal()};
        this.bean = bean;

    }

    public void accept(NIFVisitor visitor) {
        for (NIFFormat elem : elements) {
            elem.accept(visitor);
        }
    }

    public Model getModel() {
        NIFVisitor nifVisitor = new NIF20CreateContext(bean.getContext(), bean);
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
        return "";
    }


}
