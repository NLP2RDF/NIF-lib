package org.unileipzig.persistence.nif.impl;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.IOException;
import java.io.StringWriter;

public class Formats {


    /**
     * Return the content as RDF XML
     *
     * @return
     */
    protected String getRDFxml(Model model) {
        StringWriter sw = new StringWriter();
        RDFDataMgr.write(sw, model, Lang.RDFXML);
        String result = sw.toString();
        try {
            sw.close();
        } catch (IOException e) {
        }

        return result;

    }

    /**
     * Return the content as NTriples
     *
     * @return
     */
    protected String getNTriples(Model model) {
        StringWriter sw = new StringWriter();
        RDFDataMgr.write(sw, model, Lang.NTRIPLES);
        String result = sw.toString();
        try {
            sw.close();
        } catch (IOException e) {
        }

        return result;

    }

    /**
     * Return the content as Turtle
     *
     * @return
     */
    protected String getTurtle(Model model) {
        StringWriter sw = new StringWriter();
        RDFDataMgr.write(sw, model, Lang.TURTLE);
        String result = sw.toString();
        try {
            sw.close();
        } catch (IOException e) {
        }

        return result;

    }
}
