package com.uniquindio.avalon.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import com.uniquindio.avalon.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;

public class ReportePDFController {

    @FXML
    private TableView<Object> tablaReporte;

    @FXML
    private TableColumn<?, ?> columReporte1;

    @FXML
    private TableColumn<?, ?> columReporte2;

    @FXML
    private TableColumn<?, ?> columReporte3;

    @FXML
    private TableColumn<?, ?> columReporte4;

    @FXML
    private TableColumn<?, ?> columReporte5;

    @FXML
    private TableColumn<?, ?> columReporte6;
    
    @FXML
    private Label lblTipoReporte;
    
    @FXML
    private Label lblFechaReporte;
    
    private String opcion;
    private String tipo;
    
    @Getter @Setter
    private LocalDate date;
    private String tipoReporte;
    
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	private ObservableList<Object> listTable;
	
	void init(){
		editarColumnasTabla(tipo, opcion);
		lblFechaReporte.setText(new Date().toString());
		lblFechaReporte.setWrapText(true);
		lblTipoReporte.setText(tipoReporte);
	}
	
	 public void editarColumnasTabla(String tipo,String opcion) {
	    	
	    	
	    	if (tipo.equals("Simple")) {
	    	
	    	if (opcion.equals("1")) {
	    		
	    		
	    	
	    	columReporte1.setText("Código");
	    	columReporte2.setText("Ocupado");
	    	columReporte1.setVisible(true);
	    	columReporte2.setVisible(true);
	    	columReporte3.setVisible(false);
	    	columReporte4.setVisible(false);
	    	columReporte5.setVisible(false);
	    	columReporte6.setVisible(false);
	    	tipoReporte ="Listado de computadores que están ocupados";
	    	columReporte1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("ocupado"));
	    	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/2);
	    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/2);
	    	
