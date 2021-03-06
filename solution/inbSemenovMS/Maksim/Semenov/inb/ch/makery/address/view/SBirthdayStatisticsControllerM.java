﻿package Maksim.Semenov.inb.ch.makery.address.view;

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
import Maksim.Semenov.inb.ch.makery.address.model.SPersonM;

public class SBirthdayStatisticsControllerM {

    @FXML
    private BarChart<String, Integer> sbarChartm;

    @FXML
    private CategoryAxis sxAxism;

    private ObservableList<String> smonthNamesm = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        smonthNamesm.addAll(Arrays.asList(months));

        sxAxism.setCategories(smonthNamesm);
    }

    public void ssetPersonDataM(List<SPersonM> persons) {
        int[] monthCounter = new int[12];
        for (SPersonM p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(smonthNamesm.get(i), monthCounter[i]));
        }

        sbarChartm.getData().add(series);
    }
}