package Tatyana.Dmitrieva.inb.ch.makery.adress.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import Tatyana.Dmitrieva.inb.ch.makery.adress.model.DPersonT;

public class DBirthdayStatisticsControllerT {
	 @FXML
	    private BarChart<String, Integer> dbarChartt;

	    @FXML
	    private CategoryAxis dxAxist;

	    private ObservableList<String> dmonthNamest = FXCollections.observableArrayList();

	    @FXML
	    private void initialize() {

	        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
	        
	        dmonthNamest.addAll(Arrays.asList(months));

	       
	        dxAxist.setCategories(dmonthNamest);
	    }

	    public void dsetPersonDataT(List<DPersonT> persons) {
	        
	        int[] monthCounter = new int[12];
	        for (DPersonT p : persons) {
	            int month = p.getBirthday().getMonthValue() - 1;
	            monthCounter[month]++;
	        }

	        XYChart.Series<String, Integer> series = new XYChart.Series<>();

	        
	        for (int i = 0; i < monthCounter.length; i++) {
	            series.getData().add(new XYChart.Data<>(dmonthNamest.get(i), monthCounter[i]));
	        }

	        dbarChartt.getData().add(series);
	    }
}