## NIF library
[![maven-central](https://img.shields.io/badge/maven--central-0.4-blue.svg)](http://search.maven.org/#artifactdetails%7Corg.nlp2rdf%7Cnif%7C0.4%7Cjar)
[![Build Status](https://travis-ci.org/NLP2RDF/NIF-lib.svg?branch=master)](https://travis-ci.org/NLP2RDF/NIF-lib) [![Coverage Status](https://coveralls.io/repos/github/NLP2RDF/NIF-lib/badge.svg)](https://coveralls.io/github/NLP2RDF/NIF-lib)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0434cdca38e74a2f8ba271496d3de2eb)](https://www.codacy.com/app/sandroacoelho/NIF-lib?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=NLP2RDF/NIF-lib&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/d2f8dbc4-6853-4bc4-9933-6e6830a76a7b)](https://codebeat.co/projects/github-com-nlp2rdf-nif-lib) [![Project Stats](https://www.openhub.net/p/NIF-lib/widgets/project_thin_badge.gif)](https://www.ohloh.net/p/NIF-lib)

## What is NIF (NLP Interchange Format) ?

The NLP Interchange Format (NIF) is an RDF/OWL-based format that aims to achieve interoperability between Natural Language Processing (NLP) tools, language resources and annotations. NIF consists of specifications, ontologies and software (overview).

## Documentation

[NIF Documentation](http://persistence.uni-leipzig.org/nlp2rdf/)


## Supported NIF versions

 * 2.0 
 * 2.1
 
 ## Jena x NIF Lib versions

* Jena 2.13.0  - 0.2.x ; 0.3.x
* Jena 3.1.0   - 0.4.x 

## Supported formats

* JSON-LD
* Turtle
* RDF-xml
* NTriples

## Usage

1) Create a context 
```
NIFBean.NIFBeanBuilder contextBuilder = new NIFBean.NIFBeanBuilder();

contextBuilder.context("http://freme-project.eu", 0, 33).mention("Diego Maradona is from Argentina.").nifType(NIFType.CONTEXT);

NIFBean beanContext = new NIFBean(contextBuilder);

```

2) Create entries for the entities

```
   NIFBean.NIFBeanBuilder entityBuilder = new NIFBean.NIFBeanBuilder();
   
   List<String> types = new ArrayList<String>();
                types.add("http://dbpedia.org/ontology/Place");
                types.add("http://dbpedia.org/ontology/Location");
                types.add("http://dbpedia.org/ontology/PopulatedPlace");
                types.add("http://nerd.eurecom.fr/ontology#Location");
                types.add("http://dbpedia.org/ontology/Country");

        entityBuilder.context("http://freme-project.eu", 23, 32).mention("Argentina").beginIndex(23).endIndex(32)
                .taIdentRef("http://dbpedia.org/resource/Argentina").score(0.9804963628413852)
                .annotator("http://freme-project.eu/tools/freme-ner")
                .types(types);

        NIFBean entityBean = new NIFBean(contextBuilder);

```

3) Add it in an array list

```
    List<NIFBean> beans = new ArrayList<>();
    
    beans.add(entityBean);
    
```

4) Instantiate a NIF version that you like to use,

```
   NIF nif = new NIF20(beans);   // For NIF 2.0
   
   NIF nif = new NIF21(beans);   // For NIF 2.1
```

5) Finally, get the output with the format that you need 

```
  nif.getJSONLD("Path for NIF Context");  //JSON-LD  
 
  nif.getTurtle(); //Turtle 
  
  nif.getRDFxml(); //RDF-xml
  
  nif.getNTriples(); //NTriples
```


## Issues

If you have any problems with or questions about this library, please contact us through a [GitHub issue](https://github.com/sandroacoelho/NIF/issues).

## Maintainers

<a href="http://infai.org"><img src="https://infai.org/wp-content/uploads/2017/08/InfAI-Logo.png" align="left" height="20%" width="20%" ></a>

