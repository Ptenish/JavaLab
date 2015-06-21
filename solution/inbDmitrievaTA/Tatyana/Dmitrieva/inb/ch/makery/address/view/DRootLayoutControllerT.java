package Tatyana.Dmitrieva.inb.ch.makery.adress.view;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import Tatyana.Dmitrieva.inb.ch.makery.adress.DMainAppT;

public class DRootLayoutControllerM {
	private DMainAppT DMainAppT;

    public void setMainApp(DMainAppT mainApp) {
        this.DMainAppT = mainApp;
    }

   
    @FXML
    private void dhandleNewT() {
        DMainAppT.getPersonData().clear();
        DMainAppT.dsetPersonFilePathT(null);
    }

 
    @FXML
    private void dhandleOpenT() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(DMainAppT.dgetPrimaryStageT());

        if (file != null) {
            DMainAppT.dloadPersonDataFromFileT(file);
        }
    }

    @FXML
    private void dhandleSaveT() {
        File personFile = DMainAppT.dgetPersonFilePathT();
        if (personFile != null) {
            DMainAppT.savePersonDataToFile(personFile);
        } else {
            dhandleSaveAsT();
        }
    }

    @FXML
    private void dhandleSaveAsT() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(DMainAppT.dgetPrimaryStageT());

        if (file != null) {
        	
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            DMainAppT.savePersonDataToFile(file);
        }
    }

    
    @FXML
    private void dhandleAboutT() {
        Dialogs.create()
            .title("AdressApp")
            .masthead("About")
            .message("Author: Dmitrieva Tatyana")
            .showInformation();
    }

 
    @FXML
    private void dhandleExitT() {
        System.exit(0);
    }
    
    @FXML
    private void dhandleShowBirthdayStatisticsT() {
      DMainAppT.dshowBirthdayStatisticsT();
    }
}