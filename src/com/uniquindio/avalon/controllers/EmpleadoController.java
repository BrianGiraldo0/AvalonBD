package com.uniquindio.avalon.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class EmpleadoController {

    @FXML
    private Label lbCedulaSelec;

    @FXML
    private TextField tfCedulaSelec;

    @FXML
    private Label lbDireccionSelec;

    @FXML
    private TextField tfDireccionSelec;

    @FXML
    private TextField tfNombreSelec;

    @FXML
    private Label lbNombreSelec;

    @FXML
    private Label lbCiudadSelec;

    @FXML
    private ComboBox<String> cbCiudadSelec;

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
    private Label lbCorreoSelec;

    @FXML
    private TextField tfCorreoSelec;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private TableView<?> tablaListado;

    @FXML
    private TableColumn<?, ?> columCedula;

    @FXML
    private TableColumn<?, ?> columNombre;

    @FXML
    private TableColumn<?, ?> columCorreo;

    @FXML
    private TableColumn<?, ?> columDireccion;

    @FXML
    private TableColumn<?, ?> columCiudad;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfCedula;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfDireccion;

    @FXML
    private ComboBox<?> cbCiudad;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnLimpiar;

    @FXML
    void actionListener(ActionEvent event) {

    }

    @FXML
    void keyListener(KeyEvent event) {

    }

}
