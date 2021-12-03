package es.florida.AE05_GUI;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import es.florida.AE05.Libro;

public class Modelo {

	public ArrayList<String> consultaCatalogo() {

		ArrayList<String> catalogo = new ArrayList<String>();

		Configuration configuration = new Configuration().configure("hibernate.cfg2.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Libro> listaLibros = new ArrayList();
		listaLibros = session.createQuery("FROM Libro").list();

		String lib;
		for (Object obj : listaLibros) {
			Libro libro = (Libro) obj;
			lib = libro.getId() + ". " + libro.getTitulo();
			catalogo.add(lib);
		}
		session.getTransaction().commit();
		session.close();
		return catalogo;
	}
	
	
	public String[] consultaLibro(int id_consulta) {

		String[] detalleLibro = new String[7];

		Configuration configuration = new Configuration().configure("hibernate.cfg2.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Libro libro = (Libro) session.get(Libro.class, id_consulta);
		if (libro == null)
			detalleLibro[0] = "\n ID inexistente";
		else {
			detalleLibro[0] = "Ok";
			detalleLibro[1] = libro.getTitulo().toString();
			detalleLibro[2] = libro.getAutor().toString();
			detalleLibro[3] = libro.getAnyo_nacimiento().toString();
			detalleLibro[4] = libro.getAnyo_publicacion().toString();
			detalleLibro[5] = libro.getEditorial().toString();
			detalleLibro[6] = libro.getNum_paginas().toString();
		}
		session.getTransaction().commit();
		session.close();

		return detalleLibro;
	}
	
	
	public int nuevoLibro(Libro libro) {

		Configuration configuration = new Configuration().configure("hibernate.cfg2.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Serializable id = session.save(libro);

		session.getTransaction().commit();
		session.close();

		return (int) id;
	}
	
	public void borrarLibro(int id) {

		Configuration configuration = new Configuration().configure("hibernate.cfg2.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Libro lib = new Libro();
		lib.setId(id);
		session.delete(lib);

		session.getTransaction().commit();
		session.close();

	}
	
	
	public void editarLibro(Libro libro) {

		Configuration configuration = new Configuration().configure("hibernate.cfg2.xml");
		configuration.addClass(Libro.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String titulo = libro.getTitulo();
		String autor = libro.getAutor();
		String anyonacimiento = libro.getAnyo_nacimiento();
		String anyopublicacion = libro.getAnyo_publicacion();
		String editorial = libro.getEditorial();
		String paginas = libro.getNum_paginas();

		Libro lib = (Libro) session.load(Libro.class, libro.getId());

		try {
			lib.setTitulo(titulo);
			lib.setAutor(autor);
			lib.setAnyo_nacimiento(anyonacimiento);
			lib.setAnyo_publicacion(anyopublicacion);
			lib.setEditorial(editorial);
			lib.setNum_paginas(paginas);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error. Los cambios no se han podidoo actualizar.\nRevise los datos");
		}

		session.update(lib);

		session.getTransaction().commit();
		session.close();
	}
}




















