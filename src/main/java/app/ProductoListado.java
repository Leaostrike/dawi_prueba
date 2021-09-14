package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Producto;
import model.Usuario;

public class ProductoListado {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		System.out.println(" --- Lista de Productos ---");
		
		String sql = "SELECT p FROM Producto p";
		TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
		List<Producto> lstProductos = query.getResultList();
		
		System.out.println("Cantidad de Items : "+ lstProductos.size());
		
		for (Producto p : lstProductos) {
			System.out.println(p);
		}
		System.out.println("---------------------------------");
		
	}

}