package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

public class ProductoActualizar {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> Actualizar Producto
		Producto p = new Producto();
		
		p.setIdprod("P0035");
		p.setDescripcion("Apronax Forte 500");
		p.setStock(50);
		p.setPrecio(17.50);
		p.setIdcategoria(2);
		p.setEstado(1);
		
		em.getTransaction().begin();
		
		em.merge(p); //<---- Actualizar Producto
		
		em.getTransaction().commit();
		System.out.println("Producto Actualizado");
		
		em.close();
		
		
	}

}