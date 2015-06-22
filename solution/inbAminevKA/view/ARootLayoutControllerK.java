package Konstantin.Aminev.inb.ch.makery.adress.view;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import Konstantin.Aminev.inb.ch.makery.adress.VMainAppK;

public class ARootLayoutControllerK {
	private AMainAppK amainAppk;

    public void setMainApp(AMainAppK mainApp) {
        this.amainAppk = mainApp;
    }

   
    @FXML
    private void ahandleNewK() {
        amainAppk.getPersonData().clear();
        amainAppk.vsetPersonFilePathK(null);
    }

 
    @FXML
    private void ahandleOpenK() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(amainAppk.agetPrimaryStageK());

        if (file != null) {
            amainAppk.aloadPersonDataFromFileK(file);
        }
    }

    @FXML
    private void ahandleSaveK() {
        File personFile = amainAppk.agetPersonFilePathK();
        if (personFile != null) {
            amainAppk.savePersonDataToFile(personFile);
        } else {
            ahandleSaveAsK();
        }
    }

    @FXML
    private void ahandleSaveAsK() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(amainAppk.agetPrimaryStageK());

        if (file != null) {
        	
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            amainAppk.savePersonDataToFile(file);
        }
    }

    
    @FXML
    private void ahandleAboutK() {
        Dialogs.create()
            .title("AdressApp")
            .masthead("About")
            .message("Author: Aminev Kostya")
            .showInformation();
    }

 
    @FXML
    private void ahandleExitK() {
        System.exit(0);
    }
    
    @FXML
    private void ahandleShowBirthdayStatisticsK() {
      amainAppk.ashowBirthdayStatisticsK();
    }
}