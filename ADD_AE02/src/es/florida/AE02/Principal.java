package es.florida.AE02;

public class Principal {

	public static void main(String[] args) {
		
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador (modelo, vista);
		
		
		
		
		
		
//		// Muestra por consola texto
//		for (String l : modelo.contenidofichero(modelo.ficheroLectura())) {
//			System.out.println(l);
//		}
//
//		// Cuenta las palabras
//		String palabrabuscada = "no";
//		System.out.println("\nLa palabra \"" + palabrabuscada + "\" aparece "
//				+ modelo.buscarPalabra(palabrabuscada) + " veces en el texto\n");

		// Reemplaza texto
		//String textoReemplazar = "**";
		//modelo.copiarfichero( palabrabuscada, textoReemplazar);
	
	}

}