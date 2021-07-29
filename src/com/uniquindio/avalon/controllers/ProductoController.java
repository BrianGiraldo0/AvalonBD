package com.uniquindio.avalon.controllers;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.uniquindio.avalon.database.Database;
import com.uniquindio.avalon.logica.Cliente;
import com.uniquindio.avalon.logica.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.DatePicker;

/**
 * Controlador de Producto
 * 
 *
 */
public class ProductoController {

    @FXML
    private Label lbCodigoSelec;

    @FXML
    private TextField tfCodigoSelec;

    @FXML
    private Label lbPrecioSelec;

    @FXML
    private TextField tfPrecioSelec;

    @FXML
    private TextField tfNombreSelec;

    @FXML
    private Label lbNombreSelec;

    @FXML
    private HBox panelSuperior;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Label labelSuperiorListado;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Label lbDescripcionSelec;

    @FXML
    private TextField tfDescripcionSelec;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private TableView<Producto> tablaListado;

    @FXML
    private TableColumn<?, ?> columnCodigoProducto;
 

    @FXML
    private TableColumn<?, ?> columNombreProducto;

    @FXML
    private TableColumn<?, ?> columDescripcionProducto;

    @FXML
    private TableColumn<?, ?> columPrecioProducto;

    @FXML
    private TableColumn<?, ?> columInicioGarantiaProducto;

    @FXML
    private TableColumn<?, ?> columFinGarantiaProducto;
    
    @FXML
    private DatePicker tfFechaInicioGarantia;

    @FXML
    private DatePicker tfFechaFinGarantia;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private TextField tfPrecio;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnLimpiar;
    
    private Producto select;
    private ArrayList<Producto> listaProductos = new ArrayList<Producto>();
    
    @FXML
    private Label lblNotificacion;
    
    @FXML
    private Label lbFechaInicioSelec;

    @FXML
    private Label lbFechaFinSelec;

    @FXML
    private DatePicker tfFechaInicioGarantiaSelec;

    @FXML
    private DatePicker tfFechaFinGarantiaSelec;

    @FXML
 	void initialize() {
 		
 		
    	inicializarTabla();
		limpiarCampos();
		
		// inicializarVentana();
		colocarIconos();
		botonAgregar();
		botonActualizar();
		botonEliminar();
		botonLimpiar();
		buscador();
 	}
     
