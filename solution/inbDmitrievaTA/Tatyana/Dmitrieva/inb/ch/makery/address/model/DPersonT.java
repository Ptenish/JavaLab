package Tatyana.Dmitrieva.inb.ch.makery.adress.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Tatyana.Dmitrieva.inb.ch.makery.adress.util.DLocalDateAdapterT;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DPersonT {

    private final StringProperty dfirstNamet;
    private final StringProperty dlastNamet;
    private final StringProperty dstreett;
    private final IntegerProperty dpostalCodet;
    private final StringProperty dcityt;
    private final ObjectProperty<LocalDate> dbirthdayt;

    public DPersonT() {
		this(null, null);
	}

    public DPersonT(String firstName, String lastName) {
        this.DfirstNameT = new SimpleStringProperty(firstName);
        this.DlastNameT = new SimpleStringProperty(lastName);
        this.dstreett = new SimpleStringProperty("some street");
        this.dpostalCodet = new SimpleIntegerProperty(1234);
        this.dcityt = new SimpleStringProperty("some city");
        this.dbirthdayt = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return DfirstNameT.get();
    }

    public void setFirstName(String firstName) {
        this.DfirstNameT.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return DfirstNameT;
    }

    public String getLastName() {
        return DlastNameT.get();
    }

    public void setLastName(String lastName) {
        this.DlastNameT.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return DlastNameT;
    }

    public String getStreet() {
        return dstreett.get();
    }

    public void setStreet(String street) {
        this.dstreett.set(street);
    }

    public StringProperty streetProperty() {
        return dstreett;
    }

    public int getPostalCode() {
        return dpostalCodet.get();
    }

    public void setPostalCode(int postalCode) {
        this.dpostalCodet.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return dpostalCodet;
    }

    public String getCity() {
        return dcityt.get();
    }

    public void setCity(String city) {
        this.dcityt.set(city);
    }

    public StringProperty cityProperty() {
        return dcityt;
    }
    
    @XmlJavaTypeAdapter(DLocalDateAdapterT.class)
    public LocalDate getBirthday() {
    	    return dbirthdayt.get();
    	}
   
    public void setBirthday(LocalDate birthday) {
        this.dbirthdayt.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return dbirthdayt;
    }
}