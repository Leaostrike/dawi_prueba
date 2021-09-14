package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> actualizar un Usuario
		Usuario u = new Usuario();
		u.setCodigo(123);
		u.setNombre("Javier");
		u.setApellido("Alvarado");
		u.setUsuario("@javier");
		u.setClave("289600");
		u.setFnacim("2021/08/24");
		u.setTipo(2);
		u.setEstado(1);
		
		em.getTransaction().begin();
		
		em.merge(u); //<---- Actualizar si exixte el codigo, sino registra un nuevo dato
		
		em.getTransaction().commit();
		
		System.out.println("Usuario Actualizado");
		
		em.close();
		
		
	}

}