package org.nlp2rdf.bean;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.TransitiveProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;

import java.util.*;

public class NIFJSONLDContext {


    private final String PREDICATE_RANGE = "http://www.w3.org/2000/01/rdf-schema#range";

    private boolean isNotBlank(String data) {
        return data != null && !data.isEmpty();
    }


    private Map<String, String> types;

    private Set<String> properties;

    private List<JSONLDContextBean> beans;

    public List<JSONLDContextBean> convertToBeans(Set<String> ontologies, String language) {

        OntModel model = ModelFactory.createOntologyModel();

        beans = new ArrayList<>();
        types = new HashMap<>();
        properties = new HashSet<>();

        for(String ontology: ontologies) {
            model.read(ontology);
        }

        Iterator stms = model.listStatements();

        while (stms.hasNext()) {
            Statement s = (Statement) stms.next();

            if (PREDICATE_RANGE.equals(s.getPredicate().toString())) {
                types.put(s.getSubject().toString(), s.getObject().toString());
            }
        }


        Iterator datatypeProperties = model.listDatatypeProperties();

        while (datatypeProperties.hasNext()) {
            DatatypeProperty data = (DatatypeProperty) datatypeProperties.next();
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types != null ? data.getURI(): null);
        }

        Iterator transitiveProperties =  model.listTransitiveProperties();

        while (transitiveProperties.hasNext()) {

            TransitiveProperty data = (TransitiveProperty) transitiveProperties.next();
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types != null ? data.getURI(): null);

        }

        Iterator objectProperties = model.listObjectProperties();

        while (objectProperties.hasNext()) {

            ObjectProperty data = (ObjectProperty) objectProperties.next();
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types != null ? data.getURI(): null);

        }

        Iterator classes = model.listClasses();

        while (classes.hasNext()) {
            OntClass data = (OntClass) classes.next();
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types != null ? data.getURI(): null);
        }


        Iterator annotationProperties = model.listAnnotationProperties();

        while (annotationProperties.hasNext()) {

            AnnotationProperty data = (AnnotationProperty) annotationProperties.next();
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types != null ? data.getURI(): null);
        }



        return beans;
    }

    private void addToContext(String localName, String uri, String label, String comment, String type) {
        if (isNotBlank(localName) && !properties.contains(localName)) {
            JSONLDContextBean bean = new JSONLDContextBean(localName, uri, label, comment, type);
            beans.add(bean);
            properties.add(localName);
        }
    }



    public class JSONLDContextBean {
        private String name;

        private String uri;

        private String label;

        private String comment;

        private String type;

        public JSONLDContextBean(String name, String uri, String label, String comment, String type) {
            setName(name);
            setUri(uri);
            setLabel(label);
            setComment(comment);
            setType(type);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {

            if (label == null) {
                this.label = label;
            } else {
                this.label = "";
            }
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            if (comment == null) {
                this.comment = comment;
            } else {
                this.comment = "";
            }
        }
    }

}
