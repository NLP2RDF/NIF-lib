package org.nlp2rdf.impl;


public class NIFContext {

    private String baseURI;

    private int beginIndex;

    private int endIndex;

    public NIFContext(String baseURI, int beginIndex, int endIndex) {
        this.baseURI = baseURI;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public String context(String format) {
        return String.format(format, baseURI, beginIndex, endIndex);
    }

    public String getCollection() {
        return String.format("%s#collection", baseURI);
    }

}
