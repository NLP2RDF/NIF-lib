package org.unileipzig.persistence.nif.impl;

import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.NIF;
import org.unileipzig.persistence.nif.NIF20Format;
import org.unileipzig.persistence.nif.NIFFormat;
import org.unileipzig.persistence.nif.NIFVisitor;


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


}
