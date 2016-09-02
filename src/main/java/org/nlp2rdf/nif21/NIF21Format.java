package org.nlp2rdf.nif21;

import org.nlp2rdf.NIFFormat;

public interface NIF21Format extends NIFFormat {

    String XML_PREFIX = "http://www.w3.org/2001/XMLSchema#";

    String RDF_PREFIX = "http://www.w3.org/2005/11/its/rdf#";

    String RDF_PROPERTY_CLASS_REF = RDF_PREFIX.concat("taClassRef");

    String RDF_PROPERTY_CONFIDENCE = RDF_PREFIX.concat("taConfidence");

    String RDF_PROPERTY_IDENTREF = RDF_PREFIX.concat("taIdentRef");

    String RDF_PROPERTY_ANNOTATOR = RDF_PREFIX.concat("taAnnotatorsRef");

    String NIF_21 = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/org.unileipzig.persistence.nif-core/2.1";

    String NIF_PROPERTY_CONFORMS_TO = "http://purl.org/dc/terms/conformsTo";

    String NIF_CORE_PREFIX = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/org.unileipzig.persistence.nif-core#";

    String NIF_PROPERTY_ISSTRING = NIF_CORE_PREFIX.concat("isString");

    String NIF_PROPERTY_OFFSETBASEDSTRING = NIF_CORE_PREFIX.concat("OffsetBasedString");

    String NIF_PROPERTY_CONTEXT = NIF_CORE_PREFIX.concat("Context");

    String NIF_PROPERTY_PHRASE = NIF_CORE_PREFIX.concat("Phrase");

    String NIF_PROPERTY_BEGININDEX = NIF_CORE_PREFIX.concat("beginIndex");

    String NIF_PROPERTY_ENDINDEX = NIF_CORE_PREFIX.concat("endIndex");

    String NIF_PROPERTY_CONTEXT_COLLECTION = NIF_CORE_PREFIX.concat("ContextCollection");

    String NIF_PROPERTY_REFERENCE_CONTEXT = NIF_CORE_PREFIX.concat("referenceContext");

    String NIF_PROPERTY_ANNOTATION_UNIT = NIF_CORE_PREFIX.concat("annotationUnit");

    String NIF_PROPERTY_ENTITY_OCCURRENCE = NIF_CORE_PREFIX.concat("EntityOccurrence");

    String NIF_PROPERTY_HAS_CONTEXT = NIF_CORE_PREFIX.concat("hasContext");

    String CONTEXT_FORMAT = "%s#offset_%d_%d";

    String TEMPLATE_FREME_PATH = "./src/main/resources/templates/nif-21.vm";

}
