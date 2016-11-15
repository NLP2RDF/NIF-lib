package org.nlp2rdf.bean;



import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

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

        ontologies.forEach(ontology -> {
            model.read(ontology);
        });

       model.listStatements().forEachRemaining(s -> {
            if (PREDICATE_RANGE.equals(s.getPredicate().toString())) {
                types.put(s.getSubject().toString(), s.getObject().toString());
            }
        });

        model.listDatatypeProperties().forEachRemaining(data -> {
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types.getOrDefault(data.getURI(), null));
        });

        model.listTransitiveProperties().forEachRemaining(data -> {
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types.getOrDefault(data.getURI(), null));
        });

        model.listObjectProperties().forEachRemaining(data -> {
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types.getOrDefault(data.getURI(), null));
        });


        model.listClasses().forEachRemaining(data -> {
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types.getOrDefault(data.getURI(), null));
        });

        model.listAnnotationProperties().forEachRemaining(data -> {
            addToContext(data.getLocalName(), data.getURI(), data.getLabel(language), data.getComment(language), types.getOrDefault(data.getURI(), null));
        });



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
