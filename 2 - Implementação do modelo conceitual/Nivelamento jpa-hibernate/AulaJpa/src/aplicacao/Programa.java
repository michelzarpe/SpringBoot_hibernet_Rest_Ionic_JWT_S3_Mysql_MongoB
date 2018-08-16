package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.model.Pessoa;

public class Programa {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		/*
		Pessoa p1 = new Pessoa("Michel Zarpelon", "michelzarpe@gmail.com");
		Pessoa p2 = new Pessoa("Alessandra F. Miqueloto", "alessandra@gmail.com");
		Pessoa p3 = new Pessoa("Giovana B. Tiepo", "giovana@gmail.com");
		
		
		
		//quando é diferente de leitura, tem que fazer com transasao
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		*/
		
		/*Buscar*/
		Pessoa p = em.find(Pessoa.class, 1);
		System.out.println(p);
		
		/*Remover*/
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		em.close();
		emf.close();	
		

	}

}
