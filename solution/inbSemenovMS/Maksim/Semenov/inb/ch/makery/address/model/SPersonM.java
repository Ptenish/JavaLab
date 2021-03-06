﻿package Maksim.Semenov.inb.ch.makery.address.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Maksim.Semenov.inb.ch.makery.address.util.SLocalDateAdapterM;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SPersonM {

    private final StringProperty sfirstNamem;
    private final StringProperty slastNamem;
    private final StringProperty sstreetm;
    private final IntegerProperty spostalCodem;
    private final StringProperty scitym;
    private final ObjectProperty<LocalDate> sbirthdaym;

    public SPersonM() {
        this(null, null);
    }

    public SPersonM(String firstName, String lastName) {
        this.sfirstNamem = new SimpleStringProperty(firstName);
        this.slastNamem = new SimpleStringProperty(lastName);
        this.sstreetm = new SimpleStringProperty("some street");
        this.spostalCodem = new SimpleIntegerProperty(1234);
        this.scitym = new SimpleStringProperty("some city");
        this.sbirthdaym = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return sfirstNamem.get();
    }

    public void setFirstName(String firstName) {
        this.sfirstNamem.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return sfirstNamem;
    }

    public String getLastName() {
        return slastNamem.get();
    }

    public void setLastName(String lastName) {
        this.slastNamem.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return slastNamem;
    }

    public String getStreet() {
        return sstreetm.get();
    }

    public void setStreet(String street) {
        this.sstreetm.set(street);
    }

    public StringProperty streetProperty() {
        return sstreetm;
    }

    public int getPostalCode() {
        return spostalCodem.get();
    }

    public void setPostalCode(int postalCode) {
        this.spostalCodem.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return spostalCodem;
    }

    public String getCity() {
        return scitym.get();
    }

    public void setCity(String city) {
        this.scitym.set(city);
    }

    public StringProperty cityProperty() {
        return scitym;
    }

    @XmlJavaTypeAdapter(SLocalDateAdapterM.class)
    public LocalDate getBirthday() {
        return sbirthdaym.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.sbirthdaym.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return sbirthdaym;
    }
}