package org.nlp2rdf.validator;

public interface NIFMessagesException {

    //Fields

    String NIF_DATA_BEANS = "NIF Beans";

    String NIF_DATA_MENTION = "Mention";

    String NIF_DATA_BASE_URI = "Base URI";

    String NIF_DATA_BEGIN_INDEX = "beginIndex";

    String NIF_DATA_END_INDEX = "endIndex";

    String NIF_DATA_CONTEXT = "context";

    String NIF_DATA_TYPE = "NIF Type";

    String NIF_DATA_SCORE = "score";

    String NIF_DATA_REFERENCE_CONTEXT = "reference context";

    String NIF_DATA_REFERENCE_ANNOTATOR = "annotator";

    String NIF_DATA_TA_REFERENCE = "taIdentRef";

    //Messages

    String NIF_DATA_VALUE_NOT_NULL = "NIF 1000 - Null is not accepted for %s";

    String NIF_DATA_VALUE_NON_NEGATIVE = "NIF 1001 - Negative numbers are not accepted as value for %s";

    String NIF_DATA_VALUE_MUST_BE_GREATER = "NIF 1002 - %s must be greater than %s for %s";

    String NIF_STRUCTURE_CONTEXT_NOT_FOUND = "NIF 2000 - You must provide a NIF Context";

    String NIF_STRUCTURE_DUPLICATED_CONTEXT = "NIF 2001 - Duplicated context. You must provide only one NIF Context";

    String NIF_BEANS_NOT_FOUND = "NIF 3000 - You must provide almost one NIF bean to convert to json";


}
