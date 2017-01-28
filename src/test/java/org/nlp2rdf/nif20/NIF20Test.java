package org.nlp2rdf.nif20;


import org.aksw.rdfunit.enums.TestCaseExecutionType;
import org.aksw.rdfunit.exceptions.TestCaseInstantiationException;
import org.aksw.rdfunit.io.reader.RdfReaderException;
import org.aksw.rdfunit.io.reader.RdfReaderFactory;
import org.aksw.rdfunit.model.interfaces.results.TestCaseResult;
import org.aksw.rdfunit.model.interfaces.results.TestExecution;
import org.aksw.rdfunit.validate.wrappers.RDFUnitStaticValidator;
import org.aksw.rdfunit.validate.wrappers.RDFUnitTestSuiteGenerator;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.junit.Ignore;
import org.junit.Test;
import org.nlp2rdf.NIF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;
import org.nlp2rdf.nif20.impl.NIF20;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class NIF20Test {

    private List<NIFBean> getBean() {

        //----------------------------
        List<NIFBean> result = new ArrayList<>();

        NIFBean.NIFBeanBuilder builderContext = new NIFBean.NIFBeanBuilder();

        builderContext.context("http://freme-project.eu", 0, 33).mention("Diego Maradona is from Argentina.").nifType(NIFType.CONTEXT);

        NIFBean beanContext = new NIFBean(builderContext);

        result.add(beanContext);

        //-------------------------

        NIFBean.NIFBeanBuilder builderMention1 = new NIFBean.NIFBeanBuilder();

        List<String> typesMention1 = new ArrayList<String>();
        typesMention1.add("http://dbpedia.org/ontology/Place");
        typesMention1.add("http://dbpedia.org/ontology/Location");
        typesMention1.add("http://dbpedia.org/ontology/PopulatedPlace");
        typesMention1.add("http://nerd.eurecom.fr/ontology#Location");
        typesMention1.add("http://dbpedia.org/ontology/Country");

        builderMention1.context("http://freme-project.eu", 23, 32).mention("Argentina").beginIndex(23).endIndex(32)
                .taIdentRef("http://dbpedia.org/resource/Argentina").score(0.9804963628413852)
                .annotator("http://freme-project.eu/tools/freme-ner")
                .types(typesMention1);

        NIFBean bean = new NIFBean(builderMention1);

        result.add(bean);

        //----------------------------

        NIFBean.NIFBeanBuilder builderMention2 = new NIFBean.NIFBeanBuilder();

        List<String> typesMention2 = new ArrayList<String>();
        typesMention2.add("http://dbpedia.org/ontology/Person");
        typesMention2.add("http://dbpedia.org/ontology/SportsManager");
        typesMention2.add("http://dbpedia.org/ontology/SoccerManager");
        typesMention2.add("http://nerd.eurecom.fr/ontology#Person");

        builderMention2.context("http://freme-project.eu", 0, 14).mention("Diego Maradona").beginIndex(0).endIndex(14)
                .taIdentRef("http://dbpedia.org/resource/Diego_Maradona").score(0.9869992701528016)
                .annotator("http://freme-project.eu/tools/freme-ner")
                .types(typesMention2);

        NIFBean bean1 = new NIFBean(builderMention2);

        //----------------------------

        result.add(bean1);

        return result;
    }

    @Test
    public void testGenerateNTriples() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans, null);
        System.out.println(nif20.getNTriples());
    }

    @Test
    public void testGenerateRDFxml() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans, null);
        System.out.println(nif20.getRDFxml());
    }

    @Test
    public void testGenerateTurtle() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans, null);
        System.out.println(nif20.getTurtle(beans));
    }

    @Test
    public void testIsomorphicRdfResults() throws RdfReaderException {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans);
        String turtle = nif20.getTurtle(beans);

        //Assert
        RdfReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();
    }


    @Test
    @Ignore
    public void testIfNTisIsomorphicWithTurtle() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans);
        String turtle = nif20.getTurtle(beans);

        Model modelTtl = RdfReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();

        String ntriples = nif20.getNTriples();
        Model modelNt = RdfReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelTtl));

    }


    @Test
    public void testIfNTisIsomorphicWithXml() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans);
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
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans);
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

    @Test
    public void testGenerateJSONLD() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF20(beans, null);
        System.out.println(nif20.getJSONLD("http://www.freme-project.eu/context.jsonld"));

    }

}
