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
		control();			//invocamos control para iniciar la app
	}
	
/*	METODO: control()
	DESCRIPCION: Llama a las funciones de inicio y asigna las acciones de 
	cada botón mediante los ActionListener
	INPUT: No recibe input, es invocado desde el constructor de Modelo
	OUTPUT: ejecuta en la interfaz gráfica las funciones solicitadas al
	pulsar los diferentes botones.
	 */
	public void control() {
		
		ficheroLectura = modelo.ficheroLectura();
		ficheroEscritura = modelo.ficheroEscritura();
		
		//Muestra fichero en el TextArea superior al inicio de la aplicación
		mostrarFichero(ficheroLectura, 1);  
		
		//Acción a realizar cuando pulsemos "Buscar"
		actionListenerBuscar = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				
				//Recoge el texto introducido en el campo buscar
				String textoBuscar = vista.getTextFieldBuscar().getText();
				
				// Invoca a buscarPalabra() y las cuenta
				int repeticiones = modelo.buscarPalabra(textoBuscar); 
				
				JOptionPane.showMessageDialog(new JFrame(),
						"La palabra \"" + textoBuscar + "\" aparece " + repeticiones + " veces en el texto");
			}
		};
		
		//Acción a realizar cuando pulsemos "Reemplazar"
		actionListenerReemplazar = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				
				//Borramos el TextArea para que los textos modificados no se acumulen
				vista.getTextAreaModificado().setText(""); 

				//Recoge texto de los TextFields
				String textoBuscar = vista.getTextFieldBuscar().getText();
				String textoReemplazar = vista.getTextFieldReemplazar().getText(); 
				
				//Invoca a copiarFichero() que a su vez invoca a reemplazarTexto()	
				modelo.copiarFichero(textoBuscar, textoReemplazar); 
				
				//Muestra ficheroEscritura en el TextArea inferior
				mostrarFichero(ficheroEscritura, 2);	
			}
		};
		//Le asignamos a cada botón el ActionListener que debe ejecutar al ser pulsado
		vista.getBtnBuscar().addActionListener(actionListenerBuscar); 
		vista.getBtnReemplazar().addActionListener(actionListenerReemplazar);
	}
	

/*	METODO: mostrarFichero()
	DESCRIPCION: presenta el texto en el TextArea supoerior o inferir según se le solicite
	INPUT: String con fichero a mostrar un Int indicando por que TextaArea hay que sacarlo 
	OUTPUT: Contenido del texto del fichero por el TextArea elegida
	 */
	public void mostrarFichero(String fichero, int TextArea) {
		
		ArrayList<String> arrayLineas = modelo.contenidoFichero(fichero);
		
		for (String linea : arrayLineas) {
			if (TextArea == 1) 	vista.getTextAreaOriginal().append(linea + "\n");
			else vista.getTextAreaModificado().append(linea + "\n");
		}
	}
	
}
