package Tatyana.Dmitrieva.inb.ch.makery.adress.model;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class DPersonListWrapperT {
	
	    private List<DPersonT> persons;

	    @XmlElement(name = "person")
	    public List<DPersonT> getPersons() {
	        return persons;
	    }

	    public void setPersons(List<DPersonT> persons) {
	        this.persons = persons;
	    }
}
