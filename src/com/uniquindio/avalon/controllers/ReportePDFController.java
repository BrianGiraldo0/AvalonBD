package com.uniquindio.avalon.controllers;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportePDFController {

    @FXML
    private TableView<?> tablaReporte;

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
    
    private String tipoReporte;
    
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	
	void init(){
		editarColumnasTabla(tipo, opcion);
		lblFechaReporte.setText(new Date().toString());
		lblFechaReporte.setWrapText(true);
		lblTipoReporte.setText(tipoReporte);
	}
	
	 public void editarColumnasTabla(String tipo,String opcion) {
	    	
	    	System.out.println(tipo + " " + opcion);
	    	
	    	if (tipo.equals("Simple")) {
	    	
	    	if (opcion.equals("1")) {
	    		
	    		
	    	
	    	columReporte1.setText("Código");
	    	columReporte2.setText("Estado");
	    	
	    	columReporte1.setVisible(true);
	    	columReporte2.setVisible(true);
	    	
	    	
	    	columReporte3.setVisible(false);
	    	columReporte4.setVisible(false);
	    	columReporte5.setVisible(false);
	    	columReporte6.setVisible(false);
	    	tipoReporte ="Listado de computadores que están ocupados";
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
	            	tipoReporte ="";
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
	            	tipoReporte ="";
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
	            	tipoReporte ="";
	    		}
	    		
	    	}
	    	
	    	if (tipo.equals("Complejo")) {
	    		
	    		if (opcion.endsWith("1")) {
	    			columReporte1.setText("Nickname");
	            	columReporte2.setText("Prestamo");
	            	columReporte3.setText("Fecha");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(true);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="";
	    			
	    		}
	    		
	    		if (opcion.equals("2")) {
	    			
	    			columReporte1.setText("Producto");
	            	columReporte2.setText("Precio");
	            	columReporte3.setText("Proveedor");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(true);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="";
	    			
	    		}
	    		
	    		if (opcion.equals("3")) {
	    			columReporte1.setText("Cedula");
	            	columReporte2.setText("Nickname");
	            	columReporte3.setText("Saldo");
	            	columReporte1.setVisible(true);
	            	columReporte2.setVisible(true);
	            	columReporte3.setVisible(true);
	            	columReporte4.setVisible(false);
	            	columReporte5.setVisible(false);
	            	columReporte6.setVisible(false);
	            	tipoReporte ="";
	    		}
	    		
	    		
	    	}
	    	
	    
	 
	    	
	    	
	    }
    
    

}
