package org.nlp2rdf.impl;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIF;
import org.nlp2rdf.NIF20Format;
import org.nlp2rdf.NIFFormat;
import org.nlp2rdf.NIFVisitor;

import java.util.List;


public class NIF20 extends Formats implements NIF20Format, NIF {

    private NIFFormat[] elements;

    private List<NIFBean> beans;

    public NIF20(List<NIFBean> beans) {
        this.beans = beans;
        this.elements = new NIFFormat[]{new NIF20Resource(), new NIF20Prefixes(), new NIF20Properties(), new NIF20Literal()};
    }

    public void accept(NIFVisitor visitor) {
        for (NIFFormat elem : elements) {
            elem.accept(visitor);
        }
    }

    public Model getModel() {

        NIF20Model model = new NIF20Model();

        NIFBean bean = beans.get(0);
        NIF20CreateContext nif20Context = new NIF20CreateContext(bean.getContext(), bean);


        nif20Context.setModel(model.create());
        NIFVisitor nifVisitor = nif20Context;

        accept(nifVisitor);

        for (int i = 1; i < beans.size(); i++) {
            nif20Context.setBean(beans.get(i));
            accept(nifVisitor);
        }

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
