package com.uniquindio.avalon.controllers;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Timer;

import com.uniquindio.avalon.database.Database;
import com.uniquindio.avalon.logica.Cliente;
import com.uniquindio.avalon.logica.Empleado;
import com.uniquindio.avalon.logica.Recarga;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
/**
 * Controlador de Recarga
 * 
 *
 */
public class RecargaController {

	
	@FXML
    private DatePicker  tfFecha;

    @FXML
    private TextField tfTotal;

    @FXML
    private HBox panelSuperior;

    @FXML
    private TextField tfCodigo;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfValorRecargaSelec;

    @FXML
    private TableColumn<?, ?> columnCodigoRecarga;

    @FXML
    private TextField tfTotalSelec;

    @FXML
    private TableColumn<?, ?> columnTotalRecarga;

    @FXML
    private TextField tfCedulaClienteSelec;

    @FXML
    private Label lbCodigoSelec;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private DatePicker tfFechaSelec;

    @FXML
    private Label lbSelecCedulaCliente;

    @FXML
    private TableView<Recarga> tablaListado;

    @FXML
    private TableColumn<?, ?> columFechaRecarga;

    @FXML
    private Button btnBorrar;

    @FXML
    private Label lbValorRecargaSelec;

    @FXML
    private Label lbCedulaEmpleadoSelec;

    @FXML
    private TableColumn<?, ?> columCedulaEmpleado;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TextField tfCedulaCliente;

    @FXML
    private Label lbFechaSelec;

    @FXML
    private Label lbSelecTotal;

    @FXML
    private TextField tfSelecCedulaEmpleado;

    @FXML
    private Label labelSuperiorListado;

    @FXML
    private TextField tfCedulaEmpleado;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Button btnAgregar;

    @FXML
    private TextField tfCodigoSelec;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<?, ?> columCedulaCliente;

    @FXML
    private TableColumn<?, ?> columnValorRecarga;

    @FXML
    private TextField tfValorRecarga;
    
    @FXML
    private Label lblNotificacion;
    
    private Recarga select;
    private ArrayList<Recarga> listaRecargas = new ArrayList<>();

    
    
 
    
    
    
    @FXML
 	void initialize() {
    	
    	inicializarTabla();
		limpiarCampos();
		botonAgregar();
		botonActualizar();
		botonEliminar();
		botonLimpiar();
		buscador();
		restrictDatePicker(tfFecha, LocalDate.now(), LocalDate.MAX);
    	
		
		/*
 		lbSelecCedulaCliente.setVisible(false);
 		lbCedulaEmpleadoSelec.setVisible(false);
 		lbFechaSelec.setVisible(false);
 		lbCodigoSelec.setVisible(false);
 		lbValorRecargaSelec.setVisible(false);
 		lbSelecTotal.setVisible(false);
 		tfTotalSelec.setVisible(false);
 		tfValorRecargaSelec.setVisible(false);
 		tfFechaSelec.setVisible(false);
 		tfCedulaClienteSelec.setVisible(false);
 		tfSelecCedulaEmpleado.setVisible(false);
 		tfCodigoSelec.setVisible(false);
 		
 		btnBorrar.setVisible(false);
 		btnGuardar.setVisible(false);
 		//inicializarVentana();
 		  */
 		 
 		colorIconos();
 		
 	}
    
    
    
   public void inicializarTabla() {
	   
    	
    	columnCodigoRecarga.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	columCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("clienteCedula"));
    	columnValorRecarga.setCellValueFactory(new PropertyValueFactory<>("valorCargar"));
    	columFechaRecarga.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    	columnTotalRecarga.setCellValueFactory(new PropertyValueFactory<>("total"));
    	columCedulaEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleadoCedula"));
    	tfCodigoSelec.setEditable(false);
    	tfCodigoSelec.setDisable(true);
    	
