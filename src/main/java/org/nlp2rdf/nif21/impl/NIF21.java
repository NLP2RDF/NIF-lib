package org.nlp2rdf.nif21.impl;

import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.ContextJSONLD;
import org.nlp2rdf.NIF;
import org.nlp2rdf.NIFFormat;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.formats.Conversor;
import org.nlp2rdf.nif21.NIF21Format;
import org.nlp2rdf.parser.NIFParser;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.nlp2rdf.validator.NIFMessagesException.NIF_DATA_CONTEXT;
import static org.nlp2rdf.validator.NIFMessagesException.NIF_DATA_VALUE_NOT_NULL;


public class NIF21 extends Conversor implements NIF21Format, NIF, ContextJSONLD {

    private NIFFormat[] elements;

    private List<NIFBean> beans;

    private NIFParser parser;

    public NIF21() {
    }

    public NIF21(List<NIFBean> beans) {
        this.elements = new NIFFormat[]{new NIF21Resource(), new NIF21Prefixes(), new NIF21Properties(), new NIF21Literal(), new NIF21AnnotationUnit()};
        this.beans = beans;
    }

    public NIF21(List<NIFBean> beans, NIFParser parser) {
        this(beans);
        this.parser = parser;
    }

    public Model getModel() {

        NIFBean.validate(beans);
        NIFBean.fillBeansWithContext(beans, CONTEXT_FORMAT);

        NIF21Model model = new NIF21Model();

        NIFBean bean = beans.get(0);
        NIF21CreateContext nif21Context = new NIF21CreateContext(bean.getContext(), bean);


        nif21Context.setModel(model.create());
        NIFVisitor nifVisitor = nif21Context;

        if (parser != null) {
            parser.merge(nifVisitor.getModel());
        }

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

    public String getTurtle(List<NIFBean> beans) {
        return super.getTurtle(beans, getModel());
    }

    public String getJSONLD(String context) {

        Objects.requireNonNull(context, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_CONTEXT));

        super.getNTriples(getModel()); //Just to check if the model has no errors

        return super.getJSONLD(context, beans, TEMPLATE_NIF_PATH);
    }

    @Override
    public String getContextForJSONLD(Set<String> ontologies, String language) {
        return super.getContextForJSONLD(ontologies, TEMPLATE_CONTEXT_PATH, language);
    }

    @Override
    public String getContextForJSONLD(Set<String> ontologies, String template, String language) {
        return super.getContextForJSONLD(ontologies, template, language);
    }

    public void accept(NIFVisitor visitor) {
        for (NIFFormat elem : elements) {
            elem.accept(visitor);
        }
    }

    @Override
    public String getTurtle(List<NIFBean> beans, Model model) {
        return super.getTurtle(beans, getModel());
    }
}
