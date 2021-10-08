package es.florida.AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
		
		mostrarFichero(ficheroLectura, 1);  //Muestra fichero en el área superior
		
		//Espera a que pulsemos boton de "Buscar"
		actionListenerBuscar = new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				String textoBuscar = vista.getTextFieldBuscar().getText(); //Recoge el texto introducido en el campo buscar
				int repeticiones = modelo.buscarPalabra(textoBuscar);
				JOptionPane.showMessageDialog(null, "La palabra \"" + textoBuscar + "\" aparece " + repeticiones + " veces en el texto");
			}
		};
		vista.getBtnBuscar().addActionListener(actionListenerBuscar); //Le decimos al boton Buscar que ejecute la accion.
	}
	

	
	public void mostrarFichero(String fichero, int TextArea) {
		ArrayList<String> arrayLineas = modelo.contenidofichero(fichero);
		for (String linea : arrayLineas) {
			if (TextArea == 1) {
				vista.getTextAreaOriginal().append(linea+"\n");
			} else {
				vista.getTextAreaModificado().append(linea + "\n");
			}
		
	}
		
	}
}
