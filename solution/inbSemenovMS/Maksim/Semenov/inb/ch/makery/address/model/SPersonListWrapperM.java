﻿package Maksim.Semenov.inb.ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class SPersonListWrapperM {

    private List<SPersonM> persons;

    @XmlElement(name = "person")
    public List<SPersonM> getPersons() {
        return persons;
    }

    public void setPersons(List<SPersonM> persons) {
        this.persons = persons;
    }
}
