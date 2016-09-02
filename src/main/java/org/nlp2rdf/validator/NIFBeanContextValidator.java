package org.nlp2rdf.validator;


import org.nlp2rdf.exception.NIFException;
import org.nlp2rdf.impl.NIFBean;
import org.nlp2rdf.impl.NIFType;

import java.util.List;

public class NIFBeanContextValidator implements NIFMessagesException {

    public static void checkIfContextExists(List<NIFBean> beans) {
        if (!beans.stream().filter(bean -> NIFType.CONTEXT.equals(bean.getNifType())).findFirst().isPresent()) {
            throw new NIFException(NIF_STRUCTURE_CONTEXT_NOT_FOUND);
        }
    }

    public static void checkIfHasDuplicatedContext(List<NIFBean> beans) {
        if (beans.stream().filter(bean -> NIFType.CONTEXT.equals(bean.getNifType())).count() > 1) {
            throw new NIFException(NIF_STRUCTURE_DUPLICATED_CONTEXT);
        }
    }
}
