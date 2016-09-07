## NIF library

[![RDFUnit](https://img.shields.io/badge/RDFUnit-compliance-brightgreen.svg)](https://github.com/AKSW/RDFUnit) [![Build Status](https://travis-ci.org/sandroacoelho/NIF-lib.svg?branch=master)](https://travis-ci.org/sandroacoelho/NIF-lib) [![Coverage Status](https://coveralls.io/repos/github/sandroacoelho/NIF-lib/badge.svg?branch=master)](https://coveralls.io/github/sandroacoelho/NIF-lib?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/eef8ff92daa34394ab90fea368a2d639)](https://www.codacy.com/app/sandroacoelho/NIF-lib?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sandroacoelho/NIF-lib&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/09126edf-2462-436b-8045-78de67d01ef2)](https://codebeat.co/projects/github-com-sandroacoelho-nif-lib)

## What is NIF (NLP Interchange Format) ?

The NLP Interchange Format (NIF) is an RDF/OWL-based format that aims to achieve interoperability between Natural Language Processing (NLP) tools, language resources and annotations. NIF consists of specifications, ontologies and software (overview).

## Documentation

[NIF Documentation](http://persistence.uni-leipzig.org/nlp2rdf/)


## Supported versions

 * 2.0 
 * 2.1

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

NIFBean beanContext = new NIFBean(builderContext);

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

        NIFBean entityBean = new NIFBean(entity);

```

3) Add it in an array list

```
    List<NIFBean> beans = new ArrayList<>();
    
    beans.add(entityBean);
    beans.add(contextBuilder);
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

<a href="http://infai.org"><img src="http://infai.org/de/Aktuelles/files?get=10_jahre_infai_gold.PNG" align="left" height="20%" width="20%" ></a>

