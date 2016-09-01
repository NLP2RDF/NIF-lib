package org.nlp2rdf;


import org.aksw.rdfunit.enums.TestCaseExecutionType;
import org.aksw.rdfunit.exceptions.TestCaseInstantiationException;
import org.aksw.rdfunit.io.reader.RdfReaderException;
import org.aksw.rdfunit.io.reader.RdfReaderFactory;
import org.aksw.rdfunit.model.impl.results.DatasetOverviewResults;
import org.aksw.rdfunit.model.interfaces.results.TestCaseResult;
import org.aksw.rdfunit.model.interfaces.results.TestExecution;
import org.aksw.rdfunit.validate.wrappers.RDFUnitStaticValidator;
import org.aksw.rdfunit.validate.wrappers.RDFUnitTestSuiteGenerator;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.junit.Test;
import org.nlp2rdf.impl.NIF20;
import org.nlp2rdf.impl.NIFBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class NIF20Test {

    private NIFBean getBean() {

        List<String> types = new ArrayList<String>();
        types.add("http://dbpedia.org/ontology/Person");
        types.add("http://dbpedia.org/ontology/SportsManager");
        types.add("http://dbpedia.org/ontology/SoccerManager");
        types.add("http://nerd.eurecom.fr/ontology#Person");


        NIFBean.NIFBeanBuilder builder = new NIFBean.NIFBeanBuilder();
        builder.context("http://freme-project.eu", 0, 22).mention("Diego Maradona").beginIndex(0).endIndex(14)
                .taIdentRef("http://dbpedia.org/resource/Diego_Maradona").score(0.9869992701528016)
                .annotator("http://freme-project.eu/tools/freme-ner")
                .types(types);
        NIFBean bean = new NIFBean(builder);

        return bean;
    }

    @Test
    public void testGenerateNTriples() {
        //Init
        NIFBean bean = getBean();

        //Act
        NIF20 nif20 = new NIF20(bean);
        System.out.println(nif20.getNTriples());
    }

    @Test
    public void testGenerateRDFxml() {
        //Init
        NIFBean bean = getBean();

        //Act
        NIF20 nif20 = new NIF20(bean);
        System.out.println(nif20.getRDFxml());
    }

    @Test
    public void testGenerateTurtle() {
        //Init
        NIFBean bean = getBean();

        //Act
        NIF20 nif20 = new NIF20(bean);
        System.out.println(nif20.getTurtle());
    }

    @Test
    public void testIsomorphicRdfResults() throws RdfReaderException {
        //Init
        NIFBean bean = getBean();

        //Act
        NIF20 nif20 = new NIF20(bean);
        String turtle = nif20.getTurtle();

        //Assert
        Model model = RdfReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();
    }


    @Test
    public void testIfNTisIsomorphicWithTurtle() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        NIFBean bean = getBean();

        //Act
        NIF20 nif20 = new NIF20(bean);
        String turtle = nif20.getTurtle();

        Model modelTtl = RdfReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();

        String ntriples = nif20.getNTriples();
        Model modelNt = RdfReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelTtl));

    }


    @Test
    public void testIfNTisIsomorphicWithXml() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        NIFBean bean = getBean();

        //Act
        NIF20 nif20 = new NIF20(bean);
        String rdfXml = nif20.getRDFxml();
        Model modelXml = RdfReaderFactory.createReaderFromText(rdfXml, Lang.RDFXML.getName()).read();

        String ntriples = nif20.getNTriples();
        Model modelNt = RdfReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelXml));

    }


    @Test
    public void testDynamicRDFUnitTestsLookingForErrors() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        NIFBean bean = getBean();
        DatasetOverviewResults overviewResults = new DatasetOverviewResults();


        //Act
        NIF20 nif20 = new NIF20(bean);
        String turtle = nif20.getNTriples();


        Model model = RdfReaderFactory.createReaderFromText(turtle, Lang.NTRIPLES.getName()).read();
        RDFUnitStaticValidator.initWrapper(
                new RDFUnitTestSuiteGenerator.Builder()
                        .addLocalResourceOrSchemaURI("org/unileipzig/persistence/nif", "org/uni-leipzig/persistence/nlp2rdf/org.unileipzig.persistence.nif-core/org.unileipzig.persistence.nif-core.ttl", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/org.unileipzig.persistence.nif-core#")
                        .build()
        );
        TestExecution te = RDFUnitStaticValidator.validate(model, TestCaseExecutionType.shaclFullTestCaseResult);

        //Assert
        for (TestCaseResult tcr : te.getTestCaseResults()) {
            fail(tcr.getMessage());
        }

    }

}
