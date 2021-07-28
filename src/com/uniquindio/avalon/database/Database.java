package com.uniquindio.avalon.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.uniquindio.avalon.logica.Administrador;
import com.uniquindio.avalon.logica.Ciudad;
import com.uniquindio.avalon.logica.Clase;
import com.uniquindio.avalon.logica.Cliente;
import com.uniquindio.avalon.logica.CompraProducto;
import com.uniquindio.avalon.logica.Computador;
import com.uniquindio.avalon.logica.Empleado;
import com.uniquindio.avalon.logica.Producto;
import com.uniquindio.avalon.logica.Recarga;
import com.uniquindio.avalon.logica.ReporteComplejo3;
import com.uniquindio.avalon.logica.ReporteIntermedio1;
import com.uniquindio.avalon.logica.ReporteIntermedio3;
import com.uniquindio.avalon.logica.ReporteIntermedio4;

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
			dropAllTables();
			createTables();
			crearCiudades();
			llenarQuerysPrueba();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void llenarQuerysPrueba(){
		ArrayList<String> lista = new ArrayList<>();
		 try {
		      File myObj = new File("DBReportes.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		       lista.add(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 Statement update;
		 String sa ="";
		try {
			update = connection.createStatement();
			for(String s : lista) {
				sa=s;
				 update.execute(s);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sa);
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
//
//		createTables();

	}
	
	public static void createTables() throws SQLException {
		String administrador = "CREATE TABLE IF NOT EXISTS Administrador (nickname VARCHAR(80) NOT NULL UNIQUE, clave VARCHAR(100) NOT NULL, PRIMARY KEY(nickname))";
		String cliente = "CREATE TABLE IF NOT EXISTS Cliente (cedula VARCHAR(10) NOT NULL, nickname VARCHAR(80) NOT NULL UNIQUE, clave VARCHAR(100) NOT NULL, correo VARCHAR(80) NOT NULL, saldo INTEGER, PRIMARY KEY(cedula, nickname))";
		String departamento = "CREATE TABLE IF NOT EXISTS Departamento (nombre VARCHAR(50), codigo INTEGER ,PRIMARY KEY(codigo))";
		String ciudad = "CREATE TABLE IF NOT EXISTS Ciudad (nombre VARCHAR(50), codigo INTEGER, codigoDepartamento INTEGER, PRIMARY KEY(codigo), FOREIGN KEY(codigoDepartamento) REFERENCES Departamento(codigo))";
		String empleado = "CREATE TABLE IF NOT EXISTS Empleado (cedula VARCHAR(10), nombre VARCHAR(80), correo VARCHAR(80), direccion VARCHAR(80), codigoCiudad INTEGER, PRIMARY KEY(cedula), FOREIGN KEY(codigoCiudad) REFERENCES Ciudad(codigo))";
		String recarga = "CREATE TABLE IF NOT EXISTS Recarga (codigo VARCHAR(80), total INTEGER, valorCargar INTEGER, fecha DATE, cedulaCliente VARCHAR(10), cedulaEmpleado VARCHAR(10), PRIMARY KEY(codigo), FOREIGN KEY(cedulaCliente) REFERENCES Cliente(cedula), FOREIGN KEY(cedulaEmpleado) REFERENCES Empleado(cedula))";
		String compra = "CREATE TABLE IF NOT EXISTS Compra (codigo VARCHAR(80), fecha DATE, direccion VARCHAR(80), cedulaCliente VARCHAR(10), telefono VARCHAR(15), codigoCiudad INTEGER, PRIMARY KEY(codigo), FOREIGN KEY(cedulaCliente) REFERENCES Cliente(cedula), FOREIGN KEY(codigoCiudad) REFERENCES Ciudad(codigo))";
		String producto = "CREATE TABLE IF NOT EXISTS Producto (codigo VARCHAR(80), descripcion VARCHAR(600), nombre VARCHAR(80), precio INTEGER, fechaInicioGarantia DATE, fechaFinGarantia DATE, PRIMARY KEY(codigo))";
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
		update.execute(administrador);
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
	
	public static Ciudad loadCiudad(int codigo) throws SQLException {
		openConnection();
		Statement update = connection.createStatement(); 
		String query = "SELECT * FROM Ciudad WHERE Ciudad.codigo = " + codigo;
		Ciudad ciudad = null;
		ResultSet rs = update.executeQuery(query);
		if(rs.next()) {
			String nombre = rs.getString("nombre");
			int codigoDepartamento = rs.getInt("codigoDepartamento");
			ciudad = new Ciudad(nombre, codigo, codigoDepartamento);
		}
		
		return ciudad;
	}
	
	public static int getCiudadCodigo(String nombre) throws SQLException {
		openConnection();
		Statement update = connection.createStatement(); 
		String query = "SELECT * FROM Ciudad WHERE Ciudad.nombre = '" + nombre +  "'";
		ResultSet rs = update.executeQuery(query);
		if(rs.next()) {
			return rs.getInt("codigo");
		}
		
		
		return -1;
		
		
	}
	
	
	public static Administrador loadAdministrador(String nickname) throws SQLException {
		openConnection();
		Statement update = connection.createStatement(); 
		String query = "SELECT * FROM Administrador a WHERE a.nickname = '" + nickname +"'";
		Administrador admin=null;
		ResultSet rs = update.executeQuery(query);
		if(rs.next()) {
			String clave = rs.getString("clave");
			admin = new Administrador(nickname, clave);
		}
		
		return admin;
	}
	
	public static ArrayList<Ciudad> loadCiudades() throws SQLException{
		openConnection();
		ArrayList<Ciudad> ciudades = new ArrayList<>();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Ciudad";
		ResultSet rs = update.executeQuery(query);
		while(rs.next()) {
			String nombre = rs.getString("nombre");
			int codigo = rs.getInt("codigo");
			int codigoDepartamento = rs.getInt("codigoDepartamento");
			
			Ciudad ciudad = new Ciudad(nombre, codigo, codigoDepartamento);
			ciudades.add(ciudad);
		}
		
		
		return ciudades;
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
	
	public static Cliente loadClientbyId(String cedula) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Cliente WHERE Cliente.cedula = '" + cedula + "'";
		Cliente cliente = null;
		ResultSet rs = update.executeQuery(query);
		if(rs.next()) {
			String nickname = rs.getString("nickname");
			String cedulaCliente = rs.getString("cedula");
			String correo = rs.getString("correo");
			String clave = rs.getString("clave");
			int saldo = rs.getInt("saldo");
			cliente = new Cliente(cedulaCliente, nickname, clave, correo, saldo);
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
		String query = "UPDATE Cliente SET correo = '" + cliente.getCorreo() + "', clave = '" + cliente.getClave() + "' WHERE cedula = '" + cliente.getCedula()+"'";
		update.execute(query);
	}
	
	public static void borrarCliente(Cliente cliente) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "DELETE FROM Cliente WHERE cedula = '" + cliente.getCedula()+"'";
		update.execute(query);
		
	}
	
	/*
	 * Fin DB Cliente
	 */
	
	/* Inicio DB Recarga
	 */
	
	public static ArrayList<Recarga> loadRecarga() throws SQLException {
		openConnection();
		ArrayList<Recarga> recargas = new ArrayList<>();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Recarga";;
		ResultSet rs = update.executeQuery(query);
		 while(rs.next()) {
			 	String codigo = rs.getString("codigo");
				int total  = rs.getInt("total");
				int valorCargar = rs.getInt("valorCargar");
				Date fecha = rs.getDate("fecha");
				String cedulaCliente = rs.getString("cedulaCliente");
				String cedulaEmpleado  = rs.getString("cedulaEmpleado");
				Recarga recarga = new Recarga(codigo, total, valorCargar, fecha, cedulaCliente, cedulaEmpleado);
				recargas.add(recarga);
				
		 }
		 
		return recargas;
		
	}
	
	public static void addRecarga(Recarga recarga) throws SQLException {
		openConnection();
		
		System.out.println("DB" + convertDate(recarga.getFecha()) );
		Statement update = connection.createStatement(); 
		String query = "INSERT INTO Recarga VALUES('" + recarga.getCodigo()+"', '" + recarga.getTotal()+"', '" + recarga.getValorCargar() +"', '" + convertDate(recarga.getFecha())+"', '" + recarga.getClienteCedula()+"', '" + recarga.getEmpleadoCedula()+"')";
		update.execute(query);
		
	}
	
	public static void actualizarRecarga(Recarga recarga) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "UPDATE Recarga SET  total = "+ recarga.getTotal() +", valorCargar = " + recarga.getValorCargar() + ", fecha = '" + convertDate(recarga.getFecha())+"', cedulaCliente = '" + recarga.getClienteCedula() +"', cedulaEmpleado = '" + recarga.getEmpleadoCedula() +"' WHERE codigo = '" + recarga.getCodigo()+ "'";
		update.execute(query);
	}
	
	public static void borrarRecarga(Recarga recarga) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "DELETE FROM Recarga WHERE codigo = '" + recarga.getCodigo()+"'";
		update.execute(query);
		
	}
	
	/**
	 * 
	 * Fin de DB Recarga
	 * 
	 */
	
	
	/*
	 * Inicio DB Empleado
	 */
	public static ArrayList<Empleado> loadEmpleados() throws SQLException {
		openConnection();
		ArrayList<Empleado> empleados = new ArrayList<>();
		Statement update = connection.createStatement();
		String query = "SELECT e.*, c.nombre as ciudadNombre FROM Empleado e JOIN Ciudad c ON c.codigo = e.codigoCiudad;";
		ResultSet rs = update.executeQuery(query);
		 while(rs.next()) {
			 	String cedula = rs.getString("cedula");
				String correo = rs.getString("correo");
				String nombre = rs.getString("nombre");
				String ciudad = rs.getString("ciudadNombre");
				String direccion = rs.getString("direccion");
				
				Empleado empleado = new Empleado(cedula, nombre, ciudad, correo, direccion);
				empleados.add(empleado);
				
		 }
		 
		return empleados;
		
	}
	
	public static Empleado loadEmpleado (String cedula) throws SQLException {
		openConnection();
		Empleado empleado = null;
		Statement update = connection.createStatement();
		String query = "SELECT  e.*,c.nombre as ciudadNombre From Empleado e JOIN Ciudad c ON c.codigo = e.codigoCiudad WHERE cedula = '" +cedula+ "'" ;
		ResultSet rs = update.executeQuery(query);
		 while(rs.next()) {
			 	String cedulaEmpleado = rs.getString("cedula");
				String correo = rs.getString("correo");
				String nombre = rs.getString("nombre");
				String ciudad = rs.getString("ciudadNombre");
				String direccion = rs.getString("direccion");
				
				empleado = new Empleado(cedulaEmpleado, nombre, ciudad, correo, direccion);
		 }
		
		return empleado;
	}

	
	
	public static void addEmpleado(Empleado empleado) throws SQLException {
		openConnection();
		Statement update = connection.createStatement(); 
		String query = "INSERT INTO Empleado VALUES('" + empleado.getCedula()+"', '" + empleado.getNombre()+"', '" + empleado.getCorreo()+"', '" + empleado.getDireccion()+"', " + getCiudadCodigo(empleado.getCiudad())+")";
		update.execute(query);
		
	}
	
	public static void actualizarEmpleado(Empleado empleado) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "UPDATE Empleado SET correo = '" + empleado.getCorreo() + "', nombre = '" + empleado.getNombre() + "', direccion = '" + empleado.getDireccion() +"', codigoCiudad = " +getCiudadCodigo(empleado.getCiudad()) +" WHERE cedula = '" + empleado.getCedula()+"'";
		update.execute(query);
	}
	
	public static void borrarEmpleado(Empleado empleado) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "DELETE FROM Empleado WHERE cedula = '" + empleado.getCedula()+"'";
		update.execute(query);
		
	}
	
	
	/*
	 * Fin DB Empleado
	 */
	
	/*
	 * Inico DB Producto
	 */
	public static ArrayList<Producto> loadProductos() throws SQLException {
		openConnection();
		ArrayList<Producto> productos = new ArrayList<>();
		Statement update = connection.createStatement();
		String query = "SELECT * FROM Producto";;
		ResultSet rs = update.executeQuery(query);
		 while(rs.next()) {
			 	String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				int precio = rs.getInt("precio");
				Date fechaInicioGarantia = rs.getDate("fechaInicioGarantia");
				Date fechaFinGarantia = rs.getDate("fechaFinGarantia");
				
				Producto producto = new Producto(codigo, descripcion, nombre, precio, fechaInicioGarantia, fechaFinGarantia);
				productos.add(producto);
				
		 }
		 
		return productos;
		
	}

	
	
	public static void addProducto(Producto producto) throws SQLException {
		openConnection();
		Statement update = connection.createStatement(); 
		String query = "INSERT INTO Producto VALUES('" + producto.getCodigo()+"', '" + producto.getDescripcion()+"', '" + producto.getNombre() +"', " + producto.getPrecio()+", '" +convertDate(producto.getFechaInicioGarantia())+ "', '" + convertDate(producto.getFechaFinGarantia())+"')";
		update.execute(query);
		
	}
	
	public static void actualizarProducto(Producto producto) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "UPDATE Producto SET descripcion = '" + producto.getDescripcion() + "', nombre = '" + producto.getNombre() +"', precio = " + producto.getPrecio() + " WHERE codigo = '" + producto.getCodigo()+"'";
		update.execute(query);
	}
	
	public static void borrarProducto(Producto producto) throws SQLException {
		openConnection();
		Statement update = connection.createStatement();
		String query = "DELETE FROM Producto WHERE codigo = '" + producto.getCodigo()+"'";
		update.execute(query);
		
	}
	
	/*
	 * Fin DB Producto
	 */
	
	public static String convertDate(Date fecha)
	{
		String formato = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		formato = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
		
		return formato;
	}

	
