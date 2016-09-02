package org.nlp2rdf.nif21.impl;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.NIF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.nif21.NIF21Format;
import org.nlp2rdf.NIFFormat;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.formats.Conversor;

import java.util.List;


public class NIF21 extends Conversor implements NIF21Format, NIF {

    NIFFormat[] elements;

    private List<NIFBean> beans;

    public NIF21(List<NIFBean> beans) {
        this.elements = new NIFFormat[]{new NIF21Resource(), new NIF21Prefixes(), new NIF21Properties(), new NIF21Literal(), new NIF21AnnotationUnit()};
        this.beans = beans;
    }

    public Model getModel() {

        NIFBean.validate(beans);
        NIFBean.fillBeansWithContext(beans, CONTEXT_FORMAT);

        NIF21Model model = new NIF21Model();

        NIFBean bean = beans.get(0);
        NIF21CreateContext nif21Context = new NIF21CreateContext(bean.getContext(), bean);


        nif21Context.setModel(model.create());
        NIFVisitor nifVisitor = nif21Context;

        accept(nifVisitor);

        for (int i = 1; i < beans.size(); i++) {
            nif21Context.setBean(beans.get(i));
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

    public String getJSONLD(String context) {

        super.getNTriples(getModel());

        return super.getJSONLD(context, beans, TEMPLATE_FREME_PATH);
    }

    public void accept(NIFVisitor visitor) {
        for (NIFFormat elem : elements) {
            elem.accept(visitor);
        }
    }
}