	    	try {
				listTable = FXCollections.observableArrayList(Database.reporteSimple1());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	tablaReporte.setItems(listTable);
	    	
	    	}
	    	
	    	
	    	if (opcion.equals("2")) {
	    		columReporte1.setText("Nickname");
	        	columReporte2.setText("Cedula");
	        	columReporte3.setText("Correo");
	        	columReporte4.setText("Saldo");
	        	columReporte1.setVisible(true);
	        	columReporte2.setVisible(true);
	        	columReporte3.setVisible(true);
	        	columReporte4.setVisible(true);
	        	columReporte5.setVisible(false);
	        	columReporte6.setVisible(false);
	        	tipoReporte ="Listado de clientes con saldo recargado";
	        	columReporte1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
		    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		    	columReporte3.setCellValueFactory(new PropertyValueFactory<>("correo"));
		    	columReporte4.setCellValueFactory(new PropertyValueFactory<>("saldo"));
		    	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/4);
		    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/4);
		    	columReporte3.setPrefWidth(tablaReporte.getPrefWidth()/4);
		    	columReporte4.setPrefWidth(tablaReporte.getPrefWidth()/4);
		    	
		    	try {
					listTable = FXCollections.observableArrayList(Database.reporteSimple2());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	tablaReporte.setItems(listTable);
	    	}
	    	
	    	if (opcion.equals("3")) {
	    		columReporte1.setText("Código clase");
	        	columReporte2.setText("Fecha");
	        	columReporte1.setVisible(true);
	        	columReporte2.setVisible(true);
	        	columReporte3.setVisible(false);
	        	columReporte4.setVisible(false);
	        	columReporte5.setVisible(false);
	        	columReporte6.setVisible(false);
	        	tipoReporte ="Clases agendadas para una fecha especifica";
	        	columReporte1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		    	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/2);
		    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/2);
		    	
		    	try {
					listTable = FXCollections.observableArrayList(Database.reporteSimple3(date.toString()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	tablaReporte.setItems(listTable);
	    	}
	    	
	    	}
	    	
	    	if (tipo.equals("Intermedio")) {
	    		
	    		if (opcion.equals("1")) {
	    		
	    		columReporte1.setText("Nickname");
	        	columReporte2.setText("Codigo clase");
	        	columReporte3.setText("Fecha");
	        	columReporte1.setVisible(true);
	        	columReporte2.setVisible(true);
	        	columReporte3.setVisible(true);
	        	columReporte4.setVisible(false);
	        	columReporte5.setVisible(false);
	        	columReporte6.setVisible(false);
	        	tipoReporte ="Nickname de los clientes que recibirán clases en una fecha especifica";
	        	
	        	columReporte1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
		    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		    	columReporte3.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		    	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/3);
		    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/3);
		    	columReporte3.setPrefWidth(tablaReporte.getPrefWidth()/3);
		    	
		    	try {
					listTable = FXCollections.observableArrayList(Database.reporteIntermedio1(date.toString()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	tablaReporte.setItems(listTable);
		    	
		    	}
	        	
	  
	    		
	    		if (opcion.equals("2")) {
	    			columReporte1.setText("Nickname");
	            	columReporte2.setText("Cedula");
	            	columReporte3.setText("Correo");
	            	columReporte4.setText("Saldo");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(true);
	            	columReporte4.setVisible(true);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="Listado de clientes que compraron productos en los últimos 6 meses e hicieron recargas de más de 5000 mil pesos";
	            	columReporte1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
			    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			    	columReporte3.setCellValueFactory(new PropertyValueFactory<>("correo"));
			    	columReporte4.setCellValueFactory(new PropertyValueFactory<>("saldo"));
			    	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/4);
			    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/4);
			    	columReporte3.setPrefWidth(tablaReporte.getPrefWidth()/4);
			    	columReporte4.setPrefWidth(tablaReporte.getPrefWidth()/4);
	            	try {
						listTable = FXCollections.observableArrayList(Database.reporteIntermedio2(LocalDate.now().toString(), restarMeses(6, LocalDate.now()).toString()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	tablaReporte.setItems(listTable);
			    	
	    		}
	    		
	    		if (opcion.equals("3")) {
	    			columReporte1.setText("Categoria");
	            	columReporte2.setText("Codigo pc");
	            	columReporte3.setText("Codigo mantenimiento");
	            	columReporte4.setText("Empleado");
	            	columReporte5.setText("Observación");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(true);
	            	columReporte4.setVisible(true);
	            	columReporte5.setVisible(true);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="Obtener los reportes de mantenimiento realizados a los computadores por categoría";
	            	columReporte1.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("codigoPC"));
			    	columReporte3.setCellValueFactory(new PropertyValueFactory<>("codigoMantenimiento"));
			    	columReporte4.setCellValueFactory(new PropertyValueFactory<>("empleado"));
			    	columReporte5.setCellValueFactory(new PropertyValueFactory<>("observacion"));
			    	columReporte1.setPrefWidth(75);
			    	columReporte2.setPrefWidth(75);
			    	columReporte3.setPrefWidth(75);
			    	columReporte4.setPrefWidth(tablaReporte.getPrefWidth()/5);
			    	columReporte5.setPrefWidth(144);
	            	try {
						listTable = FXCollections.observableArrayList(Database.reporteIntermedio3());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	tablaReporte.setItems(listTable);
	    		}
	    		
	    		if (opcion.equals("4")) {
	    			columReporte1.setText("Cedula");
	            	columReporte2.setText("Empleado nombre");
	            	columReporte3.setText("Recargas");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(true);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="Listar por empleado las recargas realizadas por mayor valor en el último mes";
	            	columReporte1.setCellValueFactory(new PropertyValueFactory<>("cedula"));
			    	columReporte2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			    	columReporte3.setCellValueFactory(new PropertyValueFactory<>("maximaRecarga"));
			    	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/3);
			    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/3);
			    	columReporte3.setPrefWidth(tablaReporte.getPrefWidth()/3);
	            	try {
						listTable = FXCollections.observableArrayList(Database.reporteIntermedio4(LocalDate.now().toString(), restarMeses(1, LocalDate.now()).toString()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	tablaReporte.setItems(listTable);
	    		}
	    		
	    	}
	    	
	    	if (tipo.equals("Complejo")) {
	    		
	    		if (opcion.endsWith("1")) {
	    			columReporte1.setText("Nickname");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(false);
	            	columReporte3.setVisible(false);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="Listar los nickname de los clientes que hayan solicitado préstamos en los últimos 3 meses exceptuando aquellos que no hayan hecho recargas";
	            	columReporte1.setCellValueFactory(new PropertyValueFactory<>("nickname"));
	            	columReporte1.setPrefWidth(tablaReporte.getPrefWidth());
	            	try {
						listTable = FXCollections.observableArrayList(Database.reporteComplejo1(LocalDate.now().toString(), restarMeses(3, LocalDate.now()).toString()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	tablaReporte.setItems(listTable);
	    		}
	    		
	    		if (opcion.equals("2")) {
	    			columReporte1.setText("Producto");
	            	columReporte2.setText("Cantidad de ventas");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(false);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="Listar los productos más vendidos con un precio mayor a 100000 que no sean ofertados por el proveedor Razer";
	            	columReporte1.setCellValueFactory(new PropertyValueFactory<>("productoCodigo"));
	            	columReporte2.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
	            	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/2);
			    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/2);
	            	try {
						listTable = FXCollections.observableArrayList(Database.reporteComplejo2());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	tablaReporte.setItems(listTable);
	            	
	    			
	    		}
	    		
	    		if (opcion.equals("3")) {
	    			columReporte1.setText("Nombre");
	            	columReporte2.setText("Cantidad de recargas");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(false);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="Mostrar por empleado la cantidad de recargas realizadas. Contar solo aquellas recargas realizadas a clientes que hayan solicitado por lo menos una clase";
	            	columReporte1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	            	columReporte2.setCellValueFactory(new PropertyValueFactory<>("cantidadRecargas"));
	            	columReporte1.setPrefWidth(tablaReporte.getPrefWidth()/2);
			    	columReporte2.setPrefWidth(tablaReporte.getPrefWidth()/2);
	            	try {
						listTable = FXCollections.observableArrayList(Database.reporteComplejo3());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	tablaReporte.setItems(listTable);
	    		
	    		}
	    		
	    	}
	    }
    

	 public LocalDate restarMeses(int meses, LocalDate fecha) {
		 
		 LocalDate resultante = null;
		 
		  resultante = fecha.minusMonths(meses);
		 
		 return resultante;
	 }
		
    

}
