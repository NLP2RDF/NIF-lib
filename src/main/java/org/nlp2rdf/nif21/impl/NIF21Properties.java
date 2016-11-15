package org.nlp2rdf.nif21.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.nlp2rdf.NIFProperties;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFContext;
import org.nlp2rdf.nif21.NIF21Format;

public class NIF21Properties implements NIFProperties, NIF21Format {


    public void add(Model model, NIFBean entity) {

        if (model != null && entity != null) {
            Resource contextRes = model.getResource(entity.getContext().getNIF21());

            if (entity.isMention()) {
                fillMention(model, entity, contextRes);
            } else if (entity.isContext()) {
                fillResourceCollection(model, entity.getContext());
                fillContext(model, contextRes);
            }
        }
    }


    private void fillResourceCollection(Model model, NIFContext context) {

        Resource resource = model.getResource(context.getCollection());

        resource.addProperty(RDF.type, model.createResource(NIF_PROPERTY_CONTEXT_COLLECTION));

        resource.addProperty(
                model.createProperty(NIF_PROPERTY_HAS_CONTEXT),
                model.createResource(context.getNIF21()));


        resource.addProperty(
                model.createProperty(NIF_PROPERTY_CONFORMS_TO),
                model.createResource(NIF_21));
    }

    private void fillContext(Model model, Resource contextRes) {


        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_CONTEXT));

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_OFFSETBASEDSTRING));

    }

    private void fillMention(Model model, NIFBean entity, Resource contextRes) {

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_OFFSETBASEDSTRING));

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_PHRASE));

        contextRes.addProperty(
                model.createProperty(NIF_PROPERTY_REFERENCE_CONTEXT),
                model.createResource(entity.getReferenceContext()));

    }


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
