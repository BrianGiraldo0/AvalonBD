package com.uniquindio.avalon.application;
	
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.uniquindio.avalon.database.Database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;


public class Main extends Application {
	
	public static Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;

		Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Login.fxml"));

        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #FFFFFF");
        primaryStage.setScene(scene);
        primaryStage.show();
  
	}
}
	

