package org.nlp2rdf.nif20.impl;


import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import org.nlp2rdf.NIFLiteral;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.nif20.NIF20Format;

import static org.nlp2rdf.model.ModelMergeManager.removeDuplicatedValues;

public class NIF20Literal implements NIFLiteral, NIF20Format {

    public void add(Model model, NIFBean entity) {

        if (model != null && entity != null) {

            Resource contextRes = model.getResource(entity.getContext().getNIF20());

            if (entity.isContext()) {

                removeDuplicatedValues(model, contextRes, NIF_PROPERTY_ISSTRING, entity.getMention());
                removeDuplicatedValues(model, contextRes, NIF_PROPERTY_BEGININDEX, entity.getMention());
                removeDuplicatedValues(model, contextRes, NIF_PROPERTY_ENDINDEX, entity.getMention());

                model.add(contextRes, model.createProperty(NIF_PROPERTY_ISSTRING),
                        entity.getMention(), XSDDatatype.XSDstring);

                model.add(contextRes, model.createProperty(NIF_PROPERTY_BEGININDEX),
                        entity.getContext().getBeginIndex().toString(),
                        XSDDatatype.XSDnonNegativeInteger);

                model.add(contextRes, model.createProperty(NIF_PROPERTY_ENDINDEX),
                        entity.getContext().getEndIndex().toString(),
                        XSDDatatype.XSDnonNegativeInteger);

            } else if (entity.isMention()) {

                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_ANCHOR_OF),
                        entity.getMention());

                model.add(contextRes, model.createProperty(NIF_PROPERTY_BEGININDEX),
                        entity.getContext().getBeginIndex().toString(),
                        XSDDatatype.XSDnonNegativeInteger);

                model.add(contextRes, model.createProperty(NIF_PROPERTY_ENDINDEX),
                        entity.getContext().getEndIndex().toString(),
                        XSDDatatype.XSDnonNegativeInteger);

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
