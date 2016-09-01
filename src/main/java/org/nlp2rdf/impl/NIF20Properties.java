package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.nlp2rdf.NIF20Format;
import org.nlp2rdf.NIFProperties;
import org.nlp2rdf.NIFVisitor;

public class NIF20Properties implements NIFProperties, NIF20Format {


    public void add(Model model, NIFBean entity) {

        if (model != null && entity != null) {
            Resource contextRes = model.getResource(entity.getContext().context(CONTEXT_FORMAT));

            if (entity.isMention()) {
                fillMention(model, entity, contextRes);

            } else if (entity.isContext()) {

                fillContext(model, contextRes);
            }


        }
    }

    private void fillContext(Model model, Resource contextRes) {
        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_STRING));

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_CONTEXT));

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_RFC5147));
    }

    private void fillMention(Model model, NIFBean entity, Resource contextRes) {

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_WORD));

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_PHRASE));
        contextRes.addProperty(
                model.createProperty(NIF_PROPERTY_REFERENCE_CONTEXT),
                model.createResource(entity.getReferenceContext()));

        if (entity.hasTaIdentRef()) {
            contextRes.addProperty(
                    model.createProperty(RDF_PROPERTY_IDENTREF),
                    model.createResource(entity.getTaIdentRef()));
        }

        if (entity.hasTypes()) {
            for (String type : entity.getTypes()) {
                contextRes.addProperty(
                        model.createProperty(RDF_PROPERTY_CLASS_REF),
                        model.createResource(type));
            }
        }
        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_STRING));

        contextRes.addProperty(
                RDF.type,
                model.createResource(NIF_PROPERTY_RFC5147));
    }


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
