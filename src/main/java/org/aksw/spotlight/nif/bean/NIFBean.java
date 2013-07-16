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


package org.aksw.spotlight.nif.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NIFBean implements Serializable {

    private String URL = "";

    private String content = "";

    private Integer offset = 0;

    private Integer size = 0;

    private List<String> resourceTypes = new ArrayList<String>(1);

    private String referenceContextURL = "";

    /**
     * NIFBean URL
     */
    public String getURL() {
        return URL.concat("#char=").concat(getOffset().toString()).concat(",").concat(getEndIndex().toString());
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Text offset
     */
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * Text size
     */
    public Integer getSize() {
        return size;
    }


    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * rdf:type
     */
    public List<String> getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(List<String> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    /**
     * NIF content
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Resource context reference
     */
    public String getReferenceContextURL() {
        return referenceContextURL;
    }

    public void setReferenceContextURL(String referenceContextURL) {
        this.referenceContextURL = referenceContextURL;
    }

    public Integer getEndIndex() {
        return getOffset() + getSize();
    }
}
