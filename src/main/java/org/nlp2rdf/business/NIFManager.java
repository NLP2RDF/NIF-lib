/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.nlp2rdf.business;



import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.prefix.ModelPrefix;
import org.nlp2rdf.property.NIFProperty;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

public class NIFManager implements ModelPrefix, NIFProperty {

    private Model model;
    private List<NIFBean> beans;

    private NIFManager(List<NIFBean> beans) {
        this.model = ModelFactory.createDefaultModel();
        this.beans = beans;
        build();
    }

    public static final NIFManager build(List<NIFBean> beans) {
        NIFManager result = new NIFManager(beans);
        return result;
    }

    /**
     * Prefixes
     */
    private void setPrefixes() {
        model.setNsPrefix(RDF_LABEL, RDF_CORE);
        model.setNsPrefix(ITSRDF, ITSRDF_CORE);
        model.setNsPrefix(NIF, NIF_CORE);
    }

    /**
     * Add RFC5147 property
     *
     * @param root
     */
    private void addRfc5147Format(Resource root) {
        Property type = model.createProperty(RDF_CORE + TYPE_LABEL);
        Property rfc5147 = model.createProperty(NIF_CORE + NIF_RFC5147);
        model.add(root,type, rfc5147);
    }


    /**
     * Add begin property
     *
     * @param root
     * @param bean
     */
    private void addBeginIndex(Resource root, NIFBean bean) {
        Property beginIndex = model.createProperty(NIF_CORE + NIF_BEGIN_INDEX);
        model.add(root, beginIndex, bean.getOffset().toString());
    }

    /**
     * Add end property
     *
     * @param root
     * @param bean
     */
    private void addEndIndex(Resource root, NIFBean bean) {
        Property endIndex = model.createProperty(NIF_CORE + NIF_ENDINDEX);
        model.add(root, endIndex, bean.getEndIndex().toString());
    }


    private void addIdentRef(Resource root, NIFBean bean) {
        Property identRef = model.createProperty(ITSRDF_CORE + IDENT_REF);
        model.add(root, identRef, model.createResource(bean.getResource()));
    }

    /**
     * Add types
     *
     * @param root
     * @param bean
     */
    private void addTypes(Resource root, NIFBean bean) {
        if (!bean.getResourceTypes().isEmpty()) {
            Iterator<String> itTypes = bean.getResourceTypes().iterator();

            Property type = model.createProperty(ITSRDF_CORE + CLASS_REF);

            while (itTypes.hasNext()) {
                String dbpediaType = itTypes.next();
                model.add(root, type, model.createResource(dbpediaType) );
            }
        }
    }

    /**
     * Build the complete model
     */
    private void build() {

        Iterator it = beans.iterator();
        setPrefixes();

        while (it.hasNext()) {
            NIFBean bean = (NIFBean) it.next();
            Resource root = model.createResource(bean.getURL());
            addRfc5147Format(root);
            addBeginIndex(root, bean);
            addEndIndex(root, bean);
            addTypes(root, bean);
            addIdentRef(root,bean);
        }

    }

    /**
     * Return the content as RDF XML
     *
     * @return
     */
    public String getRDFxml() {
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
    public String getNTriples() {
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
    public String getTurtle() {
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
