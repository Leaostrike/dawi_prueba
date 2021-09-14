package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo09 {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> Validad usando Usuario y Clave : Login con USP
		//String sql = "{call usp_validaAcceso (?,?)}";
		String sql = "{call usp_validaAcceso (:xusr,:xcla)}";
		
		Query query = em.createNativeQuery(sql, Usuario.class);
		//query.setParameter(1, "U001@gmail.com");
		//query.setParameter(2, "10001");
		
		query.setParameter("xusr", "U001@gmail.com");
		query.setParameter("xcla", "10001");
		
		//Ejecuta la consulta y obtiene el resultado
		Usuario u = (Usuario) query.getSingleResult();
		
		em.close();
		
		if(u == null) {
			System.out.println("Usuario No existe, usuario o clave incorrecto");
		}else {
			System.out.println("Bienvenido: " + u.getNombre());
		}
			
		
		
	}

}