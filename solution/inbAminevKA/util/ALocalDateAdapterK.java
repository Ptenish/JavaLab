package Konstantin.Aminev.inb.ch.makery.adress.util;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ALocalDateAdapterK extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String a) throws Exception {
        return LocalDate.parse(a);
    }

    @Override
    public String marshal(LocalDate a) throws Exception {
        return a.toString();
    }
}