 	public void colocarIconos() {
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
 	
 	public void inicializarTabla() {
		columnCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columDescripcionProducto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		columNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columInicioGarantiaProducto.setCellValueFactory(new PropertyValueFactory<>("fechaInicioGarantia"));
		columFinGarantiaProducto.setCellValueFactory(new PropertyValueFactory<>("fechaFinGarantia"));

		tablaListado.setRowFactory(tv->{
			TableRow<Producto> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					Producto rowData = row.getItem();
					select = rowData;
					if (rowData != null) {
						tfCodigoSelec.setText(select.getCodigo());
						tfDescripcionSelec.setText(select.getDescripcion());
						tfNombreSelec.setText(select.getNombre());
						tfPrecioSelec.setText(select.getPrecio() + "");
						tfFechaFinGarantiaSelec.setValue(dateToLocaldate(select.getFechaFinGarantia()));
						tfFechaInicioGarantiaSelec.setValue(dateToLocaldate(select.getFechaInicioGarantia()));
						lbCodigoSelec.setVisible(true);
						lbDescripcionSelec.setVisible(true);
						lbNombreSelec.setVisible(true);
						lbPrecioSelec.setVisible(true);
						lbFechaInicioSelec.setVisible(true);
						lbFechaFinSelec.setVisible(true);
						tfFechaInicioGarantiaSelec.setVisible(true);
						tfFechaFinGarantiaSelec.setVisible(true);
						tfCodigoSelec.setVisible(true);
						tfDescripcionSelec.setVisible(true);
						tfNombreSelec.setVisible(true);
						tfPrecioSelec.setVisible(true);
						btnBorrar.setVisible(true);
						btnGuardar.setVisible(true);
					}
				}
			});
			
			return row;
		});
		

		try {
			listaProductos = Database.loadProductos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Producto> listaTabla = FXCollections.observableArrayList(listaProductos);
		tablaListado.setItems(listaTabla);
	}

 	public LocalDate dateToLocaldate(Date date) {
 		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
 		LocalDate lDate = LocalDate.of(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH));
 		
 		return lDate;
 	}
	public void actualizarTabla() {
		try {
			listaProductos = Database.loadProductos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Producto> listaTabla = FXCollections.observableArrayList(listaProductos);
		tablaListado.setItems(listaTabla);
	}
    
	public void botonActualizar() {
		btnGuardar.setOnMouseClicked(e -> {
			select.setDescripcion(tfDescripcionSelec.getText());
			select.setNombre(tfNombreSelec.getText());
			select.setPrecio(Integer.parseInt(tfPrecioSelec.getText()));
			try {
				Database.actualizarProducto(select);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			actualizarTabla();
		});

	}

	public void botonEliminar() {
		btnBorrar.setOnMouseClicked(e -> {
			try {
				Database.borrarProducto(select);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Error: Este producto está asociado con más datos, por lo tanto no puede ser eliminado!");
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

	public void botonAgregar() {
		btnAgregar.setOnMouseClicked(e -> {
			String codigo = tfCodigo.getText();
			String descripcion = tfDescripcion.getText();
			String nombre = tfNombre.getText();
			int precio = Integer.parseInt(tfPrecio.getText());
			Date fechaInicioGarantia = Date.from(tfFechaInicioGarantia.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date fechaFinGarantia = Date.from(tfFechaFinGarantia.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			if (!verificarExistencia(codigo, nombre)) {
				Producto producto = new Producto(codigo, descripcion, nombre, precio, fechaInicioGarantia, fechaFinGarantia);
				try {
					Database.addProducto(producto);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				actualizarTabla();
				limpiarCampos();
			} else {
				lblNotificacion.setVisible(true);
			}

		});
	}

	public boolean verificarExistencia(String codigo, String nombre) {
		for (Producto p : listaProductos) {
			if (p.getCodigo().equals(codigo) || p.getNombre().equals(nombre)) {
				return true;
			}
		}

		return false;
	}

	public void limpiarCampos() {
		tfBuscar.setText("");
		tfCodigo.setText("");
		tfCodigoSelec.setText("");
		tfDescripcion.setText("");
		tfDescripcionSelec.setText("");
		tfNombre.setText("");
		tfNombreSelec.setText("");
		tfPrecio.setText("");
		tfPrecioSelec.setText("");
		tfFechaFinGarantia.setValue(LocalDate.now());
		tfFechaFinGarantiaSelec.setValue(LocalDate.now());
		tfFechaInicioGarantia.setValue(LocalDate.now());
		tfFechaInicioGarantiaSelec.setValue(LocalDate.now());
		lbCodigoSelec.setVisible(false);
		lbNombreSelec.setVisible(false);
		lbDescripcionSelec.setVisible(false);
		lbPrecioSelec.setVisible(false);
		lbFechaFinSelec.setVisible(false);
		lbFechaInicioSelec.setVisible(false);
		tfCodigoSelec.setVisible(false);
		tfNombreSelec.setVisible(false);
		tfDescripcionSelec.setVisible(false);
		tfPrecioSelec.setVisible(false);
		tfFechaFinGarantiaSelec.setVisible(false);
		tfFechaInicioGarantiaSelec.setVisible(false);
		btnBorrar.setVisible(false);
		btnGuardar.setVisible(false);
		tfCodigoSelec.setEditable(false);
		lblNotificacion.setVisible(false);
		select = null;

	}

	public void buscador() {
		tfBuscar.setOnKeyPressed(e -> {
			if (tfBuscar.isFocused()) {
				if (tfBuscar.getText() != null) {

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

	public ObservableList<Producto> getListFound() {

		ObservableList<Producto> listaTabla = FXCollections.observableArrayList(listaProductos);

		ObservableList<Producto> founds = FXCollections.observableArrayList();

		for (Producto p : listaTabla) {
			if (p.getNombre().toLowerCase().contains(tfBuscar.getText().toLowerCase())
					|| p.getCodigo().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
				founds.add(p);
			}
		}

		return founds;
	}

}
