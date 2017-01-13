package org.nlp2rdf.bean;

import com.google.common.hash.Hashing;
import org.nlp2rdf.validator.NIFBeanContextValidator;
import org.nlp2rdf.validator.NIFBeanNumbersValidator;
import org.nlp2rdf.validator.NIFMessagesException;

import java.util.*;

public class NIFBean implements NIFMessagesException {

    private NIFContext context;

    private String annotator;

    private String mention;

    private Integer beginIndex;

    private Integer endIndex;

    private NIFType nifType = NIFType.ENTITY;

    private List<String> types;

    private Double score;

    private String taIdentRef;

    private List<String> taClassRef;

    private String referenceContext;

    private String referenceContextId;

    public String getReferenceContextId() {
        return referenceContextId;
    }

    private Map<String, String> entityTypes = new HashMap<String, String>(8);

    public NIFBean(List<NIFBeanBuilder> builders) {

        List<NIFBean> nifBeans = new ArrayList<>();

        for (NIFBeanBuilder builder: builders) {
            nifBeans.add(new NIFBean(builder));
        }
    }

    public List<String> getTaClassRef() {
        return taClassRef;
    }

    public NIFBean(NIFBeanBuilder builder) {
        init();
        setNifType(builder.nifType);
        setContext(builder.context);
        setMention(builder.mention);
        setBeginIndex(builder.beginIndex);
        setEndIndex(builder.endIndex);
        setTypes(builder.types);
        setScore(builder.score);
        setTaIdentRef(builder.taIdentRef);
        setReferenceContext(builder.referenceContext);
        setAnnotator(builder.annotator);
        setTaClassRef(builder.taClassRef);
    }

    public static void validate(List<NIFBean> beans) {

        NIFBeanNumbersValidator.checkIfEndIndexIsGreaterThanBeginIndex(beans);
        NIFBeanContextValidator.checkIfContextExists(beans);
        NIFBeanContextValidator.checkIfHasDuplicatedContext(beans);

    }

    public static void fillBeansWithContext(List<NIFBean> beans, String CONTEXT_FORMAT) {

        for(NIFBean bean: beans) {
            if ( bean != null && NIFType.CONTEXT.equals(bean.getNifType())) {
                bean.setReferenceContext(bean.getContext().context(CONTEXT_FORMAT));
            }
        }
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Boolean hasTaClassRef() {
        return taClassRef != null && !taClassRef.isEmpty();
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

    public void setMention(String mention) {
        Objects.requireNonNull(mention, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_MENTION));
        this.mention = mention;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        if (isMention()) {
            Objects.requireNonNull(beginIndex, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_BEGIN_INDEX));
        }
        this.beginIndex = beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        if (isMention()) {
            Objects.requireNonNull(beginIndex, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_END_INDEX));
        }
        this.endIndex = endIndex;
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

    public void setNifType(NIFType nifType) {
        this.nifType = nifType;
    }

    public String getTaIdentRef() {
        return taIdentRef;
    }

    public void setTaIdentRef(String taIdentRef) {
        this.taIdentRef = taIdentRef;
    }

    public String getReferenceContext() {
        return referenceContext;
    }

    public void setReferenceContext(String referenceContext) {
        if (referenceContext != null) {
            this.referenceContext = referenceContext;
            this.referenceContextId = Hashing.md5().hashBytes(referenceContext.getBytes()).toString();
        }
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        List<String> convertedTypes = new ArrayList<>();
        if (types != null) {

            for(String type: types) {
                if (type != null) {
                    convertedTypes.add(entityTypes.get(type));
                }
            }
        }
        this.types = convertedTypes;
    }

    public NIFContext getContext() {
        return context;
    }

    public void setContext(NIFContext context) {
        Objects.requireNonNull(context, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_CONTEXT));
        this.context = context;
    }

    public String getAnnotator() {
        return annotator;
    }

    public void setAnnotator(String annotator) {
        this.annotator = annotator;
    }

    public void setTaClassRef(List<String> taClassRef) {
        this.taClassRef = taClassRef;
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

        private List<String> taClassRef;

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
            this.taClassRef = null;
            return this;
        }


        public NIFBeanBuilder context(String baseURI, int beginIndex, int endIndex) {
            Objects.requireNonNull(baseURI, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_BASE_URI));

            assert beginIndex >= 0 : String.format(NIF_DATA_VALUE_NON_NEGATIVE, NIF_DATA_BEGIN_INDEX);
            assert endIndex >= 0 : String.format(NIF_DATA_VALUE_NON_NEGATIVE, NIF_DATA_END_INDEX);
            assert endIndex > beginIndex : String.format(NIF_DATA_VALUE_MUST_BE_GREATER, NIF_DATA_BEGIN_INDEX,
                    NIF_DATA_END_INDEX, NIF_DATA_CONTEXT);

            this.context = new NIFContext(baseURI, beginIndex, endIndex);
            return this;
        }

        public NIFBeanBuilder context(String baseURI) {
            Objects.requireNonNull(baseURI, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_BASE_URI));

            this.context = new NIFContext(baseURI);
            return this;
        }

        public NIFBeanBuilder taIdentRef(String taIdentRef) {
            Objects.requireNonNull(taIdentRef, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_TA_REFERENCE));
            this.taIdentRef = taIdentRef;
            return this;
        }

        public NIFBeanBuilder mention(String mention) {
            Objects.requireNonNull(mention, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_MENTION));
            this.mention = mention;
            return this;
        }

        public NIFBeanBuilder beginIndex(Integer beginIndex) {
            Objects.requireNonNull(beginIndex, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_BEGIN_INDEX));
            assert beginIndex >= 0 : String.format(NIF_DATA_VALUE_NON_NEGATIVE, NIF_DATA_BEGIN_INDEX);

            this.beginIndex = beginIndex;
            return this;
        }

        public NIFBeanBuilder endIndex(Integer endIndex) {

            Objects.requireNonNull(beginIndex, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_END_INDEX));
            assert endIndex >= 0 : String.format(NIF_DATA_VALUE_NON_NEGATIVE, NIF_DATA_END_INDEX);

            this.endIndex = endIndex;
            return this;
        }

        public NIFBeanBuilder nifType(NIFType nifType) {

            Objects.requireNonNull(nifType, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_TYPE));
            this.nifType = nifType;
            return this;
        }

        public NIFBeanBuilder types(List<String> types) {
            this.types = types == null ? new ArrayList<String>(1) : types;
            return this;
        }

        public NIFBeanBuilder taClassRef(List<String> taClassRef) {
            this.taClassRef = taClassRef;
            return this;
        }

        public NIFBeanBuilder score(Double score) {
            Objects.requireNonNull(score, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_SCORE));
            this.score = score;
            return this;
        }

        public NIFBeanBuilder referenceContext(String referenceContext) {
            Objects.requireNonNull(referenceContext, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_REFERENCE_CONTEXT));
            this.referenceContext = referenceContext;
            return this;
        }

        public NIFBeanBuilder annotator(String annotator) {
            Objects.requireNonNull(annotator, String.format(NIF_DATA_VALUE_NOT_NULL, NIF_DATA_REFERENCE_ANNOTATOR));
            this.annotator = annotator;
            return this;
        }

        public NIFBean build() {
            return new NIFBean(this);
        }

    }

}
