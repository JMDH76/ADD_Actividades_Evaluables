package es.florida.AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador {
	
	
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBuscar;
	private String  ficheroLectura, ficheroEscritura;
	
	//Se lo pasamos desde principal
	public Controlador (Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	//Recoge los nombres de los ficheros
	public void control() {
		
		ficheroLectura = modelo.ficheroLectura();
		ficheroEscritura = modelo.ficheroEscritura();
		
		mostrarFichero(ficheroLectura, 1);
//		actionListenerBuscar = new ActionListener() {
//			
//		}
	
	
	}
	
	
	public void mostrarFichero(String fichero, int TextArea) {}
	
//	public void mostrarFichero(String fichero, int TextArea) {
//		ArrayList<String> arrayLineas = modelo.contenidofichero(fichero);
//		for (String linea : arrayLineas) {
//			if (TextArea == 1) {
//				vista.getTextFieldBuscar().append(linea+"\n");
//			} else {
//				//vista.get;
//			}
//		
//	}
//		
//	}
}