/*
 * Recarga DB Recarga
 * 
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
		
		String query = "SELECT * FROM Cliente c WHERE c.saldo > 0";
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
		
		String query = "SELECT * FROM Cliente c INNER JOIN Clase cl ON c.cedula = cl.cedulaCliente WHERE cl.fecha =" +" DATE '"+ fecha+"'";
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
		
		String query = "SELECT * FROM (Cliente c JOIN Recarga r ON c.cedula = r.cedulaCliente) JOIN Compra cp ON cp.cedulaCliente = c.cedula WHERE r.valorCargar > 5000 "
				+ "AND r.fecha BETWEEN '" + fechaAnterior +"' " + "AND '"
				+ fechaActual+"'";
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
		
		String query = "SELECT r.*, c.codigo as codigoPC, e.nombre, c.categoria FROM (ReporteMantenimiento r JOIN Computador c ON r.codigoComputador = c.codigo) JOIN Empleado e ON r.cedulaEmpleado = e.cedula ORDER BY c.categoria ASC; ";
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
	
	public static ArrayList<ReporteIntermedio4> reporteIntermedio4(String fechaFinal, String fechaInicio) throws SQLException{
		openConnection();
		ArrayList<ReporteIntermedio4> reportes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		ResultSet rs = update.executeQuery("SELECT e.cedula, e.nombre, max(r.valorCargar) as maximaRecarga FROM Empleado e Join Recarga r ON e.cedula = r.cedulaEmpleado WHERE r.fecha BETWEEN '" + fechaInicio + "' AND '"  + fechaFinal + "' GROUP BY e.cedula;");
		
		while(rs.next()) {
			String cedula = rs.getString("cedula");
			String nombre = rs.getString("nombre");
			int maximaRecarga = rs.getInt("maximaRecarga");
			ReporteIntermedio4 ri = new ReporteIntermedio4(cedula, nombre, maximaRecarga);
			reportes.add(ri);
		}
		
		return reportes;
	}
	
	public static ArrayList<Cliente> reporteComplejo1(String fechaFinal, String fechaInicio) throws SQLException{
		openConnection();
		ArrayList<Cliente> reportes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		ResultSet rs = update.executeQuery("SELECT c.nickname from Cliente c JOIN Prestamo p ON c.cedula = p.cedulaCliente WHERE p.fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFinal + "' AND c.cedula NOT IN (SELECT c.cedula from Cliente c JOIN Recarga r ON c.cedula = r.cedulaCliente);");
		while(rs.next()) {
			String nickname = rs.getString("nickname");
			Cliente cliente = new Cliente(nickname);
			reportes.add(cliente);
		}
		
		return reportes;
	}
	
	public static ArrayList<CompraProducto> reporteComplejo2() throws SQLException{
		openConnection();
		ArrayList<CompraProducto> reportes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		ResultSet rs = update.executeQuery("SELECT p.nombre , count(p.codigo) as cantidadVentas from Producto p JOIN CompraProducto cp  ON cp.codigoProducto = p.codigo    JOIN ProductoProveedor pp ON pp.codigoProducto = p.codigo WHERE p.precio > 100000 AND pp.nitProveedor NOT IN (SELECT nit from Proveedor p WHERE nombre = 'Razer') GROUP BY p.codigo;");
		while(rs.next()) {
			String nombre = rs.getString("nombre");
			int cantVentas = rs.getInt("cantidadVentas");
			CompraProducto cp = new CompraProducto(cantVentas, nombre);
			reportes.add(cp);
		}
		
		return reportes;
	}
	
	public static ArrayList<ReporteComplejo3> reporteComplejo3() throws SQLException {
		openConnection();
		ArrayList<ReporteComplejo3> reportes = new ArrayList<>();
		Statement update = connection.createStatement();
		
		ResultSet rs = update.executeQuery("SELECT e.nombre, count(r.codigo) as cantidadRecargas from Empleado e JOIN Recarga r on e.cedula = r.cedulaEmpleado WHERE r.cedulaCliente IN (SELECT c.cedula from Cliente c JOIN Clase cl on c.cedula = cl.cedulaCliente) GROUP BY e.cedula;");
		while(rs.next()) {
			String nombre = rs.getString("nombre");
			int cantidad = rs.getInt("cantidadRecargas");
			
			ReporteComplejo3 rc = new ReporteComplejo3(cantidad, nombre);
			reportes.add(rc);
		}
		
		return reportes;
	}
	
	/*
	 * Fin reportes
	 */
	

	
	
}
