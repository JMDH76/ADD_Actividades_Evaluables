package es.florida.AE04;

public class Libro {

	String titulo, autor, editorial ,anyo_nacimiento, anyo_publicacion, num_paginas;
	
	Libro(){}
	
	Libro (String titulo, String autor, String anyo_nacimiento, String anyo_publicacion, String editorial, String num_paginas  ){
		
		this.titulo= titulo;
		this.autor = autor;
		this.anyo_nacimiento = anyo_nacimiento;
		this.anyo_publicacion = anyo_publicacion;
		this.editorial= editorial;
		this.num_paginas = num_paginas;		
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo (String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getAnyo_nacimineto () {
		return anyo_nacimiento;
	}
		
	public void setAnyo_nacimiento(String anyo_nacimineto) {
		this.anyo_nacimiento = anyo_nacimineto;
	}
	
	public String getAnyo_publicacion () {
		return anyo_publicacion;
	}
	
	public void setAnyo_publicacion (String anyo_publicacion) {
		this.anyo_publicacion = anyo_publicacion;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
			this.editorial = editorial;
	}
	
	public String getNum_paginas() {
		return num_paginas;
	}
	
	public void setNum_paginas (String num_paginas) {
		this.num_paginas = num_paginas;
	}
	
	public String getLibroText () {
		String libro = ("Titulo: " + titulo + "\n" + "Autor: " + autor + "\n" + "Año nacimineto: " + anyo_nacimiento + "\n" +"Año publicacion: " 
	+ anyo_publicacion + "\n" +"Editorial: " + editorial + "\n" + "Páginas: " + num_paginas + "\n"); 
		
		return libro;
	}
}
	

