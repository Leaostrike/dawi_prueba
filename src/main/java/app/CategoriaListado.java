package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Categoria;
import model.Producto;
import model.Usuario;

public class CategoriaListado {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		System.out.println(" --- Lista de Categorias ---");
		
		String sql = "SELECT c FROM Categoria c";
		TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
		List<Categoria> lstCategorias = query.getResultList();
		
		System.out.println("Numero de Categorias : "+ lstCategorias.size());
		
		for (Categoria c : lstCategorias) {
			System.out.println(c);
		}
		System.out.println("-----------------------------------------------");
		
	}

}