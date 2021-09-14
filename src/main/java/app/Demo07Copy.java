package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo07Copy {
	
	public static void main(String[] args) {
		
		//similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		// Similar a Crear el Objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso -> Consultar Datos de Usuario
		
		//em.createQuery("Sentencia de consulta JPQL", claseDeResultado)
		
		System.out.println(" --- Listado de los Usuarios ---");
		List<Usuario> lstUsuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		System.out.println("antidad de Usuarios : "+lstUsuarios.size());
		
		for (Usuario u : lstUsuarios) {
			
			System.out.println(u);

		}
		System.out.println("---------------------------------");
		
		
		
		TypedQuery<Usuario> t =	em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		
		List<Usuario> lstUsuarios2= t.getResultList();
		
		//System.out.println("Numero de Usuarios: "+lstUsuarios.size());
		
		/*for (Usuario usuario : lstUsuarios) {
			//System.out.println("id: " + usuario.getCodigo()+ " Nombre: "+usuario.getNombre()+ " Apellidos: "+ usuario.getApellido());
			//System.out.println("Id: " + usuario.getCodigo());
			//System.out.println( usuario.toString());
			System.out.println("Siguiente Empleado" + usuario);
			
		}*/
		
		
		
		em.close();
		
		
	}

}