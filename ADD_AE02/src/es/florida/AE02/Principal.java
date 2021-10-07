package es.florida.AE02;

public class Principal {

	public static void main(String[] args) {
		
//		Vista vista = new Vista();
		Modelo modelo = new Modelo();
//		Controlador controlador = new Controlador (modelo, vista);
		
		
		
		//Muestra por consola texto
		int cont = 0;
		for (String l : modelo.contenidofichero(modelo.ficheroLectura())) {
			System.out.println(l);
			cont++;
				}
		System.out.println("\n" + cont + " lineas");
		
		//Añade texto al fichero nuevo
		//modelo.anyadirTexto("Hola caracola");
		
		
		//Cuenta las palabras
		String palabrabuscada = "no";
		System.out.println("La palabra \"" + palabrabuscada + "\" se repite "   + " " + modelo.buscarPalabra(modelo.contenidofichero(args[0]), palabrabuscada) + " veces en el texto\n");
		
		
		
		//Reemplaza texto
		String textoReemplazar = "XX";
		modelo.reemplazarTexto(modelo.contenidofichero(args[0]), palabrabuscada, textoReemplazar);
		
		
	}

}