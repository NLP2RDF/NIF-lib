package org.nlp2rdf.parser;

import java.util.List;


public class Document {
    private List<EntityMention> entities;
    private String text;

    public Document(List<EntityMention> entities, String text) {
        this.entities = entities;
        this.text = text;
    }

    public List<EntityMention> getEntities() {
        return entities;
    }

    public String getText() {
        return text;
    }

}
