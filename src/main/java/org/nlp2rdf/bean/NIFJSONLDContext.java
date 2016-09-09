package org.nlp2rdf.bean;


import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import java.util.ArrayList;
import java.util.List;

public class NIFJSONLDContext {


    public List<NIFJSONLDContextBean> convertToBeans (List<String> ontologies) {

        OntModel model = ModelFactory.createOntologyModel();

        List<NIFJSONLDContextBean> beans = new ArrayList<>();

        ontologies.forEach(ontology -> {
            model.read(ontology);
        });

        model.listDatatypeProperties().forEachRemaining(data -> {
            NIFJSONLDContextBean bean = new NIFJSONLDContextBean(data.getLocalName(),data.getURI(), data.getLabel("en"),data.getComment("en"));
            beans.add(bean);
        });

        model.listObjectProperties().forEachRemaining(data -> {
            NIFJSONLDContextBean bean = new NIFJSONLDContextBean(data.getLocalName(),data.getURI(), data.getLabel("en"),data.getComment("en"));
            beans.add(bean);
        });


        model.listClasses().forEachRemaining(data -> {
            NIFJSONLDContextBean bean = new NIFJSONLDContextBean(data.getLocalName(),data.getURI(), data.getLabel("en"),data.getComment("en"));
            beans.add(bean);
        });


        model.listIndividuals().forEachRemaining(data -> {
            NIFJSONLDContextBean bean = new NIFJSONLDContextBean(data.getLocalName(),data.getURI(), data.getLabel("en"),data.getComment("en"));
            beans.add(bean);
        });


        model.listAnnotationProperties().forEachRemaining(data -> {
            NIFJSONLDContextBean bean = new NIFJSONLDContextBean(data.getLocalName(),data.getURI(), data.getLabel("en"),data.getComment("en"));
            beans.add(bean);
        });



        return beans;
    }

    public class NIFJSONLDContextBean {
        private String name;

        private String uri;

        private String label;

        private String comment;

        public NIFJSONLDContextBean (String name, String uri, String label, String comment) {
            setName(name);
            setUri(uri);
            setLabel(label);
            setComment(comment);
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
