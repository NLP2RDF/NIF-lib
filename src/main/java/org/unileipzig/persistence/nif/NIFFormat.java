package org.unileipzig.persistence.nif;


public interface NIFFormat {

    void accept(NIFVisitor visitor);

}
