package org.nlp2rdf.business;


import com.hp.hpl.jena.rdf.model.Model;
import org.aksw.rdfunit.exceptions.TestCaseInstantiationException;
import org.aksw.rdfunit.io.reader.RDFReaderException;
import org.aksw.rdfunit.io.reader.RDFReaderFactory;
import org.aksw.rdfunit.utils.TestUtils;
import org.apache.jena.riot.Lang;
import org.junit.Test;
import org.nlp2rdf.bean.NIFBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NIFManagerTest {

    private List<NIFBean> getBeans() {
        List<NIFBean> result = new ArrayList<NIFBean>();

        NIFBean bean = new NIFBean();
        bean.setContent("Berlin is the capital of Germany and one of the 16 states of Germany.");
        bean.setResource("http://dbpedia.org/page/Berlin");
        bean.setURL("http://dbpedia.org/page/Berlin");
        bean.setResourceTypes(Arrays.asList("DBpedia:Place", "DBpedia:Location"));
        bean.setOffset(1);
        bean.setSize(6);


        result.add(bean);

        bean = new NIFBean();
        bean.setContent("Berlin is the capital of Germany and one of the 16 states of Germany.");
        bean.setResourceTypes(Arrays.asList("DBpedia:Place", "DBpedia:Location"));
        bean.setResource("http://dbpedia.org/page/Germany");
        bean.setURL("http://dbpedia.org/page/Germany");
        bean.setOffset(61);
        bean.setSize(7);
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

    @Test
    public void testTurtleOutputWithRDFUnit() throws RDFReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        String turtle = NIFManager.build(beans).getTurtle();
        Model model = RDFReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();

        TestUtils.instantiateTestsFromModel(model, true);
    }

    @Test
    public void testRDFXMLOutputWithRDFUnit() throws RDFReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        String turtle = NIFManager.build(beans).getRDFxml();
        Model model = RDFReaderFactory.createReaderFromText(turtle, Lang.RDFXML.getName()).read();

        TestUtils.instantiateTestsFromModel(model, true);
    }

    @Test
    public void testNTripleOutputWithRDFUnit() throws RDFReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        String turtle = NIFManager.build(beans).getNTriples();

        Model model = RDFReaderFactory.createReaderFromText(turtle, Lang.NTRIPLES.getName()).read();

        TestUtils.instantiateTestsFromModel(model, true);
    }

}
