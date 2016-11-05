package org.nlp2rdf.nif20;

import org.nlp2rdf.NIFFormat;

public interface NIF20Format extends NIFFormat {

    String XML_PREFIX = "http://www.w3.org/2001/XMLSchema#";

    String RDF_PREFIX = "http://www.w3.org/2005/11/its/rdf#";

    String RDF_PROPERTY_CLASS_REF = RDF_PREFIX.concat("taClassRef");

    String RDF_PROPERTY_CONFIDENCE = RDF_PREFIX.concat("taConfidence");

    String RDF_PROPERTY_IDENTREF = RDF_PREFIX.concat("taIdentRef");

    String NIF_CORE_PREFIX = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#";

    String NIF_PROPERTY_ISSTRING = NIF_CORE_PREFIX.concat("isString");

    String NIF_PROPERTY_RFC5147 = NIF_CORE_PREFIX.concat("RFC5147String");

    String NIF_PROPERTY_CONTEXT = NIF_CORE_PREFIX.concat("Context");

    String NIF_PROPERTY_WORD = NIF_CORE_PREFIX.concat("Word");

    String NIF_PROPERTY_PHRASE = NIF_CORE_PREFIX.concat("Phrase");

    String NIF_PROPERTY_STRING = NIF_CORE_PREFIX.concat("String");

    String NIF_PROPERTY_BEGININDEX = NIF_CORE_PREFIX.concat("beginIndex");

    String NIF_PROPERTY_ENDINDEX = NIF_CORE_PREFIX.concat("endIndex");

    String NIF_PROPERTY_ANCHOR_OF = NIF_CORE_PREFIX.concat("anchorOf");

    String NIF_PROPERTY_REFERENCE_CONTEXT = NIF_CORE_PREFIX.concat("referenceContext");

    String CONTEXT_FORMAT = "%s#char=%d,%d";

    String TEMPLATE_NIF_PATH = TEMPLATE_ROOT.concat("nif-20.vm");

    String TEMPLATE_CONTEXT_PATH = TEMPLATE_ROOT.concat("json-ld-context.vm");
}
