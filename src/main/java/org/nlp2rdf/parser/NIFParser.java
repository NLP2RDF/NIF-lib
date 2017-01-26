package org.nlp2rdf.parser;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.vocabulary.RDF;
import org.nlp2rdf.nif20.NIF20Format;

import java.io.ByteArrayInputStream;
import java.util.*;

public class NIFParser {

    private String nif;

    public NIFParser(String nif) {
        this.nif = nif;
    }

    // authors: Milan Dojchinovski milan.dojchinovski@fit.cvut.cz, Nilesh Chakraborty nilesh@nileshc.com
    // http://dojchinovski.mk
    public static Document getDocumentFromNIFString(String nifString) {
        ArrayList<EntityMention> list = new ArrayList<>();
        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(nifString.getBytes()), null, "TTL");
        StmtIterator iter = model.listStatements(null, RDF.type, model.getResource(NIF20Format.NIF_PROPERTY_PHRASE));
        while (iter.hasNext()) {
            Statement stm = iter.nextStatement();
            Resource entityRes = stm.getSubject().asResource();
            String mention = entityRes.getProperty(model.getProperty(NIF20Format.NIF_PROPERTY_ANCHOR_OF)).getObject().asLiteral().getString();
            String referenceContext = entityRes.getProperty(model.getProperty(NIF20Format.NIF_PROPERTY_REFERENCE_CONTEXT)).getObject().toString();
            int beginIndex = entityRes.getProperty(model.getProperty(NIF20Format.NIF_PROPERTY_BEGININDEX)).getObject().asLiteral().getInt();
            int endIndex = entityRes.getProperty(model.getProperty(NIF20Format.NIF_PROPERTY_ENDINDEX)).getObject().asLiteral().getInt();
            EntityMention em = new EntityMention();
            em.setMention(mention);
            em.setBeginIndex(beginIndex);
            em.setEndIndex(endIndex);
            em.setContext(stm.getSubject().getNameSpace());
            em.setReferenceContext(referenceContext);

            list.add(em);
        }

        iter = model.listStatements(null, RDF.type, model.getResource(NIF20Format.NIF_PROPERTY_CONTEXT));
        Statement stm = iter.nextStatement();
        Resource contextRes = stm.getSubject().asResource();
        String text = contextRes.getProperty(model.getProperty(NIF20Format.NIF_PROPERTY_ISSTRING)).getObject().asLiteral().getString();

        return new Document(list, text);
    }

    private Model init() {
        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(nif.getBytes()), null, "TTL");
        return model;
    }

    public Map<String, String> getPrefixes() {
        Model model = init();

        Map<String, String> result = new HashMap<>();

        if (model != null) {
            result = model.getNsPrefixMap();
        }

        return result;
    }

    private List<Statement> getStatements() {

        Model model = init();

        List<Statement> result = new ArrayList<>();

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement statement = iter.nextStatement();
            result.add(statement);
        }

        return result;
    }

    private void mergePrefixes(Model model) {

        if (model != null) {
            Map<String, String> map = getPrefixes();

            if (map != null) {

                Iterator<String> it = map.keySet().iterator();

                while (it.hasNext()) {
                    String key = it.next();
                    model.setNsPrefix(key, map.get(key));
                }
            }
        }
    }

    private void mergeStatements(Model model) {

        if (model != null) {
            model.add(getStatements());
        }

    }

    public Model merge(Model model) {

        mergePrefixes(model);

        mergeStatements(model);

        return model;
    }

}
