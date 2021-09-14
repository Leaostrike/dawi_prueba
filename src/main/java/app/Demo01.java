package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> registrar un nuevo Usuario
		Usuario u = new Usuario();
		u.setCodigo(123);
		u.setNombre("Luis");
		u.setApellido("Alvarado");
		u.setUsuario("@lucas");
		u.setClave("289600");
		u.setFnacim("2021/08/24");
		u.setTipo(1);
		u.setEstado(1);
		
		em.getTransaction().begin();
		
		em.persist(u); //<---- Registrar
		
		em.getTransaction().commit();
		System.out.println("Usuario Creado satisfactoriamente");
		
		em.close();
		
		
	}

}