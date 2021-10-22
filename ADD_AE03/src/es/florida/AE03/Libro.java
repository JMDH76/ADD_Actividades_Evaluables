package es.florida.AE03;

public class Libro {

	String  id,titulo, autor, anyo, editorial,paginas;

	Libro() {

	}

	Libro(String id, String titulo, String autor, String anyo, String editorial, String paginas) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.editorial = editorial;
		this.paginas = paginas;
	}

	//GETTERS y SETTERS de los atributos
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		this.anyo = anyo;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	/*Método getStringInfoReducida()
	 * ACTION:	obtiene y formatea un String con la presentación de los atributos
	 * id y Título de un objeto Libro
	 * INPUT:	valores atributos "id" y "titulo"
	 * OUTPUT: 	String formateado con id y títulño del objeto. */
	public String getStringInfoReducida() {
		return " (" + id + ") " + titulo ;
	}

	/*Método getStringInfoCompleta()
	 * ACTION:	obtiene y formatea un String con la presentación de todos los 
	 * atributos de un objeto Libro.
	 * INPUT:	valores atributos de los atributos del objeto
	 * OUTPUT: 	String formateado presentando los atributos del objeto. */
	public String getStringInfoCompleta() {
		return "\nId:		" + id + "\nTitulo:		" + titulo + "\nAutor:		" + autor + "\nAño:		" + anyo + "\nEditorial:	"
				+ editorial + "\nNum. Páginas:	" + paginas + "\n";
	}
	
}
