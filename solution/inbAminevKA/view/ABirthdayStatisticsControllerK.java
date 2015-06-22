package Konstantin.Aminev.inb.ch.makery.adress.view;

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
import Konstantin.Aminev.inb.ch.makery.adress.model.VPersonK;

public class ABirthdayStatisticsControllerK {
	 @FXML
	    private BarChart<String, Integer> abarChartk;

	    @FXML
	    private CategoryAxis axAxisk;

	    private ObservableList<String> amonthNamesk = FXCollections.observableArrayList();

	    @FXML
	    private void initialize() {

	        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
	        
	        amonthNamesk.addAll(Arrays.asList(months));

	       
	        axAxisk.setCategories(vmonthNamesk);
	    }

	    public void asetPersonDataK(List<APersonK> persons) {
	        
	        int[] monthCounter = new int[12];
	        for (VPersonK p : persons) {
	            int month = p.getBirthday().getMonthValue() - 1;
	            monthCounter[month]++;
	        }

	        XYChart.Series<String, Integer> series = new XYChart.Series<>();

	        
	        for (int i = 0; i < monthCounter.length; i++) {
	            series.getData().add(new XYChart.Data<>(amonthNamesk.get(i), monthCounter[i]));
	        }

	        abarChartk.getData().add(series);
	    }
}