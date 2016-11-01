package org.nlp2rdf.model;


import org.apache.jena.rdf.model.*;

public class ModelMergeManager {

    public static void removeDuplicatedValues(Model model, Resource resource, String propertyName, String mention) {

        Property property = model.createProperty(propertyName);
        NodeIterator iterator = model.listObjectsOfProperty(property);

        while (iterator.hasNext()) {
            RDFNode node = iterator.next();
            if (node.toString().contains(mention)) {
                model.removeAll(resource, property, node);
            }
        }
    }

}
