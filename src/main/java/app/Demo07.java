package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo07 {
	
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
		
		
		System.out.println(" --- Listado de los Usuarios x Tipo 2 ---");
		
		//Define  la cadena
		String sql = "SELECT u FROM Usuario u where u.tipo = :xtipo";
		//Prepara la sentencia
		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);
		//Setea los parametros
		query.setParameter("xtipo", 2);
		//Ejecuta la consulta y convierte el resultado en un List
		List<Usuario> lstUsuarios2 = query.getResultList();
		//Muestra el resultado
		System.out.println("antidad de Usuarios : "+lstUsuarios2.size());
		
		for (Usuario u : lstUsuarios2) {
			System.out.println(u);
		}
		System.out.println("---------------------------------");
		
		
		
		
		
		
		
		em.close();
		
		
	}

}