    	tablaListado.setRowFactory(tv -> {
    		
    		
    		TableRow<Recarga> row = new TableRow<>();
    		
    		row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					Recarga rowData = row.getItem();
					select = rowData;
					if (rowData != null) {
						
						
						tfCodigoSelec.setText(select.getCodigo());
						tfFechaSelec.setValue(dateToLocaldate(select.getFecha()));
						tfCedulaClienteSelec.setText(select.getClienteCedula());
						tfSelecCedulaEmpleado.setText(select.getEmpleadoCedula());
						tfValorRecargaSelec.setText(select.getValorCargar() +"");
						tfTotalSelec.setText(select.getTotal() +"");
						lbSelecCedulaCliente.setVisible(true);
				 		lbCedulaEmpleadoSelec.setVisible(true);
				 		lbFechaSelec.setVisible(true);
				 		lbCodigoSelec.setVisible(true);
				 		lbValorRecargaSelec.setVisible(true);
				 		lbSelecTotal.setVisible(true);
				 		tfTotalSelec.setVisible(true);
				 		tfValorRecargaSelec.setVisible(true);
				 		tfFechaSelec.setVisible(true);
				 		tfCedulaClienteSelec.setVisible(true);
				 		tfSelecCedulaEmpleado.setVisible(true);
				 		tfCodigoSelec.setVisible(true);
				 	
				 		
				 		btnBorrar.setVisible(true);
				 		btnGuardar.setVisible(true);
					}
				}
			});

			return row;
		});
    	
    	try {
			listaRecargas = Database.loadRecarga();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Recarga> listaTabla = FXCollections.observableArrayList(listaRecargas);
		tablaListado.setItems(listaTabla);
    	
    	
    	
    }
    
    
   public void botonActualizar() {
		btnGuardar.setOnMouseClicked(e -> {
			ZoneId defaultZoneId = ZoneId.systemDefault();
			select.setEmpleadoCedula(tfSelecCedulaEmpleado.getText());
			select.setClienteCedula(tfCedulaClienteSelec.getText());
			select.setValorCargar(Integer.parseInt(tfValorRecargaSelec.getText()));
			select.setTotal(Integer.parseInt(tfTotalSelec.getText()));
			select.setFecha(Date.from(tfFechaSelec.getValue().atStartOfDay(defaultZoneId).toInstant()));
			select.setCodigo(tfCodigoSelec.getText());
			
			try {
				if (verificarCedulas(tfSelecCedulaEmpleado.getText(), tfCedulaClienteSelec.getText())) {
					

					try {
						Database.actualizarRecarga(select);
						lblNotificacion.setVisible(false);
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			actualizarTabla();
			
		});

	}
   
	public void botonAgregar() {
		btnAgregar.setOnMouseClicked(e -> {
			String codigo = tfCodigo.getText();
			String cedulaEmpleado = tfCedulaEmpleado.getText();
			String cedulaCliente = tfCedulaCliente.getText();
			Date fecha = Date.from(tfFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			int valorCarga = Integer.parseInt(tfValorRecarga.getText());
			int total = Integer.parseInt(tfTotal.getText());

			try {
				if (verificarCedulas(cedulaEmpleado, cedulaCliente)) {
					if (!verificarExistencia(codigo)) {

						Recarga recarga = new Recarga(codigo, total, valorCarga, fecha, cedulaEmpleado, cedulaCliente);
						try {
							Database.addRecarga(recarga);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						actualizarTabla();
						limpiarCampos();
					} else {
						lblNotificacion.setText("Ya existe una recarga con ese codigo!");
						lblNotificacion.setVisible(true);
					}
				}
			}

			catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});
	}
	
	
	public boolean verificarCedulas(String cedulaEmpleado, String cedulaCliente) throws SQLException {
		
		boolean verificacion = true;
		Cliente c = Database.loadClientbyId(cedulaCliente);
		Empleado em = Database.loadEmpleado(cedulaEmpleado);
		
		if (c ==null ) {
			
			lblNotificacion.setVisible(true);
			lblNotificacion.setText("El cliente no existe, ingrese cedula valida");
			verificacion = false;
			
			
		}
		
		if (em ==null) {

			lblNotificacion.setVisible(true);
			lblNotificacion.setText("El empleado no existe, ingrese cedula valida");
			verificacion = false;
		}
		
		if (em == null && c ==null) {
			lblNotificacion.setVisible(true);
			lblNotificacion.setText("El empleado y el cliente no existen, ingrese cedula valida");
			verificacion  = false;
		}
		
		return verificacion;
		
	}
	
	public void botonEliminar() {
		btnBorrar.setOnMouseClicked(e -> {
			try {
				Database.borrarRecarga(select);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			limpiarCampos();
			select = null;
			actualizarTabla();
		});
	}

	public void botonLimpiar() {
		btnLimpiar.setOnMouseClicked(e -> {
			limpiarCampos();
		});
	}
    
	public void actualizarTabla() {
		try {
			listaRecargas = Database.loadRecarga();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Recarga> listaTabla = FXCollections.observableArrayList(listaRecargas);
		tablaListado.setItems(listaTabla);
	}
    
	
	public boolean verificarExistencia(String codigo) {
		for (Recarga r : listaRecargas) {
			if (r.getCodigo().equals(codigo)) {
				return true;
			}
		}

		return false;
	}
    
	public void limpiarCampos() {
		
		tfBuscar.setText("");
		
		
		tfCodigo.setText("");
		tfFecha.setValue(LocalDate.now());
		tfCedulaCliente.setText("");
		tfCedulaEmpleado.setText("");
		tfValorRecarga.setText("");
		tfTotal.setText("");
		
		lbSelecCedulaCliente.setVisible(false);
 		lbCedulaEmpleadoSelec.setVisible(false);
 		lbFechaSelec.setVisible(false);
 		lbCodigoSelec.setVisible(false);
 		lbValorRecargaSelec.setVisible(false);
 		lbSelecTotal.setVisible(false);
 		tfTotalSelec.setVisible(false);
 		tfValorRecargaSelec.setVisible(false);
 		tfFechaSelec.setVisible(false);
 		tfCedulaClienteSelec.setVisible(false);
 		tfSelecCedulaEmpleado.setVisible(false);
 		tfCodigoSelec.setVisible(false);
 		
 		btnBorrar.setVisible(false);
 		btnGuardar.setVisible(false);
 		lblNotificacion.setVisible(false);
		
		select = null;
	}
	
	  public void buscador() {
			tfBuscar.setOnKeyPressed(e -> {
				if (tfBuscar.isFocused()) {
					if (tfBuscar.getText() != null && tfBuscar.getText().equalsIgnoreCase("")) {

						Timer timer = new Timer(1, new ActionListener() {

							@Override
							public void actionPerformed(java.awt.event.ActionEvent e) {
								tablaListado.setItems(getListFound());

							}
						});
						timer.start();
						timer.setRepeats(false);

					}

				}
			});
		}
	  
	  
		public ObservableList<Recarga> getListFound() {

			ObservableList<Recarga> listaTabla = FXCollections.observableArrayList(listaRecargas);

			ObservableList<Recarga> founds = FXCollections.observableArrayList();

			for (Recarga r : listaTabla) {
				if (r.getCodigo().toLowerCase().contains(tfBuscar.getText().toLowerCase())
						|| r.getClienteCedula().toLowerCase().contains(tfBuscar.getText().toLowerCase()) || r.getEmpleadoCedula().toLowerCase().contains(tfBuscar.getText().toLowerCase()) ) {
					founds.add(r);
				}
			}

			return founds;
		}
     
 	public void colorIconos() {
 		URL iconBucar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconAgregar.png");
 		URL iconLimpiar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconLimpiar.png");
 		URL iconGuardar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconGuardar.png");
 		URL iconBorrar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconBorrar.png");
 		Image imagenBuscar = new Image(iconBucar.toString(), 24, 24, false, true);
 		Image imagenBorrar = new Image(iconBorrar.toString(), 24, 24, false, true);
 		Image imagenGuardar = new Image(iconGuardar.toString(), 24, 24, false, true);
 		Image imagenLimpiar = new Image(iconLimpiar.toString(), 24, 24, false, true);

 		btnBorrar.setGraphic(new ImageView(imagenBorrar));
 		btnGuardar.setGraphic(new ImageView(imagenGuardar));
 		btnAgregar.setGraphic(new ImageView(imagenBuscar));
 		btnLimpiar.setGraphic(new ImageView(imagenLimpiar));
 	}
    
    
    
    
    
    @FXML
    void keyListener(ActionEvent event) {

    }

    @FXML
    void actionListener(ActionEvent event) {

    }
	
	
    public LocalDate dateToLocaldate(Date date) {
 		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
 		LocalDate lDate = LocalDate.of(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH));
 		
 		return lDate;
 	}
	
	
    public void restrictDatePicker(DatePicker datePicker, LocalDate minDate, LocalDate maxDate) {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                         if (item.isBefore(minDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }else if (item.isAfter(maxDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }
	
	
	
	
	
}
