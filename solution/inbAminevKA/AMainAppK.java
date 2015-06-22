package Konstantin.Aminev.inb.ch.makery.adress;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import Konstantin.Aminev.inb.ch.makery.adress.model.VPersonListWrapperK;
import Konstantin.Aminev.inb.ch.makery.adress.model.VPersonK;
import Konstantin.Aminev.inb.ch.makery.adress.view.VBirthdayStatisticsControllerK;
import Konstantin.Aminev.inb.ch.makery.adress.view.VPersonEditDialogControllerK;
import Konstantin.Aminev.inb.ch.makery.adress.view.VPersonOverviewControllerK;
import Konstantin.Aminev.inb.ch.makery.adress.view.VRootLayoutControllerK;

import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

public class AMainAppK extends Application {

	
	 private Stage aprimaryStagek;
	 private BorderPane arootLayoutk;
	 private ObservableList<APersonK> apersonDatak = FXCollections.observableArrayList();
	  
	 public AMainAppK() {
			
			vpersonDatak.add(new APersonK("Aminev", "Kostya"));
			vpersonDatak.add(new APersonK("Matveev", "Alex"));
			vpersonDatak.add(new APersonK("Mirakin", "Dima"));
			vpersonDatak.add(new APersonK("Emkin", "Vlad"));
			vpersonDatak.add(new APersonK("Popov", "Vlad"));
			vpersonDatak.add(new APersonK("Baranov", "Alex"));
			vpersonDatak.add(new APersonK("Volchkov", "Ruslan"));
			vpersonDatak.add(new APersonK("Begov", "Nazim"));
			vpersonDatak.add(new APersonK("Korovnikov", "Igor"));
					}

	 public ObservableList<APersonK> getPersonData() {
			return apersonDatak;
		}
	 
	 @Override
	    public void start(Stage primaryStage) {
	        this.aprimaryStagek = primaryStage;
	        this.aprimaryStagek.setTitle("AdressApp");
	        this.aprimaryStagek.getIcons().add(new Image("file:resources/images/address_book_32.png"));
	        ainitRootLayoutK();
	        ashowPersonOverviewK();

	    }

	    
	    public void vinitRootLayoutK() {
	    	 try {
	    	       
	    	        FXMLLoader loader = new FXMLLoader();
	    	        loader.setLocation(AMainAppK.class
	    	                .getResource("view/RootLayout.fxml"));
	    	        arootLayoutk = (BorderPane) loader.load();

	    	        
	    	        Scene scene = new Scene(arootLayoutk);
	    	        aprimaryStagek.setScene(scene);

	    	     
	    	        ARootLayoutControllerK controller = loader.getController();
	    	        controller.setMainApp(this);

	    	        aprimaryStagek.show();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }

	    	    File file = agetPersonFilePathK();
	    	    if (file != null) {
	    	        aloadPersonDataFromFileK(file);
	    	    }
	        }

	   
	    public void ashowPersonOverviewK() {
	        try {
	            FXMLLoader aloaderk = new FXMLLoader();
	            aloaderk.setLocation(AMainAppK.class.getResource("view/PersonOverview.fxml"));
	           AnchorPane apersonOverviewk = (AnchorPane) aloaderk.load();

	            arootLayoutk.setCenter(apersonOverviewk);
	            
	            APersonOverviewControllerK controller = aloaderk.getController();
	            controller.setMainApp(this);

	            aprimaryStagek.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public Stage agetPrimaryStageK() {
	        return aprimaryStagek;
	    }
	      
	    public static void main(String[] args) {
	        launch(args);
	    
	}
	    
	    
	    public boolean showPersonEditDialog(APersonK person) {
	        try {
	            
	            FXMLLoader aloaderk = new FXMLLoader();
	            aloaderk.setLocation(AMainAppK.class.getResource("view/PersonEditDialog.fxml"));
	            AnchorPane apagek = (AnchorPane) aloaderk.load();

	            Stage adialogStagek = new Stage();
	            adialogStagek.setTitle("Edit Person");
	            adialogStagek.initModality(Modality.WINDOW_MODAL);
	            adialogStagek.initOwner(aprimaryStagek);
	            Scene ascenek = new Scene(apagek);
	            adialogStagek.setScene(ascenek);

	            APersonEditDialogControllerK controller = aloaderk.getController();
	            controller.setDialogStage(adialogStagek);
	            controller.setPerson(person);

	            adialogStagek.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public File agetPersonFilePathK() {
	        Preferences aprefsk = Preferences.userNodeForPackage(AMainAppK.class);
	        String afilePathk = aprefsk.get("filePath", null);
	        if (afilePathk != null) {
	            return new File(afilePathk);
	        } else {
	            return null;
	        }
	    }

	    
	    public void asetPersonFilePathK(File file) {
	        Preferences aprefsk = Preferences.userNodeForPackage(AMainAppK.class);
	        if (file != null) {
	            aprefsk.put("filePath", file.getPath());

	            
	            aprimaryStagek.setTitle("AdressApp - " + file.getName());
	        } else {
	            aprefsk.remove("filePath");

	            
	            aprimaryStagek.setTitle("AdressApp");
	        }
	    }
	    
	    
	    public void aloadPersonDataFromFileK(File file) {
	        try {
	            JAXBContext acontextk = JAXBContext
	                    .newInstance(APersonListWrapperK.class);
	            Unmarshaller aumk = acontextk.createUnmarshaller();

	           
	            APersonListWrapperK awrapperk = (APersonListWrapperK) aumk.unmarshal(file);

	            apersonDatak.clear();
	            apersonDatak.addAll(awrapperk.getPersons());

	            
	            asetPersonFilePathK(file);

	        } catch (Exception e) {
	            Dialogs.create()
	                    .title("Error")
	                    .masthead("Could not load data from file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }

	  
	    public void savePersonDataToFile(File file) {
	        try {
	            JAXBContext acontextk = JAXBContext
	                    .newInstance(APersonListWrapperK.class);
	            Marshaller amk = acontextk.createMarshaller();
	            amk.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	         
	            APersonListWrapperK awrapperk = new APersonListWrapperK();
	            awrapperk.setPersons(apersonDatak);

	            
	            amk.marshal(awrapperk, file);

	           
	            asetPersonFilePathK(file);
	        } catch (Exception e) { 
	            Dialogs.create().title("Error")
	                    .masthead("Could not save data to file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }
	    
	    public void ashowBirthdayStatisticsK() {
	        try {
	           
	            FXMLLoader aloaderk = new FXMLLoader();
	            aloaderk.setLocation(AMainAppK.class.getResource("view/BirthdayStatistics.fxml"));
	            AnchorPane apagek = (AnchorPane) aloaderk.load();
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Birthday Statistics");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(aprimaryStagek);
	            Scene scene = new Scene(apagek);
	            dialogStage.setScene(scene);

	            ABirthdayStatisticsControllerK controller = aloaderk.getController();
	            controller.asetPersonDataK(apersonDatak);

	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}