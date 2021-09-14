package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
import model.Usuario;

public class ProductoEliminar {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> Consultar Datos de un producto
		Producto p = em.find(Producto.class, "P0035");
		
		if(p == null) {
			System.out.println("Producto no existe");
		}else {
			System.out.println("Producto almacenado!!!\n" + p);
			
			em.getTransaction().begin();
			
			em.remove(p); //<---- Eliminar
			
			em.getTransaction().commit();
			System.out.println("Producto Eliminado");
			
			
		}
		
		
		
		
		
	}

}