package Tatyana.Dmitrieva.inb.ch.makery.adress.view;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import Tatyana.Dmitrieva.inb.ch.makery.adress.model.DPersonT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.util.DDateUtilT;
public class DPersonEditDialogControllerT {
	
	    @FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    @FXML
	    private TextField streetField;
	    @FXML
	    private TextField postalCodeField;
	    @FXML
	    private TextField cityField;
	    @FXML
	    private TextField birthdayField;


	    private Stage ddialogStaget;
	    private DPersonT DPersonT;
	    private boolean dokClickedt = false;

	  
	    @FXML
	    private void initialize() {
	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.ddialogStaget = dialogStage;
	    }

	    public void setPerson(DPersonT person) {
	        this.DPersonT = person;

	        firstNameField.setText(person.getFirstName());
	        lastNameField.setText(person.getLastName());
	        streetField.setText(person.getStreet());
	        postalCodeField.setText(Integer.toString(person.getPostalCode()));
	        cityField.setText(person.getCity());
	        birthdayField.setText(DDateUtilT.dformatT(person.getBirthday()));
	        birthdayField.setPromptText("dd.mm.yyyy");
	    }

	
	    public boolean isOkClicked() {
	        return dokClickedt;
	    }

	    @FXML
	    private void handleOk() {
	        if (disInputValidT()) {
	            DPersonT.setFirstName(firstNameField.getText());
	            DPersonT.setLastName(lastNameField.getText());
	            DPersonT.setStreet(streetField.getText());
	            DPersonT.setPostalCode(Integer.parseInt(postalCodeField.getText()));
	            DPersonT.setCity(cityField.getText());
	            DPersonT.setBirthday(DDateUtilT.dparseT(birthdayField.getText()));

	            dokClickedt = true;
	            ddialogStaget.close();
	        }
	    }

	    @FXML
	    private void handleCancel() {
	        ddialogStaget.close();
	    }

	   
	    private boolean disInputValidT() {
	        String errorMessage = "";

	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        if (streetField.getText() == null || streetField.getText().length() == 0) {
	            errorMessage += "No valid street!\n"; 
	        }

	        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
	            errorMessage += "No valid postal code!\n"; 
	        } else {
	           
	            try {
	                Integer.parseInt(postalCodeField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid postal code (must be an integer)!\n"; 
	            }
	        }

	        if (cityField.getText() == null || cityField.getText().length() == 0) {
	            errorMessage += "No valid city!\n"; 
	        }

	        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
	            errorMessage += "No valid birthday!\n";
	        } else {
	            if (!DDateUtilT.dvalidDateT(birthdayField.getText())) {
	                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
	            }
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            Dialogs.create()
	                .title("Invalid Fields")
	                .masthead("Please correct invalid fields")
	                .message(errorMessage)
	                .showError();
	            return false;
	        }
	    }
	}