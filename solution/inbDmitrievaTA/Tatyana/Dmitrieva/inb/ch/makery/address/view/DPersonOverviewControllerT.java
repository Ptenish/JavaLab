package Tatyana.Dmitrieva.inb.ch.makery.adress.view;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Tatyana.Dmitrieva.inb.ch.makery.adress.DMainAppT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.model.DPersonT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.util.DDateUtilT;

public class DPersonOverviewControllerlT {
    @FXML
    private TableView<DPersonT> personTable;
    @FXML
    private TableColumn<DPersonT, String> firstNameColumn;
    @FXML
    private TableColumn<DPersonT, String> secondNameColumn;
    
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

    private DMainAppT DMainAppT;

    public DPersonOverviewControllerlT() {
    }

    @FXML
    private void initialize() {
    	
        firstNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().firstNameProperty());
        secondNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().lastNameProperty());        
       
        dshowPersonDetailsT(null);
        
		personTable .getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> dshowPersonDetailsT(newValue));
    }

   
    public void setMainApp(DMainAppT mainApp) {
        this.DMainAppT = mainApp;        
        personTable.setItems(mainApp.getPersonData());
    }
 
    private void dshowPersonDetailsT(DPersonT person) {
    	if (person != null) {
    		
    		firstNameLabel.setText(person.getFirstName());
    		lastNameLabel.setText(person.getLastName());
    		streetLabel.setText(person.getStreet());
    		postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
    		cityLabel.setText(person.getCity());
    		birthdayLabel.setText(DDateUtilT.dformatT(person.getBirthday()));
    		
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
    private void dhandleDeletePersonT() {
        int dselectedIndext = personTable.getSelectionModel().getSelectedIndex();
        if (dselectedIndext >= 0) {
            personTable.getItems().remove(dselectedIndext);
        } else {
        
            Dialogs.create().title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
    
    @FXML
	private void handleNewPersonM() {
		DPersonT tempPerson = new DPersonT();
		boolean okClicked = DMainAppT.showPersonEditDialog(tempPerson);
		if (okClicked) {
			DMainAppT.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void dhandleEditPersonT() {
		DPersonT selectedPerson = personTable.getSelectionModel()
				.getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = DMainAppT.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				dshowPersonDetailsT(selectedPerson);
			}

		} else {
		
			Dialogs.create().title("No Selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}
}