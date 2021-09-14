package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

public class ProductoRegistrar {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> registrar un nuevo Producto
		Producto p = new Producto();
		
		p.setIdprod("P0035");
		p.setDescripcion("Apronax Forte");
		p.setStock(35);
		p.setPrecio(12.50);
		p.setIdcategoria(4);
		p.setEstado(1);
		
		em.getTransaction().begin();
		
		em.persist(p); //<---- Registrar Producto
		
		em.getTransaction().commit();
		System.out.println("Producto Agregado Satisfactoriamente");
		
		em.close();
		
		
	}

}