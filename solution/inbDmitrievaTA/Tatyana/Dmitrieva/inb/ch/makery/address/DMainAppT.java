package Tatyana.Dmitrieva.inb.ch.makery.adress;

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
import Tatyana.Dmitrieva.inb.ch.makery.adress.model.DPersonListWrapperT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.model.DPersonT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.view.DBirthdayStatisticsControllerT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.view.DPersonEditDialogControllerrT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.view.DPersonOverviewControllerT;
import Tatyana.Dmitrieva.inb.ch.makery.adress.view.DRootLayoutControllerT;

import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

public class DMainAppT extends Application {

	
	 private Stage dprimaryStaget;
	 private BorderPane drootLayoutt;
	 private ObservableList<DPersonT> dpersonDatatt = FXCollections.observableArrayList();
	  
	 public DMainAppT() {
			
			dpersonDatatt.add(new DPersonT("Hans", "Muster"));
			dpersonDatatt.add(new DPersonT("Ruth", "Mueller"));
			dpersonDatatt.add(new DPersonT("Heinz", "Kurz"));
			dpersonDatatt.add(new DPersonT("Cornelia", "Meier"));
			dpersonDatatt.add(new DPersonT("Werner", "Meyer"));
			dpersonDatatt.add(new DPersonT("Lydia", "Kunz"));
			dpersonDatatt.add(new DPersonT("Anna", "Best"));
			dpersonDatatt.add(new DPersonT("Stefan", "Meier"));
			dpersonDatatt.add(new DPersonT("Martin", "Mueller"));
					}

	 public ObservableList<DPersonT> getPersonData() {
			return dpersonDatatt;
		}
	 
	 @Override
	    public void start(Stage primaryStage) {
	        this.dprimaryStaget = primaryStage;
	        this.dprimaryStaget.setTitle("AdressApp");
	        this.dprimaryStaget.getIcons().add(new Image("file:resources/images/address_book_32.png"));
	        dinitRootLayoutT();
	        dshowPersonOverviewT();

	    }

	    
	    public void dinitRootLayoutT() {
	    	 try {
	    	       
	    	        FXMLLoader loader = new FXMLLoader();
	    	        loader.setLocation(DMainAppT.class
	    	                .getResource("view/RootLayoute.fxml"));
	    	        drootLayoutt = (BorderPane) loader.load();

	    	        
	    	        Scene scene = new Scene(drootLayoutt);
	    	        dprimaryStaget.setScene(scene);

	    	     
	    	        DRootLayoutControllerT controller = loader.getController();
	    	        controller.setMainApp(this);

	    	        dprimaryStaget.show();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }

	    	    File file = dgetPersonFilePathT();
	    	    if (file != null) {
	    	        dloadPersonDataFromFileT(file);
	    	    }
	        }

	   
	    public void dshowPersonOverviewT() {
	        try {
	            FXMLLoader dloadert = new FXMLLoader();
	            dloadert.setLocation(DMainAppT.class.getResource("view/PersonOverviev.fxml"));
	           AnchorPane dpersonOverviewt = (AnchorPane) dloadert.load();

	            drootLayoutt.setCenter(dpersonOverviewt);
	            
	            DPersonOverviewControllerT controller = dloadert.getController();
	            controller.setMainApp(this);

	            dprimaryStaget.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public Stage dgetPrimaryStageT() {
	        return dprimaryStaget;
	    }
	      
	    public static void main(String[] args) {
	        launch(args);
	    
	}
	    
	    
	    public boolean showPersonEditDialog(DPersonT person) {
	        try {
	            
	            FXMLLoader dloadert = new FXMLLoader();
	            dloadert.setLocation(DMainAppT.class.getResource("view/PersonEditDialogs.fxml"));
	            AnchorPane dpaget = (AnchorPane) dloadert.load();

	            Stage ddialogStaget = new Stage();
	            ddialogStaget.setTitle("Edit Person");
	            ddialogStaget.initModality(Modality.WINDOW_MODAL);
	            ddialogStaget.initOwner(dprimaryStaget);
	            Scene dscenet = new Scene(dpaget);
	            ddialogStaget.setScene(dscenet);

	            DPersonEditDialogControllerrT controller = dloadert.getController();
	            controller.setDialogStage(ddialogStaget);
	            controller.setPerson(person);

	            ddialogStaget.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public File dgetPersonFilePathT() {
	        Preferences dprefst = Preferences.userNodeForPackage(DMainAppT.class);
	        String dfilePatht = dprefst.get("filePath", null);
	        if (dfilePatht != null) {
	            return new File(dfilePatht);
	        } else {
	            return null;
	        }
	    }

	    
	    public void dsetPersonFilePathT(File file) {
	        Preferences dprefst = Preferences.userNodeForPackage(DMainAppT.class);
	        if (file != null) {
	            dprefst.put("filePath", file.getPath());

	            
	            dprimaryStaget.setTitle("AddressApp - " + file.getName());
	        } else {
	            dprefst.remove("filePath");

	            
	            dprimaryStaget.setTitle("AdressApp");
	        }
	    }
	    
	    
	    public void dloadPersonDataFromFileT(File file) {
	        try {
	            JAXBContext dcontextt = JAXBContext
	                    .newInstance(DPersonListWrapperT.class);
	            Unmarshaller dumt = dcontextt.createUnmarshaller();

	           
	            DPersonListWrapperT dwrappert = (DPersonListWrapperT) dumt.unmarshal(file);

	            dpersonDatatt.clear();
	            dpersonDatatt.addAll(dwrappert.getPersons());

	            
	            dsetPersonFilePathT(file);

	        } catch (Exception e) {
	            Dialogs.create()
	                    .title("Error")
	                    .masthead("Could not load data from file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }

	  
	    public void savePersonDataToFile(File file) {
	        try {
	            JAXBContext dcontextt = JAXBContext
	                    .newInstance(DPersonListWrapperT.class);
	            Marshaller dmt = dcontextt.createMarshaller();
	            dmt.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	         
	            DPersonListWrapperT dwrappert = new DPersonListWrapperT();
	            dwrappert.setPersons(dpersonDatatt);

	            
	            dmt.marshal(dwrappert, file);

	           
	            dsetPersonFilePathT(file);
	        } catch (Exception e) { 
	            Dialogs.create().title("Error")
	                    .masthead("Could not save data to file:\n" + file.getPath())
	                    .showException(e);
	        }
	    }
	    
	    public void dshowBirthdayStatisticsT() {
	        try {
	           
	            FXMLLoader dloadert = new FXMLLoader();
	            dloadert.setLocation(DMainAppT.class.getResource("view/BirthdayStatistics.fxml"));
	            AnchorPane dpaget = (AnchorPane) dloadert.load();
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Birthday Statistics");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(dprimaryStaget);
	            Scene scene = new Scene(dpaget);
	            dialogStage.setScene(scene);

	            DBirthdayStatisticsControllerT controller = dloadert.getController();
	            controller.dsetPersonDatat(dpersonDatatt);

	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}