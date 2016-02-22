package org.nlp2rdf.business;


import org.junit.Test;
import org.nlp2rdf.bean.NIFBean;

import java.util.ArrayList;
import java.util.List;

public class NIFManagerTest {

    private List<NIFBean> getBeans() {
        List<NIFBean> result = new ArrayList<NIFBean>();

        NIFBean bean = new NIFBean();
        bean.setContent("Berlin is the capital of Germany and one of the 16 states of Germany.");

        result.add(bean);

        return result;
    }

    @Test
    public void testGenerateNTriples() {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        NIFManager.build(beans).getNTriples();
    }

    @Test
    public void testGenerateRDFxml() {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        NIFManager.build(beans).getRDFxml();
    }

    @Test
    public void testGenerateTurtle() {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        NIFManager.build(beans).getTurtle();
    }

}
