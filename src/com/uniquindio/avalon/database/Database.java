package com.uniquindio.avalon.database;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;
import com.uniquindio.avalon.logica.Clase;
import com.uniquindio.avalon.logica.Cliente;
import com.uniquindio.avalon.logica.Computador;
import com.uniquindio.avalon.logica.Empleado;
import com.uniquindio.avalon.logica.ReporteIntermedio1;
import com.uniquindio.avalon.logica.ReporteIntermedio3;
import com.uniquindio.avalon.logica.ReporteMantenimiento;
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
		crearCiudades();
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
	
	
	public static void crearCiudades() throws SQLException {
		Statement update = connection.createStatement();
		update.execute("INSERT INTO Departamento VALUES('Quindio', 1)");
		update.execute("INSERT INTO Departamento VALUES('Cundinamarca', 2)");
		update.execute("INSERT INTO Departamento VALUES('Santander', 3)");
		update.execute("INSERT INTO Departamento VALUES('Valle del cauca', 4)");
		update.execute("INSERT INTO Departamento VALUES('Tolima', 5)");
		update.execute("INSERT INTO Departamento VALUES('Magdalena', 6)");
		update.execute("INSERT INTO Departamento VALUES('Cesar', 7)");
		update.execute("INSERT INTO Departamento VALUES('La Guajira', 8)");
		update.execute("INSERT INTO Departamento VALUES('Antioquia', 9)");
		
		update.execute("INSERT INTO Ciudad VALUES('Armenia', 1, 1)");
		update.execute("INSERT INTO Ciudad VALUES('Calarca', 2, 1)");
		update.execute("INSERT INTO Ciudad VALUES('Circasia', 3, 1)");
		update.execute("INSERT INTO Ciudad VALUES('Bogota', 4, 2)");
		update.execute("INSERT INTO Ciudad VALUES('Ibague', 5, 5)");
		update.execute("INSERT INTO Ciudad VALUES('Bucaramanga', 6, 3)");
		update.execute("INSERT INTO Ciudad VALUES('Cali', 7, 4)");
		update.execute("INSERT INTO Ciudad VALUES('Santa marta', 8, 6)");
		update.execute("INSERT INTO Ciudad VALUES('Valledupar', 9, 7)");
		update.execute("INSERT INTO Ciudad VALUES('Medellin', 10, 9)");
	} 
	
	/*
	 * Inicio DB Cliente
	 */
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
	
	public static Computador loadComputer (String codigo) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Computador c WHERE c.codigo = '" + codigo +"'";
		Computador computador = null;
		ResultSet rs = update.executeQuery(query);
		if(rs.next()) {
			String categoria = rs.getString("categoria");
			int intOcupado = rs.getInt("ocupado");
			boolean ocupado=false;
			if(intOcupado==1)
				ocupado = true;
			
			computador = new Computador(codigo, categoria, ocupado);
		}
		
		return computador;
		
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
	
	/*
	 * Fin DB Cliente
	 */
	
	
	/*
	 * Inicio DB Empleado
	 */
	public static ArrayList<Empleado> loadEmpleados() throws SQLException {
		openConnection();
		ArrayList<Empleado> empleados = new ArrayList<>();
		Statement update = connection.createStatement();
		String query = "SELECT e.*, c.nombre as ciudadNombre FROM Empleado e JOIN Ciudad c";
		ResultSet rs = update.executeQuery(query);
		 while(rs.next()) {
			 	String cedula = rs.getString("cedula");
				String correo = rs.getString("correo");
				String nombre = rs.getString("nombre");
				String ciudad = rs.getString("ciudadNombre");
				String direccion = rs.getString("direccion");
				
				Empleado empleado = new Empleado(cedula, nombre, correo, direccion, ciudad);
				empleados.add(empleado);
				
		 }
		 
		return empleados;
		
	}
