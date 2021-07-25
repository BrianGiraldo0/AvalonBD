package com.uniquindio.avalon.controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.uniquindio.avalon.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReporteController {
	
	private ObservableList<Object> listTable;
	

    @FXML
    private Label opcionLabel;
	
	@FXML
    private TableColumn<?, ?> column1;

    @FXML
    private TableView<Object> tablaListado;

    @FXML
    private TableColumn<?, ?> column5;

    @FXML
    private TableColumn<?, ?> column4;

    @FXML
    private ComboBox<String> tipoReporteCombo;

    @FXML
    private TableColumn<?, ?> column3;

    @FXML
    private TableColumn<?, ?> column2;

    @FXML
    private ComboBox<String> reporteCombo;

    @FXML
    private HBox panelSuperior;

    @FXML
    private Label labelSuperiorListado;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfBuscar;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private Button generarPDFbtn;

    @FXML
    private Label lblFecha;

    @FXML
    private DatePicker datePicker;

    

    @FXML
    private Button generarReportebtn;
    
    private String tipo;
    private String opcion;
    
    @FXML
    void initialize () {
    	datePicker.setVisible(false);
		lblFecha.setVisible(false);
    	opcionLabel.setVisible(false);
    	opcionLabel.setWrapText(true);
    	
    	tipoReporteCombo.setItems(FXCollections.observableArrayList("Simple","Intermedio","Complejo" ));
    	
    	tipoReporteCombo.setOnAction ( e-> {
    		
    			String  tipoReporte = 	tipoReporteCombo.getValue();
    		    
    	    	if (tipoReporte.equals("Simple")) {
    	    		
    	    		
    	    		
    	    		reporteCombo.getItems().clear();
    	    		ArrayList <String> reportesSimples = new ArrayList<>();
    	    		
    	    		reportesSimples.add("1.  Listado de computadores que están ocupados");
    	    		reportesSimples.add("2. Listado de clientes con saldo recargado");
    	    		reportesSimples.add("3. Clases agendadas para una fecha especifica");
    	    		
    	    		
    	    		
    	    		
    	    		reporteCombo.setItems(FXCollections.observableArrayList(reportesSimples));
    	    		
    	    		
    	    		
    	    	} if (tipoReporte.equals("Intermedio")) {
    	    		reporteCombo.getItems().clear();
    	    		ArrayList <String> reportesIntermedios = new ArrayList<>();
    	    		
    	    		reportesIntermedios.add("1. Nickname de los clientes que recibirán clases en una fecha especifica");
    	    		reportesIntermedios.add("2. Listado de clientes que compraron productos en los últimos 6 meses e hicieron recargas de más de 5000 mil pesos");
    	    		reportesIntermedios.add("3. Obtener los reportes de mantenimiento realizados a los computadores por categoría");
    	    		reportesIntermedios.add("4. Listar por empleado las recargas realizadas por mayor valor en el último mes");
    	    		reporteCombo.setItems(FXCollections.observableArrayList(reportesIntermedios));
    	    		
    	    	} 
    	    	
    	    	if (tipoReporte.equals("Complejo")) {
    	    		reporteCombo.getItems().clear();
    	    		ArrayList <String> reportesComplejos = new ArrayList<>();
    	    		
    	    		reportesComplejos.add("1. Listar los nickname de los clientes que hayan solicitado préstamos en los últimos 3 meses exceptuando aquellos que no hayan hecho recargas ");
    	    		reportesComplejos.add("2. Listar los productos más vendidos con un precio mayor a 100000 que no sean ofertados por el proveedor Razer");
    	    		reportesComplejos.add("3. Mostrar por empleado la cantidad de recargas realizadas. Contar solo aquellas recargas realizadas a clientes que hayan solicitado por lo menos una clase");
    	    		reporteCombo.setItems(FXCollections.observableArrayList(reportesComplejos));
    	    		
    	    		
    	    	}
    		
    		
    		
    	});
    	
    	reporteCombo.setOnAction(e ->{
    		
    		
    		
    
    		
    		
    		if (reporteCombo.getValue()!=null) {
    		
    		
    		String valor = reporteCombo.getValue();
    		
    		opcion = valor.substring(0, 1);
    		tipo = tipoReporteCombo.getValue();
    		
    		//reporteCombo.setValue(valor.substring(0, 1));
    		
    		if((opcion.equals("1") && tipo.equals("Intermedio")) || (opcion.equals("3") && tipo.equals("Simple")) ) {
    			datePicker.setVisible(true);
    			lblFecha.setVisible(true);
    		}else {
    			datePicker.setVisible(false);
    			lblFecha.setVisible(false);
    		}
    			
    
    		opcionLabel.setVisible(true);
    		opcionLabel.setText(valor);
    		}
    		
    		
    		
    	});
    	
    	
    	column1.setVisible(false);
    	column2.setVisible(false);
    	column3.setVisible(false);
    	column4.setVisible(false);
    	column5.setVisible(false);
    	
    	generarReportebtn.setOnMouseClicked(e->{
    		editarColumnasTabla(tipo,opcion);
    	});
    	generarPDFbtn.setOnMouseClicked(e->{
    		Stage stage = new Stage();
    		Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/avalon/reportes/template.fxml"));
				root = loader.load();
				Scene scene = new Scene(root);
				ReportePDFController control = loader.getController();
				if((opcion.equals("1") && tipo.equals("Intermedio")) || (opcion.equals("3") && tipo.equals("Simple")) )
					control.setDate(datePicker.getValue());
					
				control.setOpcion(opcion);
				control.setTipo(tipo);
				control.init();
	            root.setStyle("-fx-background-color: #FFFFFF");
	            stage.setScene(scene);
	            stage.show();
	    		
	            PrinterJob job = PrinterJob.createPrinterJob();
	            if(job != null){
		            job.printPage(root);
		            job.endJob();
	            }
	            
	            stage.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            
            
    	});
    	
    	
    }
    
    
    
    private void editarColumnasTabla(String tipo,String opcion) {
    	
    	
    	if (tipo.equals("Simple")) {
    	
    	if (opcion.equals("1")) {
    		
    		
    	
    	column1.setText("Código");
    	column2.setText("Estado");
    	column1.setVisible(true);
    	column2.setVisible(true);
    	column3.setVisible(false);
    	column4.setVisible(false);
    	column5.setVisible(false);
    	column1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	column2.setCellValueFactory(new PropertyValueFactory<>("ocupado"));
    	
    	try {
			listTable = FXCollections.observableArrayList(Database.reporteSimple1());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tablaListado.setItems(listTable);
    	
    	}
    	
    	

    	if (opcion.equals("2")) {
    		column1.setText("Nickname");
        	column2.setText("Cedula");
        	column3.setText("Correo");
        	column4.setText("Saldo");
        	column1.setVisible(true);
        	column2.setVisible(true);
        	column3.setVisible(true);
        	column4.setVisible(true);
        	column5.setVisible(false);
        	column1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
	    	column2.setCellValueFactory(new PropertyValueFactory<>("cedula"));
	    	column3.setCellValueFactory(new PropertyValueFactory<>("correo"));
	    	column4.setCellValueFactory(new PropertyValueFactory<>("saldo"));
	    	
	    	try {
				listTable = FXCollections.observableArrayList(Database.reporteSimple2());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	tablaListado.setItems(listTable);
    	}
    	
    	if (opcion.equals("3")) {
    		column1.setText("Código clase");
        	column2.setText("Fecha");
        	column1.setVisible(true);
        	column2.setVisible(true);
        	column3.setVisible(false);
        	column4.setVisible(false);
        	column5.setVisible(false);
        	column1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    	column2.setCellValueFactory(new PropertyValueFactory<>("fecha"));
	    	
	    	try {
				listTable = FXCollections.observableArrayList(Database.reporteSimple3(datePicker.getValue().toString()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	tablaListado.setItems(listTable);
    	}
    	
    	}
    	
    	if (tipo.equals("Intermedio")) {
    		
    		if (opcion.equals("1")) {
    		
    		column1.setText("Nickname");
        	column2.setText("Codigo clase");
        	column3.setText("Fecha");
        	column1.setVisible(true);
        	column2.setVisible(true);
        	column3.setVisible(true);
        	column4.setVisible(false);
        	column5.setVisible(false);
        	
        	column1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
	    	column2.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    	column3.setCellValueFactory(new PropertyValueFactory<>("fecha"));
	    	
	    	try {
				listTable = FXCollections.observableArrayList(Database.reporteIntermedio1(datePicker.getValue().toString()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	tablaListado.setItems(listTable);
	    	
	    	}
        	
  
    		
    		if (opcion.equals("2")) {
    			column1.setText("Nickname");
            	column2.setText("Cedula");
            	column3.setText("Correo");
            	column4.setText("Saldo");
            	column1.setVisible(true);
            	column2.setVisible(true);
            	column3.setVisible(true);
            	column4.setVisible(true);
            	column5.setVisible(false);
            	column1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
		    	column2.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		    	column3.setCellValueFactory(new PropertyValueFactory<>("correo"));
		    	column4.setCellValueFactory(new PropertyValueFactory<>("saldo"));
            	try {
					listTable = FXCollections.observableArrayList(Database.reporteIntermedio2(LocalDate.now().toString(), restarMeses(6, LocalDate.now()).toString()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tablaListado.setItems(listTable);
		    	
    		}
    		
    		if (opcion.equals("3")) {
    			column1.setText("Categoria");
            	column2.setText("Codigo pc");
            	column3.setText("Codigo mantenimiento");
            	column4.setText("Empleado");
            	column5.setText("Observación");
            	column1.setVisible(true);
            	column2.setVisible(true);
            	column3.setVisible(true);
            	column4.setVisible(true);
            	column5.setVisible(true);
            	column1.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		    	column2.setCellValueFactory(new PropertyValueFactory<>("codigoPC"));
		    	column3.setCellValueFactory(new PropertyValueFactory<>("codigoMantenimiento"));
		    	column4.setCellValueFactory(new PropertyValueFactory<>("empleado"));
		    	column5.setCellValueFactory(new PropertyValueFactory<>("observacion"));
            	try {
					listTable = FXCollections.observableArrayList(Database.reporteIntermedio3());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tablaListado.setItems(listTable);
    		}
    		
    		if (opcion.equals("4")) {
    			column1.setText("Cedula");
            	column2.setText("Empleado nombre");
            	column3.setText("Recargas");
            	column1.setVisible(true);
            	column2.setVisible(true);
            	column3.setVisible(true);
            	column4.setVisible(false);
            	column5.setVisible(false);
            	column1.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		    	column2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		    	column3.setCellValueFactory(new PropertyValueFactory<>("maximaRecarga"));
            	try {
					listTable = FXCollections.observableArrayList(Database.reporteIntermedio4(LocalDate.now().toString(), restarMeses(1, LocalDate.now()).toString()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tablaListado.setItems(listTable);
    		}
    		
    	}
    	
    	if (tipo.equals("Complejo")) {
    		
    		if (opcion.endsWith("1")) {
    			column1.setText("Nickname");
            	column1.setVisible(true);
            	column2.setVisible(false);
            	column3.setVisible(false);
            	column4.setVisible(false);
            	column5.setVisible(false);
            	column1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
            	try {
					listTable = FXCollections.observableArrayList(Database.reporteComplejo1(LocalDate.now().toString(), restarMeses(3, LocalDate.now()).toString()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tablaListado.setItems(listTable);
    		}
    		
    		if (opcion.equals("2")) {
    			column1.setText("Producto");
            	column2.setText("Cantidad de ventas");
            	column1.setVisible(true);
            	column2.setVisible(true);
            	column3.setVisible(false);
            	column4.setVisible(false);
            	column5.setVisible(false);
            	column1.setCellValueFactory(new PropertyValueFactory<>("productoCodigo"));
            	column2.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            	try {
					listTable = FXCollections.observableArrayList(Database.reporteComplejo2());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tablaListado.setItems(listTable);
            	
    			
    		}
    		
    		if (opcion.equals("3")) {
    			column1.setText("Nombre");
            	column2.setText("Cantidad de recargas");
            	column1.setVisible(true);
            	column2.setVisible(true);
            	column3.setVisible(false);
            	column4.setVisible(false);
            	column5.setVisible(false);
            	column1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            	column2.setCellValueFactory(new PropertyValueFactory<>("cantidadRecargas"));
            	try {
					listTable = FXCollections.observableArrayList(Database.reporteComplejo3());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	tablaListado.setItems(listTable);
    		
    		}
    		
    		
    	}
    	
    
    	
    }
    
    public LocalDate restarMeses(int meses, LocalDate fecha) {
		 
		 LocalDate resultante = null;
		 
		  resultante = fecha.minusMonths(meses);
		 
		 return resultante;
	 }
    
    @FXML
    void keyListener(ActionEvent event) {

    }

}
