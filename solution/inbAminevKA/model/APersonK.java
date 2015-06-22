package Konstantin.Aminev.inb.ch.makery.adress.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Konstantin.Aminev.inb.ch.makery.adress.util.VLocalDateAdapterK;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class APersonK {

    private final StringProperty afirstNamek;
    private final StringProperty alastNamek;
    private final StringProperty astreetk;
    private final IntegerProperty apostalCodek;
    private final StringProperty acityk;
    private final ObjectProperty<LocalDate> abirthdayk;

    public APersonK() {
		this(null, null);
	}

    public APersonK(String firstName, String lastName) {
        this.afirstNamek = new SimpleStringProperty(firstName);
        this.alastNamek = new SimpleStringProperty(lastName);
        this.astreetk = new SimpleStringProperty("some street");
        this.apostalCodek = new SimpleIntegerProperty(1234);
        this.acityk = new SimpleStringProperty("some city");
        this.abirthdayk = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return afirstNamek.get();
    }

    public void setFirstName(String firstName) {
        this.afirstNamek.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return afirstNamek;
    }

    public String getLastName() {
        return alastNamek.get();
    }

    public void setLastName(String lastName) {
        this.alastNamek.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return alastNamek;
    }

    public String getStreet() {
        return astreetk.get();
    }

    public void setStreet(String street) {
        this.astreetk.set(street);
    }

    public StringProperty streetProperty() {
        return astreetk;
    }

    public int getPostalCode() {
        return apostalCodek.get();
    }

    public void setPostalCode(int postalCode) {
        this.apostalCodek.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return apostalCodek;
    }

    public String getCity() {
        return acityk.get();
    }

    public void setCity(String city) {
        this.acityk.set(city);
    }

    public StringProperty cityProperty() {
        return acityk;
    }
    
    @XmlJavaTypeAdapter(ALocalDateAdapterK.class)
    public LocalDate getBirthday() {
    	    return abirthdayk.get();
    	}
   
    public void setBirthday(LocalDate birthday) {
        this.abirthdayk.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return abirthdayk;
    }
}