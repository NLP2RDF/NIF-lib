package org.nlp2rdf.nif20.impl;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.ContextJSONLD;
import org.nlp2rdf.NIF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.nif20.NIF20Format;
import org.nlp2rdf.NIFFormat;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.formats.Conversor;
import org.nlp2rdf.validator.NIFMessagesException;

import java.util.List;
import java.util.Objects;


public class NIF20 extends Conversor implements NIF20Format, NIFMessagesException, NIF, ContextJSONLD {

    private NIFFormat[] elements;

    private List<NIFBean> beans;

    public NIF20() {
    }

    public NIF20(List<NIFBean> beans) {
        Objects.requireNonNull(beans, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_BEANS));
        this.beans = beans;
        this.elements = new NIFFormat[]{new NIF20Resource(), new NIF20Prefixes(), new NIF20Properties(), new NIF20Literal()};
    }

    public void accept(NIFVisitor visitor) {
        for (NIFFormat elem : elements) {
            elem.accept(visitor);
        }
    }

    public Model getModel() {

        NIFBean.validate(beans);
        NIFBean.fillBeansWithContext(beans, CONTEXT_FORMAT);

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

    public String getJSONLD(String context) {

        super.getNTriples(getModel());

        return super.getJSONLD(context, beans, TEMPLATE_NIF_PATH);
    }

    @Override
    public String getContextForJSONLD(List<String> ontologies) {
        return super.getContextForJSONLD(ontologies, TEMPLATE_CONTEXT_PATH);
    }


}
