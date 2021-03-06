﻿package Maksim.Semenov.inb.ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Maksim.Semenov.inb.ch.makery.address.model.SPersonM;
import Maksim.Semenov.inb.ch.makery.address.model.SPersonListWrapperM;
import Maksim.Semenov.inb.ch.makery.address.view.SBirthdayStatisticsControllerM;
import Maksim.Semenov.inb.ch.makery.address.view.SPersonEditDialogControllerM;
import Maksim.Semenov.inb.ch.makery.address.view.SPersonOverviewControllerM;
import Maksim.Semenov.inb.ch.makery.address.view.SRootLayoutControllerM;

@SuppressWarnings("deprecation")
public class SMainAppM extends Application {

    private Stage sprimaryStagem;
    private BorderPane srootLayoutm;
    private ObservableList<SPersonM> spersonDatam = FXCollections.observableArrayList();

    public SMainAppM() {
        // Add some sample data
        spersonDatam.add(new SPersonM("Hans", "Muster"));
        spersonDatam.add(new SPersonM("Ruth", "Mueller"));
        spersonDatam.add(new SPersonM("Heinz", "Kurz"));
        spersonDatam.add(new SPersonM("Cornelia", "Meier"));
        spersonDatam.add(new SPersonM("Werner", "Meyer"));
        spersonDatam.add(new SPersonM("Lydia", "Kunz"));
        spersonDatam.add(new SPersonM("Anna", "Best"));
        spersonDatam.add(new SPersonM("Stefan", "Meier"));
        spersonDatam.add(new SPersonM("Martin", "Mueller"));
    }

    @Override
    public void start(Stage primaryStage) {
        this.sprimaryStagem = primaryStage;
        this.sprimaryStagem.setTitle("AddressApp");
        this.sprimaryStagem.getIcons().add(new Image("file:resources/images/address-book.png"));

        sinitRootLayoutM();

        sshowPersonOverviewM();
    }
  
    public void sinitRootLayoutM() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SMainAppM.class
                    .getResource("view/RootLayout.fxml"));
            srootLayoutm = (BorderPane) loader.load();

            Scene scene = new Scene(srootLayoutm);
            sprimaryStagem.setScene(scene);

            SRootLayoutControllerM controller = loader.getController();
            controller.setMainApp(this);

            sprimaryStagem.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = sgetPersonFilePathM();
        if (file != null) {
            sloadPersonDataFromFileM(file);
        }
    }

	
    public void sshowPersonOverviewM() {
        try {
            FXMLLoader sloaderm = new FXMLLoader();
            sloaderm.setLocation(SMainAppM.class.getResource("view/PersonOverview.fxml"));
            AnchorPane spersonOverviewm = (AnchorPane) sloaderm.load();
            
            srootLayoutm.setCenter(spersonOverviewm);
            
            SPersonOverviewControllerM controller = sloaderm.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public boolean showPersonEditDialog(SPersonM person) {
		try {
			
			FXMLLoader sloaderm = new FXMLLoader();
			sloaderm.setLocation(SMainAppM.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane spagem = (AnchorPane) sloaderm.load();

			Stage sdialogStagem = new Stage();
			sdialogStagem.setTitle("Edit Person");
			sdialogStagem.initModality(Modality.WINDOW_MODAL);
			sdialogStagem.initOwner(sprimaryStagem);
			Scene sscenem = new Scene(spagem);
			sdialogStagem.setScene(sscenem);

			SPersonEditDialogControllerM controller = sloaderm.getController();
			controller.setDialogStage(sdialogStagem);
			controller.setPerson(person);

			sdialogStagem.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
    
    public Stage sgetPrimaryStagem() {
    	return sprimaryStagem;
    }
    
    public ObservableList<SPersonM> getPersonData() {
    	return spersonDatam;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public File sgetPersonFilePathM() {
        Preferences sprefsm = Preferences.userNodeForPackage(SMainAppM.class);
        String sfilePathm = sprefsm.get("filePath", null);
        if (sfilePathm != null) {
            return new File(sfilePathm);
        } else {
            return null;
        }
    }


    public void ssetPersonFilePathM(File file) {
        Preferences sprefsm = Preferences.userNodeForPackage(SMainAppM.class);
        if (file != null) {
            sprefsm.put("filePath", file.getPath());

            sprimaryStagem.setTitle("AddressApp - " + file.getName());
        } else {
            sprefsm.remove("filePath");

            sprimaryStagem.setTitle("AddressApp");
        }
    }

    public void sloadPersonDataFromFileM(File file) {
        try {
            JAXBContext scontextm = JAXBContext
                    .newInstance(SPersonListWrapperM.class);
            Unmarshaller summ = scontextm.createUnmarshaller();

            SPersonListWrapperM swrapperm = (SPersonListWrapperM) summ.unmarshal(file);

            spersonDatam.clear();
            spersonDatam.addAll(swrapperm.getPersons());

            ssetPersonFilePathM(file);

        } catch (Exception e) {
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + file.getPath())
                    .showException(e);
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext scontextm = JAXBContext
                    .newInstance(SPersonListWrapperM.class);
            Marshaller smm = scontextm.createMarshaller();
            smm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            SPersonListWrapperM swrapperm = new SPersonListWrapperM();
            swrapperm.setPersons(spersonDatam);

            smm.marshal(swrapperm, file);

            ssetPersonFilePathM(file);
        } catch (Exception e) {
            Dialogs.create().title("Error")
                    .masthead("Could not save data to file:\n" + file.getPath())
                    .showException(e);
        }
    }

	public void loadPersonDataFromFile1(File file) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void sshowBirthdayStatisticsM() {
	    try {
	        FXMLLoader sloaderm = new FXMLLoader();
	        sloaderm.setLocation(SMainAppM.class.getResource("view/BirthdayStatistics.fxml"));
	        AnchorPane spagem = (AnchorPane) sloaderm.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Birthday Statistics");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(sprimaryStagem);
	        Scene scene = new Scene(spagem);
	        dialogStage.setScene(scene);

	        // Set the persons into the controller.
	        SBirthdayStatisticsControllerM controller = sloaderm.getController();
	        controller.ssetPersonDataM(spersonDatam);

	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
