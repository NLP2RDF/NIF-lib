package org.nlp2rdf;

import java.util.Set;

public interface ContextJSONLD {

    String getContextForJSONLD(Set<String> ontologies, String language);

    String getContextForJSONLD(Set<String> ontologies, String template, String language);
}
