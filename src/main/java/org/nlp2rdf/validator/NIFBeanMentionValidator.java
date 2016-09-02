package org.nlp2rdf.validator;


import org.nlp2rdf.exception.NIFException;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;

import java.util.List;

public class NIFBeanMentionValidator implements NIFMessagesException {

    public static void checkIfContextExists(List<NIFBean> beans) {
        if (!beans.stream().filter(bean -> NIFType.CONTEXT.equals(bean.getNifType())).findFirst().isPresent()) {
            throw new NIFException(NIF_STRUCTURE_CONTEXT_NOT_FOUND);
        }
    }
}
