package org.nlp2rdf.nif21;

import org.nlp2rdf.NIFFormat;

public interface NIF21Format extends NIFFormat {

    String XML_PREFIX = "http://www.w3.org/2001/XMLSchema#";

    String XML_STRING = XML_PREFIX.concat("string");

    String XML_NON_NEGATIVE_INTEGER = XML_PREFIX.concat("nonNegativeInteger");

    String RDF_PREFIX = "http://www.w3.org/2005/11/its/rdf#";

    String RDF_PROPERTY_CLASS_REF = RDF_PREFIX.concat("taClassRef");

    String RDF_PROPERTY_CONFIDENCE = RDF_PREFIX.concat("taConfidence");

    String RDF_PROPERTY_IDENTREF = RDF_PREFIX.concat("taIdentRef");

    String RDF_PROPERTY_ANNOTATOR = RDF_PREFIX.concat("taAnnotatorsRef");

    String RDF_SYNTAX_PREFIX = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    String RDF_SYNTAX_TYPE = RDF_SYNTAX_PREFIX.concat("type");

    String NIF_21 = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core/2.1";

    String NIF_PROPERTY_CONFORMS_TO = "http://purl.org/dc/terms/conformsTo";

    String NIF_CORE_PREFIX = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#";

    String NIF_PROPERTY_ISSTRING = NIF_CORE_PREFIX.concat("isString");

    String NIF_CLASS_REF = NIF_CORE_PREFIX.concat("taMsClassRef");

    String NIF_PROPERTY_OFFSETBASEDSTRING = NIF_CORE_PREFIX.concat("OffsetBasedString");

    String NIF_PROPERTY_CONTEXT = NIF_CORE_PREFIX.concat("Context");

    String NIF_PROPERTY_PHRASE = NIF_CORE_PREFIX.concat("Phrase");

    String NIF_PROPERTY_WORD = NIF_CORE_PREFIX.concat("Word");

    String NIF_PROPERTY_ANCHOROF = NIF_CORE_PREFIX.concat("anchorOf");

    String NIF_PROPERTY_BEGININDEX = NIF_CORE_PREFIX.concat("beginIndex");

    String NIF_PROPERTY_ENDINDEX = NIF_CORE_PREFIX.concat("endIndex");

    String NIF_PROPERTY_CONTEXT_COLLECTION = NIF_CORE_PREFIX.concat("ContextCollection");

    String NIF_PROPERTY_REFERENCE_CONTEXT = NIF_CORE_PREFIX.concat("referenceContext");

    String NIF_PROPERTY_ANNOTATION_UNIT = NIF_CORE_PREFIX.concat("annotationUnit");

    String NIF_PROPERTY_ENTITY_OCCURRENCE = NIF_CORE_PREFIX.concat("EntityOccurrence");

    String NIF_PROPERTY_HAS_CONTEXT = NIF_CORE_PREFIX.concat("hasContext");

    String NIF_PROPERTY_PARAGRAPH = NIF_CORE_PREFIX.concat("Paragraph");

    String NIF_PROPERTY_HAS_PARAGRAPH = NIF_CORE_PREFIX.concat("hasParagraph");

    String NIF_PROPERTY_FIRST_PARAGRAPH = NIF_CORE_PREFIX.concat("firstParagraph");

    String NIF_PROPERTY_LAST_PARAGRAPH = NIF_CORE_PREFIX.concat("lastParagraph");

    String NIF_PROPERTY_SECTION = NIF_CORE_PREFIX.concat("Section");

    String NIF_PROPERTY_SUPER_STRING = NIF_CORE_PREFIX.concat("superString");

    String NIF_PROPERTY_PRED_LANG = NIF_CORE_PREFIX.concat("predLang");

    String NIF_PROPERTY_SOURCE_URL = NIF_CORE_PREFIX.concat("sourceUrl");

    String NIF_PROPERTY_HAS_SECTION = NIF_CORE_PREFIX.concat("hasSection");

    String CONTEXT_FORMAT = "%s#offset_%d_%d";

    String TEMPLATE_NIF_PATH = TEMPLATE_ROOT.concat("nif-21.vm");

    String TEMPLATE_CONTEXT_PATH = TEMPLATE_ROOT.concat("json-ld-context.vm");

}
