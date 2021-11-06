package es.florida.AE04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

	static String separadordatos = " | ";
	
	public static void main(String[] args) {

		ArrayList<Libro> libros = new ArrayList<Libro>();

		try {
			//Conexion a BDD
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ae04_biblioteca", "root", "");
			comprobarConexion(con) ;

			//Copia del fichero
			File f = new File("src\\es\\florida\\AE04\\Datos_CSV\\AE04_T1_4_JDBC_Datos.csv");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine();
			String dato = "";
			int index = -1;

			//Creamos objetos libro
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
			 //Recorremos Array con objetos libro y completamos la instruccion SQL para importar el fichero
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
			
			//Consulta predefinida 1. Publicaciones de escritores nacidos antes de 1950
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT titulo, autor, anyo_publicacion FROM `libros` WHERE anyo_nacimiento < '1950'");
			
			System.out.println("\nConsulta 01. Publicaciones de escritores nacidos antes de 1950");
			System.out.println("--------------------------------------------------------------");
			titulosColumnas(rs);
			while (rs.next()) {
				System.out.println(rs.getString(1) + separadordatos + rs.getString(2) + separadordatos + rs.getString(3));
			}
			
			//Consulta predefionioda 2. Editoriales que han publicado en el siglo XXI.
			ResultSet rs2 = stmt.executeQuery("SELECT editorial, COUNT(titulo) AS 'Libros' FROM `libros` WHERE anyo_publicacion > '2000' GROUP BY editorial");
		
			System.out.println();
			System.out.println("\nConsulta 02. Editoriales que han publicado en el siglo XXI");
			System.out.println("----------------------------------------------------------");
			
			titulosColumnas(rs2);
			
			while (rs2.next()) {
				System.out.println(rs2.getString(1)  + separadordatos + rs2.getString(2));
			}
			
			//Consulta libre. Introducimos la consulta SQL por teclado. La ejecuta y presenta resultado por pantalla
			String consulta = "";
			BufferedReader brconsulta = new BufferedReader(new InputStreamReader(System.in));
			System.out.println();
			System.out.print("¿Desea realizar alguna consulta? (s/n) ");
			String respuesta = brconsulta.readLine();
			
			while(respuesta.toLowerCase().equals("s")) {
				
				System.out.print("\nInserte su consulta SQL: ");
				consulta = brconsulta.readLine();

				System.out.println("\nResultado de su consulta: \n-------------------------");
				ResultSet rs3 = stmt.executeQuery(consulta);
				
				int numerocolumnas = titulosColumnas(rs3);
				while (rs3.next()) {
					for (int i = 1; i <= numerocolumnas; i++) {
						if (i != numerocolumnas)  System.out.print(rs3.getString(i) + separadordatos);
						else System.out.printf(rs3.getString(i));
					}
					System.out.println();
				}
				System.out.print("\n¿Desea realizar otra consulta? (s/n) ");
				respuesta = brconsulta.readLine();
				rs3.close();
			}
			
			br.close();
			brconsulta.close();
			con.close();
			rs.close();
			rs2.close();
			stmt.close();
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		System.out.print("Fin de la aplicación.");
	}


	/*Método titulosColumnas()
	 * ACTION: obtiene los nombres de las columnas de la consulta y los escribe por pantalla 
	 * para la presentacion de datos. Devuelve un Int con el número de columnas que devuelve 
	 * la consulta.
	 * INPUT:	Objeto ResultSet con la consulta
	 * OUTPUT:	Devuelve el número de columnas y escribe en pantalla el titulo de las columnas*/
	public static int titulosColumnas(ResultSet rs) {
		
		int numerocolumnas=0;
		try {
		ResultSetMetaData rsmd = rs.getMetaData();
			numerocolumnas = rsmd.getColumnCount();
			for (int i = 1; i <= numerocolumnas; i++) {
				String nombrecolumna = rsmd.getColumnName(i);
				if (i != numerocolumnas) System.out.print(nombrecolumna + separadordatos);
				else System.out.print(nombrecolumna);
			}
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numerocolumnas;
	}
	
	

	/*
	 * Metodo ComprobarConexion() 
	 * ACTION: 	comprueba si se ha conectado con la BDD.
	 * Si la vble "con" es null es uqe no se ha conectado. INPUT: recibe el objeto
	 * Connection con. OUTPUT: Devuelve un mensaje con el resultado de la conexion.
	 * INPUT: 	Objeto Connect.
	 * OUTPUT:	Mensaje con resultado de la conexion.
	 */
	public static void comprobarConexion(Connection con) {
		if (!con.equals(null))
			System.out.println("Conexión establecida con éxito\n");
		else 
			System.out.println("Conexion Fallida");

		}
}
