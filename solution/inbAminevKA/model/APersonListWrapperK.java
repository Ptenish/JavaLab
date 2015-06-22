package Konstantin.Aminev.inb.ch.makery.adress.model;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class APersonListWrapperK {
	
	    private List<APersonK> persons;

	    @XmlElement(name = "person")
	    public List<APersonK> getPersons() {
	        return persons;
	    }

	    public void setPersons(List<APersonK> persons) {
	        this.persons = persons;
	    }
}