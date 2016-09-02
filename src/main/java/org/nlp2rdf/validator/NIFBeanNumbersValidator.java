package org.nlp2rdf.validator;


import org.nlp2rdf.impl.NIFBean;
import org.nlp2rdf.impl.NIFType;

import java.util.List;

public class NIFBeanNumbersValidator implements NIFMessagesException {

    public static void checkIfEndIndexIsGreaterThanBeginIndex(List<NIFBean> beans) {

        beans.forEach(bean -> {

            if (NIFType.ENTITY.equals(bean.getNifType())) {
                assert bean.getEndIndex() > bean.getBeginIndex() : String.format(NIF_DATA_VALUE_MUST_BE_GREATER,
                        NIF_DATA_BEGIN_INDEX, NIF_DATA_END_INDEX, bean.getMention());
            }
        });

    }


}
