package org.unileipzig.persistence.nif;

import org.apache.jena.rdf.model.Model;
import org.unileipzig.persistence.nif.impl.NIFContext;
import org.unileipzig.persistence.nif.impl.NIFBean;

public interface NIFAnnotationUnit extends NIF21Format {

      void add(Model model, NIFBean entity);

}
