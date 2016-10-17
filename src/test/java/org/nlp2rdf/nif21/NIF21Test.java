package org.nlp2rdf.nif21;


import org.junit.Test;
import org.nlp2rdf.ContextJSONLD;
import org.nlp2rdf.NIF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;
import org.nlp2rdf.nif21.impl.NIF21;

import java.util.ArrayList;
import java.util.List;


public class NIF21Test {

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
        NIF nif20 = new NIF21(beans);
        System.out.println(nif20.getNTriples());
    }

    @Test
    public void testGenerateRDFxml() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF21(beans);
        System.out.println(nif20.getRDFxml());
    }

    @Test
    public void testGenerateTurtle() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF21(beans);
        System.out.println(nif20.getTurtle());
    }

  /*   @Test
   public void testIsomorphicRdfResults() throws RdfReaderException {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF21(beans);
        String turtle = nif20.getTurtle();

        //Assert
        RdfReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();
    }*/


   /* @Test
    public void testIfNTisIsomorphicWithTurtle() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF21(beans);
        String turtle = nif20.getTurtle();

        Model modelTtl = RdfReaderFactory.createReaderFromText(turtle, Lang.TURTLE.getName()).read();

        String ntriples = nif20.getNTriples();
        Model modelNt = RdfReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelTtl));

    }*/


    /*@Test
    public void testIfNTisIsomorphicWithXml() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif20 = new NIF21(beans);
        String rdfXml = nif20.getRDFxml();
        Model modelXml = RdfReaderFactory.createReaderFromText(rdfXml, Lang.RDFXML.getName()).read();

        String ntriples = nif20.getNTriples();
        Model modelNt = RdfReaderFactory.createReaderFromText(ntriples, Lang.NTRIPLES.getName()).read();

        //Assert
        assertTrue(modelNt.isIsomorphicWith(modelXml));

    }*/


   /* @Test
    public void testDynamicRDFUnitTestsLookingForErrors() throws RdfReaderException, TestCaseInstantiationException {
        //Init
        List<NIFBean> beans = getBean();


        //Act
        NIF nif20 = new NIF21(beans);
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

    }*/

     /* @Test
    public void testGenerateJSONLD() {
        //Init
        List<NIFBean> beans = getBean();

        //Act
        NIF nif21 = new NIF21(beans);
        System.out.println(nif21.getJSONLD("http://www.freme-project.eu/context.jsonld"));

    }*/

    @Test
    public void testGenerateJSONLDContext() {

        //Init
        ContextJSONLD context = new NIF21();
        List<String>  ontologies = new ArrayList<>();

        ontologies.add("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#");

        //Act

        System.out.println(context.getContextForJSONLD(ontologies));

    }

}
