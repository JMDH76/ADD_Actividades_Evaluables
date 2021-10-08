package es.florida.AE02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBuscar, actionListenerReemplazar;
	private String  ficheroLectura, ficheroEscritura;
	
	//Constructor pasamos los parametros desde main en principal
	public Controlador (Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control();		//invocamos control para que 
	}
	
	//Recoge los nombres de los ficheros
	public void control() {
		
		ficheroLectura = modelo.ficheroLectura();
		ficheroEscritura = modelo.ficheroEscritura();
		
		mostrarFichero(ficheroLectura, 1);  //Muestra fichero en el TextArea superior
		
		//Espera a que pulsemos boton de "Buscar"
		actionListenerBuscar = new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				String textoBuscar = vista.getTextFieldBuscar().getText(); //Recoge el texto introducido en el campo buscar
				int repeticiones = modelo.buscarPalabra(textoBuscar);
				JOptionPane.showMessageDialog(new JFrame(), "La palabra \"" + textoBuscar + "\" aparece " + repeticiones + " veces en el texto");
			}
		};
		
		
		
		actionListenerReemplazar = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {

				vista.getTextAreaModificado().setText(""); //Borramos el TextArea para que los textos modificados no se acumulenunos debajo de otros

				String textoBuscar = vista.getTextFieldBuscar().getText();
				String textoReemplazar = vista.getTextFieldReemplazar().getText(); // Recoge el texto introducido en
																					// el campo Reemplazar
				modelo.copiarfichero(textoBuscar, textoReemplazar);
				mostrarFichero(ficheroEscritura, 2);
			}
		};
		
		vista.getBtnBuscar().addActionListener(actionListenerBuscar); //Le decimos al boton Buscar que ejecute la accion.
		vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
	}
	

	
	public void mostrarFichero(String fichero, int TextArea) {
		ArrayList<String> arrayLineas = modelo.contenidofichero(fichero);
		for (String linea : arrayLineas) {
			if (TextArea == 1) {
				vista.getTextAreaOriginal().append(linea + "\n");
			} else {
				vista.getTextAreaModificado().append(linea + "\n");
			}
		}
	}
}
