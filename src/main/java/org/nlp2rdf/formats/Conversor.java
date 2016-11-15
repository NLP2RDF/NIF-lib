package org.nlp2rdf.formats;



import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFJSONLDContext;
import org.nlp2rdf.bean.NIFType;
import org.nlp2rdf.exception.NIFException;
import org.nlp2rdf.json.JSONMinify;


import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import static org.nlp2rdf.validator.NIFMessagesException.NIF_BEANS_NOT_FOUND;

public class Conversor {

    /**
     * Return the content as RDF XML
     *
     * @return
     */
    protected String getRDFxml(Model model) {
        return getModelAsStringByFormat(model, Lang.RDFXML);
    }

    /**
     * Return the content as NTriples
     *
     * @return
     */
    protected String getNTriples(Model model) {
        return getModelAsStringByFormat(model, Lang.NTRIPLES);
    }

    /**
     * Return the content as Turtle
     *
     * @return
     */
    protected String getTurtle(Model model) {
        return getModelAsStringByFormat(model, Lang.TURTLE);
    }

    private String getModelAsStringByFormat(Model model, Lang lang) {
        StringWriter sw = new StringWriter();
        RDFDataMgr.write(sw, model, lang);
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

        VelocityEngine velocityEngine = getVelocityEngine();

        Template template = velocityEngine.getTemplate(templatePath);
        Context context = new VelocityContext();

        if (beans.size() == 0) {
            throw new NIFException(NIF_BEANS_NOT_FOUND);
        }

        context.put("contextJSON", contextJSON);
        context.put("context", beans.get(0).getReferenceContext());
        context.put("beans", beans);

        String result = getStringFromVelocity(template, context);

        return JSONMinify.minify(result);
    }

    private void removeNIFContext(List<NIFBean> beans) {
        NIFBean nifContext = beans.stream().filter(bean -> NIFType.CONTEXT.equals(bean.getNifType())).findFirst().get();
        beans.remove(nifContext);
    }

    private VelocityEngine getVelocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();

        velocityEngine.init();

        return velocityEngine;
    }


    protected String getContextForJSONLD(Set<String> ontologies, String templatePath, String language) {

        VelocityEngine velocityEngine = getVelocityEngine();

        Template template = velocityEngine.getTemplate(templatePath);

        Context context = new VelocityContext();

        context.put("contextBeans", new NIFJSONLDContext().convertToBeans(ontologies, language));

        String result = getStringFromVelocity(template, context);

         return JSONMinify.minify(result);
    }

    private String getStringFromVelocity(Template template, Context context) {
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
