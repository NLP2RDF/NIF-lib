package org.nlp2rdf.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.nlp2rdf.NIF20Format;
import org.nlp2rdf.NIFLiteral;
import org.nlp2rdf.NIFVisitor;

public class NIF20Literal implements NIFLiteral, NIF20Format {


    public void add(Model model, NIFBean entity) {

        if (model != null && entity != null) {

            Resource contextRes = model.getResource(entity.getContext().context(CONTEXT_FORMAT));

            if (entity.isContext()) {

                contextRes.addLiteral(
                        model.getProperty(NIF_PROPERTY_ISSTRING),
                        entity.getMention());
                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_BEGININDEX),
                        model.createTypedLiteral(entity.getBeginIndex()));
                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_ENDINDEX),
                        model.createTypedLiteral(entity.getEndIndex()));

            } else if (entity.isMention()) {

                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_ANCHOR_OF),
                        entity.getMention());
                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_BEGININDEX),
                        model.createTypedLiteral(new Integer(entity.getBeginIndex())));
                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_ENDINDEX),
                        model.createTypedLiteral(new Integer(entity.getEndIndex())));

                if (entity.hasTypes()) {
                    for (String ref : entity.getTypes()) {
                        contextRes.addProperty(
                                model.createProperty(RDF_PROPERTY_CLASS_REF),
                                model.createResource(ref));
                    }
                }

                if (entity.hasScore()) {
                    contextRes.addLiteral(
                            model.createProperty(RDF_PROPERTY_CONFIDENCE),
                            model.createTypedLiteral(entity.getScore()));
                }

            }
        }

    }


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
