package es.florida.AE04_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerImportar, actionListenerConsulta1, actionListenerConsulta2,actionListenerConsulta, actionListenerBorrar;
	private String ficheroLectura;

	// Constructor pasamos los parametros desde main en principal
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		control(); // invocamos control para iniciar la app
	}

	
	public void control() {

		ficheroLectura = modelo.ficheroLectura();
		
		// Importar BDD
		actionListenerImportar = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {

				modelo.importarCSV(ficheroLectura);
			}
		};
		vista.getBtnImport().addActionListener(actionListenerImportar);

		//Consulta predefinida 1
		actionListenerConsulta1 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				limpiarCampos ();
				ArrayList<String> lineasconsulta = modelo.consultaDefinida1();
				presentarDatos(lineasconsulta);
			}
		};
		vista.getBtnConsulta1().addActionListener(actionListenerConsulta1);

		//Consulta predefinida 2
		actionListenerConsulta2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				limpiarCampos ();
				ArrayList<String> lineasconsulta = modelo.consultaDefinida2();
				presentarDatos(lineasconsulta);
			}
		};
		vista.getBtnConsulta2().addActionListener(actionListenerConsulta2);

		//Consulta libre
		actionListenerConsulta = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				limpiarCampos ();
				String consulta = vista.getTextField().getText();
				ArrayList<String> lineasconsulta = modelo.consultaLibre(consulta);
				presentarDatos(lineasconsulta);
				vista.getTextField().setText("");
				
			}
		};
		vista.getBtnConsulta().addActionListener(actionListenerConsulta);

		//Limpiar todos los campos de la ventana
		actionListenerBorrar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				limpiarCampos ();
			}
		};
		vista.getBtnBorrar().addActionListener(actionListenerBorrar);		
		
	}

	
	/*Método limpiarCampos()
	 * Action:	borra el contenido de los textArea para no acumular consultas unas encima
	 * de otras.
	 * INPUT: 	nada.
	 * OUTPUT:	borra el contenido de los textArea*/
	public void limpiarCampos () {
		vista.getTextArea().setText("");
		vista.getTextArea1().setText("");
	}
	
	
	/*Método presentarDatos()
	 * Action:	recibe un Arraylist con el resultado de la consulta correspondiente, 
	 * lo recorre y lo escribe en el textArea correspondiente. Los títulos que 
	 * van el la primera posición los pone en un textArea diferente para que sean más
	 * visibles y ayude a interpretar los datos.
	 * INPUT:	ArrayList con resultado consulta.
	 * OUTPUT: 	Presenta en la ventana dentro del TextArea correspondiente*/
	public void presentarDatos(ArrayList<String> lineasconsulta) {

		int cont = 0;
		for (String linea : lineasconsulta) {
			if (cont == 0)
				vista.getTextArea1().append(linea);
			else
				vista.getTextArea().append(linea + "\n");
			cont++;
		}
	}
}