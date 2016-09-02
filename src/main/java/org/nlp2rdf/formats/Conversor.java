package org.nlp2rdf.formats;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class Conversor {

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

    /**
     * Return the content as JSONLD
     *
     * @param beans
     * @param templatePath
     * @return
     */
    protected String getJSONLD(String contextJSON, List<NIFBean> beans, String templatePath) {

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(templatePath);
        Context context = new VelocityContext();


        NIFBean nifContext = beans.stream().filter(bean -> NIFType.CONTEXT.equals(bean.getNifType())).findFirst().get();
        beans.remove(nifContext);


        context.put("contextJSON", contextJSON);
        context.put("beans", beans);

        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        String result = sw.toString();
        try {
            sw.close();
        } catch (IOException e) {
        }

        return result;
    }
}
