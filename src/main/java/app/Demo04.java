package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> Consultar los datos de un usaario
		
		Usuario u = em.find(Usuario.class, 123);
		
		em.close();
		
		if(u == null) {
			System.out.println("Usuario No existe");
		}else {
			System.out.println("Usuario Existe!!!\n" + u);
		}
			
		
		
	}

}