package Konstantin.Aminev.inb.ch.makery.adress.view;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Konstantin.Aminev.inb.ch.makery.adress.VMainAppK;
import Konstantin.Aminev.inb.ch.makery.adress.model.VPersonK;
import Konstantin.Aminev.inb.ch.makery.adress.util.VDateUtilK;

public class APersonOverviewControllerK {
    @FXML
    private TableView<APersonK> personTable;
    @FXML
    private TableColumn<APersonK, String> firstNameColumn;
    @FXML
    private TableColumn<APersonK, String> secondNameColumn;
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private AMainAppK amainAppk;

    public APersonOverviewControllerK() {
    }

    @FXML
    private void initialize() {
    	
        firstNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().firstNameProperty());
        secondNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().lastNameProperty());        
       
        ashowPersonDetailsK(null);
        
		personTable .getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> ashowPersonDetailsK(newValue));
    }

   
    public void setMainApp(AMainAppK mainApp) {
        this.amainAppk = mainApp;        
        personTable.setItems(mainApp.getPersonData());
    }
 
    private void ashowPersonDetailsK(APersonK person) {
    	if (person != null) {
    		
    		firstNameLabel.setText(person.getFirstName());
    		lastNameLabel.setText(person.getLastName());
    		streetLabel.setText(person.getStreet());
    		postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
    		cityLabel.setText(person.getCity());
    		birthdayLabel.setText(ADateUtilK.aformatK(person.getBirthday()));
    		
    	} else {
    		
    		firstNameLabel.setText("");
    		lastNameLabel.setText("");
    		streetLabel.setText("");
    		postalCodeLabel.setText("");
    		cityLabel.setText("");
    		birthdayLabel.setText("");
    	}
    }
    
    @FXML
    private void ahandleDeletePersonK() {
        int aselectedIndexk = personTable.getSelectionModel().getSelectedIndex();
        if (aselectedIndexk >= 0) {
            personTable.getItems().remove(aselectedIndexk);
        } else {
        
            Dialogs.create().title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
    
    @FXML
	private void ahandleNewPersonK() {
		APersonK tempPerson = new APersonK();
		boolean okClicked = amainAppk.showPersonEditDialog(tempPerson);
		if (okClicked) {
			amainAppk.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void ahandleEditPersonK() {
		APersonK selectedPerson = personTable.getSelectionModel()
				.getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = amainAppk.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				ashowPersonDetailsK(selectedPerson);
			}

		} else {
		
			Dialogs.create().title("No Selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}
}