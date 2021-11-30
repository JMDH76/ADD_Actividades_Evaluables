package es.florida.Ex_1aEval;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class App {

	public static void main(String[] args) {

		ArrayList<Config> configurations = new ArrayList<Config>();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document document = dBuilder.parse(new File("./config.xml"));

			Element raiz = document.getDocumentElement();

			NodeList nodelist = document.getElementsByTagName("config1");
			int numeronodos = nodelist.getLength();

			System.out.println(
					"El nodo principal \"" + raiz.getNodeName() + "\" contiene " + numeronodos + " nodos u objetos.");

			for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(0);
				Element eElement = (Element) node;

				String url = eElement.getElementsByTagName("url").item(0).getTextContent();
				String user = eElement.getElementsByTagName("user").item(0).getTextContent();
				String password = eElement.getElementsByTagName("password").item(0).getTextContent();

				Config config = new Config(url, user, password);
				configurations.add(config);
			}

			int contador = 0;
			for (Config config : configurations) {
				if (contador == 0) {
					String url = config.getUrl();
					String user = config.getUser();
					String password = config.getPassword();

					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, password);
					if (!con.equals(null))
						System.out.println("Conexión establecida con éxito\n");
					else
						System.out.println("Conexion Fallida");

					Scanner teclado = new Scanner(System.in);
					System.out.print("Indique un precio máximo: ");
					String preciomax = teclado.nextLine();

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM precios WHERE precio <= " + preciomax);

					System.out.println("\nLos productos que valen menos de " + preciomax + "€ son:");
					while (rs.next()) {
						System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "€");
					}

					InputStreamReader texto = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader(texto);
					// Crea el nuevo fichero
					File f = new File("ConsultaSQL.txt");
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
					String linea = br.readLine();

					while (linea != null) {
						bw.write(linea);
						bw.newLine();
						linea = br.readLine();
					}

					bw.close();
					fw.close();
					br.close();
					teclado.close();

					rs.close();
					stmt.close();
					con.close();
				}
			}
		
		} catch (ParserConfigurationException |  SAXException | IOException | ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
