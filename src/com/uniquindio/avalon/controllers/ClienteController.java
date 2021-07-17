package com.uniquindio.avalon.controllers;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Timer;

import com.uniquindio.avalon.database.Database;
import com.uniquindio.avalon.logica.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ClienteController {

	@FXML
	private TextField tfClaveSelec;

	@FXML
	private TextField tfCorreo;

	@FXML
	private HBox panelSuperior;

	@FXML
	private TextField tfNicknameSelec;

	@FXML
	private AnchorPane panelDatos;

	@FXML
	private Label lbCorreoSelec;

	@FXML
	private TextField tfCedulaSelec;

	@FXML
	private AnchorPane panelTabla;

	@FXML
	private TableColumn<?, ?> columCorreoCliente;

	@FXML
	private TextField tfNickname;

	@FXML
	private TableColumn<?, ?> columNicknameCliente;

	@FXML
	private TableView<Cliente> tablaListado;

	@FXML
	private Button btnBorrar;

	@FXML
	private Button btnLimpiar;

	@FXML
	private Label labelSuperiorListado;

	@FXML
	private Label lbNicknameSelec;

	@FXML
	private TextField tfBuscar;

	@FXML
	private Button btnAgregar;

	@FXML
	private TextField tfCedula;

	@FXML
	private TableColumn<?, ?> columClaveCliente;

	@FXML
	private Label lbSelecClave;

	@FXML
	private Label lbCedulaSelec;

	@FXML
	private Button btnGuardar;

	@FXML
	private TableColumn<?, ?> columCedulaCliente;

	@FXML
	private PasswordField tfClave;

	@FXML
	private TextField tfSelecCorreo;

	private ArrayList<Cliente> listaClientes = new ArrayList<>();

	private Cliente select;

	@FXML
	private Label lblNotificacion;

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

	public void inicializarTabla() {
		columCedulaCliente.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		columNicknameCliente.setCellValueFactory(new PropertyValueFactory<>("nickname"));
		columClaveCliente.setCellValueFactory(new PropertyValueFactory<>("clave"));
		columCorreoCliente.setCellValueFactory(new PropertyValueFactory<>("correo"));

		tablaListado.setRowFactory(tv -> {
			TableRow<Cliente> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					Cliente rowData = row.getItem();
					select = rowData;
					if (rowData != null) {
						tfCedulaSelec.setText(select.getCedula());
						tfClaveSelec.setText(select.getClave());
						tfNicknameSelec.setText(select.getNickname());
						tfSelecCorreo.setText(select.getCorreo());
						lbCedulaSelec.setVisible(true);
						lbSelecClave.setVisible(true);
						lbCorreoSelec.setVisible(true);
						lbNicknameSelec.setVisible(true);
						tfCedulaSelec.setVisible(true);
						tfClaveSelec.setVisible(true);
						tfSelecCorreo.setVisible(true);
						tfNicknameSelec.setVisible(true);
						btnBorrar.setVisible(true);
						btnGuardar.setVisible(true);
					}
				}
			});

			return row;
		});

		try {
			listaClientes = Database.loadClients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Cliente> listaTabla = FXCollections.observableArrayList(listaClientes);
		tablaListado.setItems(listaTabla);
	}

	public void actualizarTabla() {
		try {
			listaClientes = Database.loadClients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Cliente> listaTabla = FXCollections.observableArrayList(listaClientes);
		tablaListado.setItems(listaTabla);
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

	public void botonActualizar() {
		btnGuardar.setOnMouseClicked(e -> {
			select.setClave(tfClaveSelec.getText());
			select.setCorreo(tfSelecCorreo.getText());
			try {
				Database.actualizarClient(select);
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
				Database.borrarCliente(select);
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

	public void botonAgregar() {
		btnAgregar.setOnMouseClicked(e -> {
			String cedula = tfCedula.getText();
			String correo = tfCorreo.getText();
			String clave = tfClave.getText();
			String nickname = tfNickname.getText();
			int saldo = 0;
			if (!verificarExistencia(cedula, nickname)) {
				Cliente cliente = new Cliente(cedula, nickname, clave, correo, saldo);
				try {
					Database.addClient(cliente);
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

	public boolean verificarExistencia(String cedula, String nickname) {
		for (Cliente c : listaClientes) {
			if (c.getCedula().equals(cedula) || c.getNickname().equals(nickname)) {
				return true;
			}
		}

		return false;
	}

	public void limpiarCampos() {
		tfBuscar.setText("");
		tfCedula.setText("");
		tfCedulaSelec.setText("");
		tfClave.setText("");
		tfClaveSelec.setText("");
		tfCorreo.setText("");
		tfNickname.setText("");
		tfNicknameSelec.setText("");
		tfSelecCorreo.setText("");
		lbCedulaSelec.setVisible(false);
		lbSelecClave.setVisible(false);
		lbCorreoSelec.setVisible(false);
		lbNicknameSelec.setVisible(false);
		tfCedulaSelec.setVisible(false);
		tfClaveSelec.setVisible(false);
		tfSelecCorreo.setVisible(false);
		tfNicknameSelec.setVisible(false);
		btnBorrar.setVisible(false);
		btnGuardar.setVisible(false);
		tfCedulaSelec.setEditable(false);
		tfNicknameSelec.setEditable(false);
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

	public ObservableList<Cliente> getListFound() {

		ObservableList<Cliente> listaTabla = FXCollections.observableArrayList(listaClientes);

		ObservableList<Cliente> founds = FXCollections.observableArrayList();

		for (Cliente c : listaTabla) {
			if (c.getNickname().toLowerCase().contains(tfBuscar.getText().toLowerCase())
					|| c.getCedula().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
				founds.add(c);
			}
		}

		return founds;
	}

}