//	
//	public static void addClient(Emplead client) throws SQLException {
//		openConnection();
//		Statement update = connection.createStatement(); 
//		String query = "INSERT INTO Cliente VALUES('" + client.getCedula()+"', '" + client.getNickname()+"', '" + client.getClave()+"', '" + client.getCorreo()+"', " + client.getSaldo()+")";
//		update.execute(query);
//		
//	}
//	
//	public static void actualizarClient(Emplead cliente) throws SQLException {
//		openConnection();
//		Statement update = connection.createStatement();
//		String query = "UPDATE CLIENTE SET correo = '" + cliente.getCorreo() + "', clave = '" + cliente.getClave() + "' WHERE cedula = '" + cliente.getCedula()+"'";
//		update.execute(query);
//	}
//	
//	public static void borrarCliente(Emplead cliente) throws SQLException {
//		openConnection();
//		Statement update = connection.createStatement();
//		String query = "DELETE FROM CLIENTE WHERE cedula = '" + cliente.getCedula()+"'";
//		update.execute(query);
//		
//	}
	
	
	/*
	 * Fin DB Empleado
	 */
	
	
	
	
	
	
	
	/*
	 * Inicio reportes
	 */
	
	public static ArrayList<Computador> reporteSimple1() throws SQLException{
		openConnection();
		ArrayList<Computador> computadores = new ArrayList<>();
		Statement update = connection.createStatement();
		
		String query="SELECT * FROM Computador c WHERE c.ocupado = 1";
		ResultSet rs = update.executeQuery(query);
		while(rs.next()) {
			String codigo = rs.getString("codigo");
			String categoria = rs.getString("categoria");
			int intOcupado = rs.getInt("ocupado");
			boolean ocupado=false;
			if(intOcupado==1)
				ocupado = true;
			
			Computador computador = new Computador(codigo, categoria, ocupado);
			computadores.add(computador);
		}
		
		
		return computadores;
	}
	
	
	public static ArrayList<Cliente> reporteSimple2 () throws SQLException
	{
		openConnection();
		ArrayList <Cliente> clientes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		String query = "SELECT * FROM Ciente c WHERE c.saldo > 0";
		ResultSet rs = update.executeQuery(query);
		while(rs.next()) {
			String nickname = rs.getString("nickname");
			String cedula = rs.getString("cedula");
			String correo = rs.getString("correo");
			String clave = rs.getString("clave");
			int saldo = rs.getInt("saldo");
			Cliente cliente = new Cliente(cedula, nickname, clave, correo, saldo);
			clientes.add(cliente);
			
			
		}
		
		return clientes;
		
		
	}
	
	public static ArrayList<Clase> reporteSimple3(String fecha) throws SQLException{
		openConnection();
		ArrayList <Clase> clases = new ArrayList<>();
		Statement update = connection.createStatement();
		
		String query = "SELECT * FROM Clase c WHERE c.fecha =" +" '"+ fecha+"'";
		ResultSet rs = update.executeQuery(query);
		while(rs.next()) {
			String codigo = rs.getString("codigo");
			Date fechaClase = rs.getDate("fecha");
			String descripcion = rs.getString("descripcion"); 
			String observacion = rs.getString("observacion");
			String cedulaCliente = rs.getString("cedulaCliente");
			String cedulaEmpleado = rs.getString("cedulaEmpleado");
			int duracion = rs.getInt("duracion");
			
			Clase clase = new Clase(codigo, fechaClase, descripcion, observacion, cedulaCliente, cedulaEmpleado, duracion);
			clases.add(clase);
		}
		
		return clases;
	}
	
	
	public static ArrayList<ReporteIntermedio1> reporteIntermedio1 (String fecha) throws SQLException
	{
		openConnection();
		ArrayList <ReporteIntermedio1> reportes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		String query = "SELECT * FROM Ciente c JOIN Clase cl WHERE cl.fecha =" +" '"+ fecha+"'";
		ResultSet rs = update.executeQuery(query);
		while(rs.next()) {
			String nickname = rs.getString("nickname");
			Date fechaClase = rs.getDate("fecha");
			String codigo  = rs.getString("codigo");
			
			ReporteIntermedio1 reporte = new ReporteIntermedio1(nickname, codigo, fechaClase);
			
			reportes.add(reporte);
		}
		
		return reportes;
		
		
	}
	

	public static ArrayList<Cliente> reporteIntermedio2 (String fechaActual, String fechaAnterior) throws SQLException
	{
		openConnection();
		ArrayList <Cliente> clientes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		String query = "SELECT * FROM Ciente c JOIN Recarga r JOIN Compra cp WHERE r.valorCargar > 5000 "
				+ "AND r.fecha BETWEEN '" + fechaActual +"' " + "AND '"
				+ fechaAnterior+"'";
		ResultSet rs = update.executeQuery(query);
		while(rs.next()) {
			String nickname = rs.getString("nickname");
			String cedula = rs.getString("cedula");
			String correo = rs.getString("correo");
			String clave = rs.getString("clave");
			int saldo = rs.getInt("saldo");
			Cliente cliente = new Cliente(cedula, nickname, clave, correo, saldo);
			clientes.add(cliente);
			
			
		}
		
		return clientes;
		
		
	}
	
	public static ArrayList<ReporteIntermedio3> reporteIntermedio3() throws SQLException{
		openConnection();
		ArrayList<ReporteIntermedio3> reportes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		String query = "SELECT r.*, c.codigo as codigoPC, e.nombre, c.categoria FROM ReporteMantenimiento r JOIN Computador c JOIN Empleado e";
		ResultSet rs = update.executeQuery(query);
		
		while(rs.next()) {
			String codigoMantenimiento = rs.getString("codigo");
			String codigoPC = rs.getString("codigoPC");
			String categoria = rs.getString("categoria");
			String empleado = rs.getString("nombre");
			String observacion = rs.getString("observacion");
			ReporteIntermedio3 reporte = new ReporteIntermedio3(categoria, codigoPC, codigoMantenimiento, empleado, observacion);
			reportes.add(reporte);
		}
		
		return reportes;
		
	}
	
	
	
	
}
