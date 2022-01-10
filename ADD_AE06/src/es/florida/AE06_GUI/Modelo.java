package es.florida.AE06_GUI;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collections;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Modelo {

	/*FUNCTION:	mostrarCatalogo()
	 * ACTION:	obtiene la id y el titulo de todos los libros, los junta en un String y los 
	 * incluye en una lista que posteriormente ordenaremos y presentaremos por pantalla
	 * INPUT: 'coleccion' por parámetro.
	 * OUTPUT: por consola listado de strings ordenados por id con las id y título de
	 * todos los libros contenidos en la Coleccion.*/
	public ArrayList<String> Catalogo() {

		//Conexión a BDD:
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		quitarBlancos(coleccion);
		
		String campoMostrar1 = "Id"; 					// Campos a mostrar
		String campoMostrar2 = "Titulo";
		String id;
		ArrayList<String> consulta = new ArrayList<>(); // Array con el resultado para almacenar
		ArrayList<String> mostrar = new ArrayList<>(); 	// Array con el resultado para mostrar ordenado
		
		MongoCursor<Document> cursor = coleccion.find().iterator(); // Puntero

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			if (obj.getString(campoMostrar1).length() == 1) id = "0" + obj.getString(campoMostrar1);
			else id = obj.getString(campoMostrar1);
			
			consulta.add(id + " - " + obj.getString(campoMostrar2));
		}
		cursor.close();
		
		//Presentacion ordenada:
		Collections.sort(consulta);
		int cont = 0;
		for (String obj : consulta) {
			if (cont == 0) mostrar.add("CATALOGO:\n ---------");
			mostrar.add(obj);
			cont++;
		}
		mostrar.add(" \n Total de libros: " + cont);
		mongoClient.close();
		return mostrar;
	}

	
	/*FUNCTION: quitarBlancos()
	 * ACTION: recorre todos los 'fileds' y sustituye los campos en blanco por 'N.D.' 
	 * INPUT:	'coleccion' por parámetro.
	 * OUTPUT:	Nada. Sustituye campos en blaco dentro de los 'fields' por 'N.D'*/
	public void quitarBlancos(MongoCollection<Document> coleccion) {

		String[] fields = {"Titulo","Autor","Anyo_Nacimiento","Anyo_Publicacion","Editorial","Paginas"};
		for(int i = 0; i<fields.length; i++) {
			Bson query = eq(fields[i], "");
			Bson query2 = eq(fields[i], " ");
			coleccion.updateMany(query, new Document("$set", new Document(fields[i], "N.D.")));
			coleccion.updateMany(query2, new Document("$set", new Document(fields[i], "N.D.")));
		}
	}
	
	
	
	
}
