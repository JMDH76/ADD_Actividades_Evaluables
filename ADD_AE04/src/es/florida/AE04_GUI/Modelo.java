package es.florida.AE04_GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Modelo {
	
	static String separadordatos = " | "; //Nos permite cambiar el saparador de los datos
	private String fichero_lectura;
	
	public Modelo() {
		fichero_lectura = "src\\es\\florida\\AE04_GUI\\AE04_T1_4_JDBC_Datos.csv";
	}
		
	//Getter path fichero CSV
	public String ficheroLectura() {
		return fichero_lectura;
	}

	/*Método importarCSV()
	 * ACTION:	Abre el fichero CSV lo copia y crea un objeto libro con cada linea del fichero
	 * y los incluye en un ArrayList de libros; luego lo recorremos y extraemos los parámetros 
	 * de cada objeto libro y los pasamos a la instruccion SQL para que los inserte en el BDD
	 * INPUT: String con la ruta del fichero que hay que importar
	 * OUTPUT:	Inserta los datos en la BDD*/
	public void importarCSV (String ficheroLectura) {
		
		ArrayList<Libro> libros = new ArrayList<Libro>();
		try {
						
			Connection con = abrirConexion();			
			
			File f = new File(fichero_lectura);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine();
			String dato = "";
			int index = -1;

			while (linea != null) {
				String datoslibro[] = new String[6];
				
				for (int i = 0; i < datoslibro.length; i++) {
					index = linea.indexOf(";");
					if (index == -1) dato = linea;
					else dato = linea.substring(0, index);
					if (dato == "") dato = "N.C";
					datoslibro[i] = dato;
					linea = linea.substring(index + 1);
				}

				Libro libro = new Libro(datoslibro[0], datoslibro[1], datoslibro[2], datoslibro[3], datoslibro[4], datoslibro[5]);
				libros.add(libro);
				linea = br.readLine();
			}

			int cont = 1;
			for (Libro libro : libros) {

				if (cont > 1) {
					PreparedStatement psInsertar = con.prepareStatement(
							"INSERT INTO libros (titulo, autor, anyo_nacimiento, anyo_publicacion, editorial, num_paginas) VALUES (?,?,?,?,?,?)");
					psInsertar.setString(1, libro.getTitulo());
					psInsertar.setString(2, libro.getAutor());
					psInsertar.setString(3, libro.getAnyo_nacimineto());
					psInsertar.setString(4, libro.getAnyo_publicacion());
					psInsertar.setString(5, libro.getEditorial());
					psInsertar.setString(6, libro.getNum_paginas());
					int resultadoInsertar = psInsertar.executeUpdate();
				}
				cont++;
			}
			fr.close();
			br.close();
			con.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/*Método consultaDefinida1()
	 * ACTION:	Consulta ya predefinida port el enunciado del ejercicio pasamos una 
	 * consulta en SQL sobre la BDD del ejercicio, la lee e incluye todas las líneas
	 * en un ArrayList. En la posicion 0 del Array ponemos los títulos de las colunmas
	 * para luego poder presentarlas por separado del resto de la consulta.
	 * INPUT:	consulta SQL
	 * OUTPUT: ArrayList con con el resultado de la consulta*/
	public ArrayList<String> consultaDefinida1() {
		
		ArrayList<String> lineasconsulta = new ArrayList<String>();
		try {
			Connection con = abrirConexion();
			Statement  stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT titulo, autor, anyo_publicacion FROM `libros` WHERE anyo_nacimiento < 1950");

			lineasconsulta.add(titulosColumnas(rs));
			while (rs.next()) {
				lineasconsulta.add(rs.getString(1) + separadordatos + rs.getString(2) + separadordatos + rs.getString(3));
			}
			rs.close();
			stmt.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return lineasconsulta;
	}

	
	/*Método consultaDefinida2()
	 * ACTION:	Consulta ya predefinida port el enunciado del ejercicio pasamos una 
	 * consulta en SQL sobre la BDD del ejercicio, la lee e incluye todas las líneas
	 * en un ArrayList. En la posicion 0 del Array ponemos los títulos de las colunmas
	 * para luego poder presentarlas por separado del resto de la consulta.
	 * INPUT:	consulta SQL
	 * OUTPUT: ArrayList con con el resultado de la consulta*/
	public ArrayList<String> consultaDefinida2() {
		ArrayList<String> lineasconsulta = new ArrayList<String>();
		try {

			Connection con = abrirConexion();
			Statement  stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT editorial, COUNT(titulo) AS 'Libros' FROM `libros` WHERE anyo_publicacion > '2000' GROUP BY editorial");
			
			lineasconsulta.add(titulosColumnas(rs));
			
			while (rs.next()) {
				lineasconsulta.add(rs.getString(1) + separadordatos + rs.getString(2));
			}
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return lineasconsulta;
	}
	
	
	
	/*Método consultaLlibre()
	 * ACTION:	recibe un string con una consulta en SQL y la ejecuta guardando el resultado en un ArrayList
	 * que devolvemos para poder presentar más tarde. El número de columnas que devuelve la consulta lo obtenemos 
	 * mediante el objeto de ResultSetMetaData del que podemos extraer el dato con .getColumnCount; este dato lo
	 * usamos para el bucle donde creamos las lineas de la consulta ya que no sabemos cuantas columnas llevarán
	 * las posibles consultas que se solñiciten.
	 * INPUT:	consulta SQL.
	 * OUTPUT: 	ArrayList con el resultado de la consulta.
	 * */
	public ArrayList<String> consultaLibre(String consulta) {
		
		ArrayList<String> lineasconsulta = new ArrayList<String>();
		String lineasalida = "";
		try {
			Connection con = abrirConexion();
			Statement  stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(consulta);			
			ResultSetMetaData rsmd = rs.getMetaData();
			int numerocolumnas = rsmd.getColumnCount();

			lineasconsulta.add(titulosColumnas(rs));

			while (rs.next()) {
				for (int i = 1; i <= numerocolumnas; i++) {
					if (i != numerocolumnas)
						lineasalida = lineasalida + rs.getString(i) + separadordatos;
					else
						lineasalida = lineasalida + rs.getString(i);
				}
				lineasconsulta.add(lineasalida);
				lineasalida = "";
			}
			stmt.close();
			rs.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return lineasconsulta;
	}
	
	
	
	/*Método abrirConexion()
	 * Action: 	crea la conexion con la BDD y la devuelve para que se puedan ejecutar las consultas.
	 * INPUT:	Libreria externa a Java jdbc
	 * OUTPUT:	Objeto Connection con la conexion establecida*/
	public static Connection abrirConexion() throws ClassNotFoundException, SQLException {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ae04_biblioteca", "root", "");
			return con;
	}
	

	/*Método titulosColumnas()
	 * ACTION: obtiene los nombres de las columnas de la consulta y las devuelve en un String 
	 * para incluirlas en los Arraylist de las consultas y presentarlas más tarde por separado del 
	 * resultado de la consulta.
	 * INPUT:	Objeto ResultSet con la consulta
	 * OUTPUT:	Devuelve un String con los títulos de las columnas*/
	public static String titulosColumnas(ResultSet rs) {

		String lineatitulo = ""; 
		int numerocolumnas = 0;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			numerocolumnas = rsmd.getColumnCount();
			for (int i = 1; i <= numerocolumnas; i++) {
				String nombrecolumna = rsmd.getColumnName(i);
				if (i != numerocolumnas)
					lineatitulo =lineatitulo + nombrecolumna + separadordatos;
				else
					lineatitulo = lineatitulo + nombrecolumna;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return lineatitulo;
	}
}
