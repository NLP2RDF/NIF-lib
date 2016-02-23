package org.nlp2rdf.business;


import org.aksw.rdfunit.enums.TestCaseExecutionType;
import org.aksw.rdfunit.exceptions.TestCaseInstantiationException;
import org.aksw.rdfunit.io.reader.RDFReaderException;
import org.aksw.rdfunit.io.reader.RDFReaderFactory;
import org.aksw.rdfunit.model.impl.results.DatasetOverviewResults;
import org.aksw.rdfunit.validate.wrappers.RDFUnitStaticValidator;
import org.aksw.rdfunit.validate.wrappers.RDFUnitTestSuiteGenerator;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.junit.Test;
import org.nlp2rdf.bean.NIFBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
    public void testRdfResultsIsomorphic() throws RDFReaderException {
        //Init
        List<NIFBean> beans = getBeans();

        String turtle = NIFManager.build(beans).getTurtle();
        Model model = RDFReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();
    }


    @Test
    public void testIfNTisIsomorphicWithTurtle() throws RDFReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        String turtle = NIFManager.build(beans).getTurtle();
        Model modelTtl = RDFReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();

        String ntriples = NIFManager.build(beans).getNTriples();
        Model modelNt = RDFReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelTtl));

    }



    @Test
    public void testIfNTisIsomorphicWithXml() throws RDFReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBeans();

        //Act
        String rdfXml = NIFManager.build(beans).getRDFxml();
        Model modelXml = RDFReaderFactory.createReaderFromText(rdfXml, Lang.RDFXML.getName()).read();

        String ntriples = NIFManager.build(beans).getNTriples();
        Model modelNt = RDFReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelXml));

    }


    @Test
    public void testDynamicRDFUnitTestsLookingForErrors() throws RDFReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBeans();
        DatasetOverviewResults overviewResults = new DatasetOverviewResults();


        //Act
        String turtle = NIFManager.build(beans).getNTriples();
        Model model = RDFReaderFactory.createReaderFromText(turtle, Lang.NTRIPLES.getName()).read();
        RDFUnitStaticValidator.initWrapper(
                new RDFUnitTestSuiteGenerator.Builder()
                        .addLocalResourceOrSchemaURI("nif", "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.ttl", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#")
                        .build()
        );
        RDFUnitStaticValidator.validate(model, TestCaseExecutionType.shaclSimpleTestCaseResult ,  "custom", overviewResults);


        //Assert
        assertEquals("Expected zero errors", 0, overviewResults.getErrorTests());
        assertEquals("Expected zero failures", 0, overviewResults.getFailedTests());
        assertEquals("Expected zero individual errors", 0, overviewResults.getIndividualErrors());
        assertEquals("Total of Test must be equals to Sucessfull tests", overviewResults.getTotalTests(), overviewResults.getSuccessfullTests());
    }

}
