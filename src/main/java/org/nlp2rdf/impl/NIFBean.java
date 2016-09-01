package org.nlp2rdf.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NIFBean {

    private NIFContext context;

    private String annotator;

    private String mention;

    private Integer beginIndex;

    private Integer endIndex;

    private NIFType nifType = NIFType.ENTITY;
    ;

    private List<String> types;

    private Double score;

    private String taIdentRef;

    private String referenceContext;

    private Map<String, String> entityTypes = new HashMap<String, String>(8);

    public NIFBean(List<NIFBeanBuilder> builders) {

        List<NIFBean> nifBeans = new ArrayList<NIFBean>();

        builders.forEach(builder -> nifBeans.add(new NIFBean(builder)));
    }

    public NIFBean(NIFBeanBuilder builder) {
        init();
        this.context = builder.context;
        this.mention = builder.mention;
        this.beginIndex = builder.beginIndex;
        this.endIndex = builder.endIndex;
        this.nifType = builder.nifType;
        this.types = builder.types;
        this.score = builder.score;
        this.taIdentRef = builder.taIdentRef;
        this.referenceContext = builder.referenceContext;
        this.annotator = builder.annotator;
    }

    public Double getScore() {
        return score;
    }

    private void init() {
        entityTypes.put("PERSON", "http://nerd.eurecom.fr/ontology#Person");
        entityTypes.put("ORGANIZATION", "http://nerd.eurecom.fr/ontology#Organization");
        entityTypes.put("LOCATION", "http://nerd.eurecom.fr/ontology#Location");
        entityTypes.put("MISC", "http://www.w3.org/2002/07/owl#Thing");
        entityTypes.put("I-PER", "http://nerd.eurecom.fr/ontology#Person");
        entityTypes.put("I-ORG", "http://nerd.eurecom.fr/ontology#Organization");
        entityTypes.put("I-LOC", "http://nerd.eurecom.fr/ontology#Location");
        entityTypes.put("I-MISC", "http://www.w3.org/2002/07/owl#Thing");
    }

    public Boolean hasTypes() {
        return types != null && !types.isEmpty();
    }

    public Boolean hasTaIdentRef() {
        return taIdentRef != null;
    }

    public Boolean hasScore() {
        return score != null;
    }

    public String getMention() {
        return mention;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public Boolean isMention() {
        return NIFType.ENTITY.equals(nifType);
    }

    public Boolean isContext() {
        return NIFType.CONTEXT.equals(nifType);
    }

    public NIFType getNifType() {
        return nifType;
    }

    public Map<String, String> getEntityTypes() {
        return entityTypes;
    }

    public String getTaIdentRef() {
        return taIdentRef;
    }

    public String getReferenceContext() {
        return referenceContext;
    }

    public List<String> getTypes() {
        return types;
    }

    public NIFContext getContext() {
        return context;
    }

    public void setContext(NIFContext context) {
        this.context = context;
    }

    public String getAnnotator() {
        return annotator;
    }

    public void setAnnotator(String annotator) {
        this.annotator = annotator;
    }

    public static class NIFBeanBuilder {

        private String mention;

        private Integer beginIndex;

        private Integer endIndex;

        private NIFType nifType;

        private List<String> types;

        private Double score;

        private String taIdentRef;

        private String referenceContext;

        private String annotator;

        private NIFContext context;

        public NIFBeanBuilder() {
            init();
        }

        public NIFBeanBuilder init() {

            this.mention = null;
            this.beginIndex = null;
            this.endIndex = null;
            this.nifType = NIFType.ENTITY;
            this.types = null;
            this.score = null;
            this.taIdentRef = null;
            this.context = null;
            this.annotator = null;
            return this;
        }


        public NIFBeanBuilder context(String baseURI, int beginIndex, int endIndex) {
            this.context = new NIFContext(baseURI, beginIndex, endIndex);
            return this;
        }

        public NIFBeanBuilder taIdentRef(String taIdentRef) {
            this.taIdentRef = taIdentRef;
            return this;
        }

        public NIFBeanBuilder mention(String mention) {
            this.mention = mention;
            return this;
        }

        public NIFBeanBuilder beginIndex(Integer beginIndex) {
            this.beginIndex = beginIndex;
            return this;
        }

        public NIFBeanBuilder endIndex(Integer endIndex) {
            this.endIndex = endIndex;
            return this;
        }


        public NIFBeanBuilder nifType(NIFType nifType) {
            this.nifType = nifType;
            return this;
        }


        public NIFBeanBuilder types(List<String> types) {
            this.types = types;
            return this;
        }


        public NIFBeanBuilder score(Double score) {
            this.score = score;
            return this;
        }

        public NIFBeanBuilder referenceContext(String referenceContext) {
            this.referenceContext = referenceContext;
            return this;
        }

        public NIFBeanBuilder annotator(String annotator) {
            this.annotator = annotator;
            return this;
        }

        public NIFBean build() {
            return new NIFBean(this);
        }


    }
}
