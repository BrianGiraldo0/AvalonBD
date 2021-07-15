package com.uniquindio.avalon.database;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.uniquindio.avalon.logica.Cliente;

public class Database {

	public static String host = "localhost";
	public static int port = 3306;
	public static String dbname = "AvalonGaming";
	public static String user = "root";
	public static String pass = "root";
	public static Connection connection = null;
	
	public static void main(String[] args) {
		try {
			openConnection(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openConnection() throws SQLException {
		try {
			InetAddress address = InetAddress.getByName(host);
			host = address.getHostAddress();
			System.out.print("Usando host: "+host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname + "?autoReconnect=true", user, pass);
//		dropAllTables();
		createTables();
	}
	
	public static void createTables() throws SQLException {
		String cliente = "CREATE TABLE IF NOT EXISTS Cliente (cedula VARCHAR(10) NOT NULL, nickname VARCHAR(80) NOT NULL UNIQUE, clave VARCHAR(100) NOT NULL, correo VARCHAR(80) NOT NULL, saldo INTEGER, PRIMARY KEY(cedula, nickname))";
		String departamento = "CREATE TABLE IF NOT EXISTS Departamento (nombre VARCHAR(50), codigo INTEGER ,PRIMARY KEY(codigo))";
		String ciudad = "CREATE TABLE IF NOT EXISTS Ciudad (nombre VARCHAR(50), codigo INTEGER, codigoDepartamento INTEGER, PRIMARY KEY(codigo), FOREIGN KEY(codigoDepartamento) REFERENCES Departamento(codigo))";
		String empleado = "CREATE TABLE IF NOT EXISTS Empleado (cedula VARCHAR(10), nombre VARCHAR(80), correo VARCHAR(80), direccion VARCHAR(80), codigoCiudad INTEGER, PRIMARY KEY(cedula), FOREIGN KEY(codigoCiudad) REFERENCES Ciudad(codigo))";
		String recarga = "CREATE TABLE IF NOT EXISTS Recarga (codigo VARCHAR(80), total INTEGER, valorCargar INTEGER, fecha DATE, cedulaCliente VARCHAR(10), cedulaEmpleado VARCHAR(10), PRIMARY KEY(codigo), FOREIGN KEY(cedulaCliente) REFERENCES Cliente(cedula), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula))";
		String compra = "CREATE TABLE IF NOT EXISTS Compra (codigo VARCHAR(80), fecha DATE, direccion VARCHAR(80), cedulaCliente VARCHAR(10), telefono VARCHAR(15), codigoCiudad INTEGER, PRIMARY KEY(codigo), FOREIGN KEY(cedulaCliente) REFERENCES Cliente(cedula), FOREIGN KEY(codigoCiudad) REFERENCES Ciudad(codigo))";
		String producto = "CREATE TABLE IF NOT EXISTS Producto (codigo VARCHAR(80), descripcion VARCHAR(300), nombre VARCHAR(80), precio INTEGER, fechaInicioGarantia DATE, fechaFinGarantia DATE, PRIMARY KEY(codigo))";
		String compraProducto = "CREATE TABLE IF NOT EXISTS CompraProducto (cantidad INTEGER, codigoProducto VARCHAR(80),codigoCompra VARCHAR(80), PRIMARY KEY(codigoProducto, codigoCompra), FOREIGN KEY(codigoProducto) REFERENCES Producto(codigo), FOREIGN KEY(codigoCompra) REFERENCES Compra(Codigo))";
		String computador = "CREATE TABLE IF NOT EXISTS Computador (codigo VARCHAR(80), categoria VARCHAR(50), ocupado boolean DEFAULT false, PRIMARY KEY(codigo))";
		String componente = "CREATE TABLE IF NOT EXISTS Componente (nombre VARCHAR(80), codigoComputador VARCHAR(80), codigo INTEGER, descripcion VARCHAR(300) , PRIMARY KEY(codigo), FOREIGN KEY(codigoComputador) REFERENCES Computador(codigo))";
		String prestamo = "CREATE TABLE IF NOT EXISTS Prestamo (codigo VARCHAR(80), fecha DATE, duracion INTEGER, cedulaCliente VARCHAR(10), codigoComputador VARCHAR(80), PRIMARY KEY(codigo), FOREIGN KEY(cedulaCliente) REFERENCES Cliente(cedula), FOREIGN KEY(codigoComputador) REFERENCES Computador(codigo))";
		String clase = "CREATE TABLE IF NOT EXISTS Clase (codigo VARCHAR(80), fecha DATE, duracion INTEGER, descripcion VARCHAR(300), observacion VARCHAR(300), cedulaCliente VARCHAR(10), cedulaEmpleado VARCHAR(10), PRIMARY KEY(codigo), FOREIGN KEY(cedulaCliente) REFERENCES Cliente(cedula), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula))";
		String reporteMantenimiento = "CREATE TABLE IF NOT EXISTS ReporteMantenimiento (codigo VARCHAR(80), fecha DATE, observacion VARCHAR(300), cedulaEmpleado VARCHAR(10), codigoComputador VARCHAR(80), PRIMARY KEY(codigo), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula), FOREIGN KEY(codigoComputador) REFERENCES Computador(codigo))";
		String telefonoEmpleado = "CREATE TABLE IF NOT EXISTS TelefonoEmpleado (telefono VARCHAR(10), cedulaEmpleado VARCHAR(10), PRIMARY KEY(telefono), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula))";
		String cargo = "CREATE TABLE IF NOT EXISTS Cargo (cargo VARCHAR(50), codigo INTEGER, descripcion VARCHAR(300), PRIMARY KEY(codigo))";
		String cargoEmpleado = "CREATE TABLE IF NOT EXISTS CargoEmpleado (descripcion VARCHAR(300), cedulaEmpleado VARCHAR(10), codigoCargo INTEGER, PRIMARY KEY(cedulaEmpleado, codigoCargo), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula), FOREIGN KEY(codigoCargo) REFERENCES Cargo(codigo))";
		String proveedor = "CREATE TABLE IF NOT EXISTS Proveedor (nit VARCHAR(15), nombre VARCHAR(80), PRIMARY KEY(nit))";
		String pedido = "CREATE TABLE IF NOT EXISTS Pedido (codigo VARCHAR(80), valor INTEGER, fechaRealizacion DATE, fechaEntrega DATE, cedulaEmpleado VARCHAR(10), nitProveedor VARCHAR(15), PRIMARY KEY(codigo), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula), FOREIGN KEY(nitProveedor) REFERENCES Proveedor(nit))";
		String productoPedido = "CREATE TABLE IF NOT EXISTS ProductoPedido (cantidad INTEGER, codigoPedido VARCHAR(80), codigoProducto VARCHAR(80), PRIMARY KEY(codigoPedido, codigoProducto), FOREIGN KEY(codigoProducto) REFERENCES Producto(codigo), FOREIGN KEY(codigoPedido) REFERENCES Pedido(codigo))";
		String productoProveedor = "CREATE TABLE IF NOT EXISTS ProductoProveedor (nitProveedor VARCHAR(15), codigoProducto VARCHAR(80), PRIMARY KEY(nitProveedor, codigoProducto), FOREIGN KEY(nitProveedor) REFERENCES Proveedor(nit), FOREIGN KEY(codigoProducto) REFERENCES Producto(codigo))";
		
		Statement update = connection.createStatement();
		update.execute(cliente);
		update.execute(departamento);
		update.execute(ciudad);
		update.execute(empleado);
		update.execute(recarga);
		update.execute(compra);
		update.execute(producto);
		update.execute(compraProducto);
		update.execute(computador);
		update.execute(componente);
		update.execute(prestamo);
		update.execute(clase);
		update.execute(reporteMantenimiento);
		update.execute(telefonoEmpleado);
		update.execute(cargo);
		update.execute(cargoEmpleado);
		update.execute(proveedor);
		update.execute(pedido);
		update.execute(productoPedido);
		update.execute(productoProveedor);
		
	}
	
	public static void dropAllTables() throws SQLException {
		Statement update = connection.createStatement();
		update.execute("DROP TABLE IF EXISTS ProductoProveedor");
		update.execute("DROP TABLE IF EXISTS ProductoPedido");
		update.execute("DROP TABLE IF EXISTS Pedido");
		update.execute("DROP TABLE IF EXISTS Proveedor");
		update.execute("DROP TABLE IF EXISTS CargoEmpleado");
		update.execute("DROP TABLE IF EXISTS Cargo");
		update.execute("DROP TABLE IF EXISTS TelefonoEmpleado");
		update.execute("DROP TABLE IF EXISTS ReporteMantenimiento");
		update.execute("DROP TABLE IF EXISTS Clase");
		update.execute("DROP TABLE IF EXISTS Prestamo");
		update.execute("DROP TABLE IF EXISTS Componente");
		update.execute("DROP TABLE IF EXISTS Computador");
		update.execute("DROP TABLE IF EXISTS CompraProducto");
		update.execute("DROP TABLE IF EXISTS Producto");
		update.execute("DROP TABLE IF EXISTS Compra");
		update.execute("DROP TABLE IF EXISTS Recarga");
		update.execute("DROP TABLE IF EXISTS Empleado");
		update.execute("DROP TABLE IF EXISTS Ciudad");
		update.execute("DROP TABLE IF EXISTS Departamento");
		update.execute("DROP TABLE IF EXISTS Cliente");
	}
	
	
	
	public static void addClient(Cliente client) throws SQLException {
		openConnection();
		Statement update = connection.createStatement(); 
		String query = "INSERT INTO Cliente VALUES('" + client.getCedula()+"', '" + client.getNickname()+"', '" + client.getClave()+"', '" + client.getCorreo()+"', " + client.getSaldo()+")";
		update.execute(query);
		
	}
	
	
	public static Cliente loadClient(String nickname) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Cliente WHERE Cliente.nickname = '" + nickname + "'";
		Cliente cliente = null;
		ResultSet rs = update.executeQuery(query);
		if(rs.next()) {
			String cedula = rs.getString("cedula");
			String correo = rs.getString("correo");
			String clave = rs.getString("clave");
			int saldo = rs.getInt("saldo");
			cliente = new Cliente(cedula, nickname, clave, correo, saldo);
		}

		return cliente;
	}
	
	
	public static ArrayList<Cliente> loadClients() throws SQLException {
		openConnection();
		ArrayList<Cliente> clientes = new ArrayList<>();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Cliente";
		ResultSet rs = update.executeQuery(query);
		 while(rs.next()) {
			 	String cedula = rs.getString("cedula");
			 	String nickname = rs.getString("nickname");
				String correo = rs.getString("correo");
				String clave = rs.getString("clave");
				int saldo = rs.getInt("saldo");
				Cliente cliente = new Cliente(cedula, nickname, clave, correo, saldo);
				clientes.add(cliente);
		 }
		 
		return clientes;
		
	}
	
	public static void actualizarClient(Cliente cliente) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "UPDATE CLIENTE SET correo = '" + cliente.getCorreo() + "', clave = '" + cliente.getClave() + "' WHERE cedula = '" + cliente.getCedula()+"'";
		update.execute(query);
	}
	
	public static void borrarCliente(Cliente cliente) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "DELETE FROM CLIENTE WHERE cedula = '" + cliente.getCedula()+"'";
		update.execute(query);
		
	}
	
	
	
}
