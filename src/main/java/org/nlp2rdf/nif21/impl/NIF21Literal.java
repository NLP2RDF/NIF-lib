package org.nlp2rdf.nif21.impl;



import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.nlp2rdf.NIFLiteral;
import org.nlp2rdf.NIFVisitor;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.nif21.NIF21Format;

public class NIF21Literal implements NIFLiteral, NIF21Format {


    public void add(Model model, NIFBean entity) {

        if (model != null && entity != null) {

            Resource contextRes = model.getResource(entity.getContext().getNIF21());

            if (entity.isContext()) {

                contextRes.addLiteral(
                        model.getProperty(NIF_PROPERTY_ISSTRING),
                        entity.getMention());

                model.add(contextRes, model.createProperty(NIF_PROPERTY_BEGININDEX),
                        entity.getContext().getBeginIndex().toString(), XSDDatatype.XSDnonNegativeInteger);

                model.add(contextRes, model.createProperty(NIF_PROPERTY_ENDINDEX),
                        entity.getContext().getEndIndex().toString(), XSDDatatype.XSDnonNegativeInteger);

            } else if (entity.isMention()) {

                contextRes.addLiteral(
                        model.createProperty(NIF_PROPERTY_ANCHOROF),
                        entity.getMention());

                model.add(contextRes, model.createProperty(NIF_PROPERTY_BEGININDEX),
                        entity.getBeginIndex().toString(), XSDDatatype.XSDnonNegativeInteger);

                model.add(contextRes, model.createProperty(NIF_PROPERTY_ENDINDEX),
                        entity.getEndIndex().toString(), XSDDatatype.XSDnonNegativeInteger);


            }
        }

    }


    public void accept(NIFVisitor visitor) {
        visitor.visit(this);
    }
}
