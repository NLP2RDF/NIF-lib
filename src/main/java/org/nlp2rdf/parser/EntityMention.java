package org.nlp2rdf.parser;

/*
 * Milan Dojchinovski <milan.dojchinovski@fit.cvut.cz>
 * http://dojchinovski.mk
 */
public class EntityMention {
    private int beginIndex;
    private int endIndex;
    private String mention;
    private String context;
    private String referenceContext;

    /**
     * @return the beginIndex
     */
    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * @param beginIndex the beginIndex to set
     */
    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    /**
     * @return the endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * @param endIndex the endIndex to set
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * @return the mention text
     */
    public String getMention() {
        return mention;
    }

    /**
     * @param mention the mention text to set
     */
    public void setMention(String mention) {
        this.mention = mention;
    }

    public String getContext() {
        return context;
    }

    public String getReferenceContext() {
        return referenceContext;
    }

    public void setReferenceContext(String referenceContext) {
        this.referenceContext = referenceContext;
    }

    public void setContext(String context) {
        this.context = context;
    }


}
