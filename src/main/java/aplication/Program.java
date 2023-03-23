package aplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Pessoa;

public class Program {
	
	public static void main(String[] args) {
		
		Pessoa p1 = new Pessoa(null, "Carlos da Silva", "silva@gmail.com");
		Pessoa p2 = new Pessoa(null, "Carlos da Cunha", "cunha@gmail.com");
		Pessoa p3 = new Pessoa(null, "Carlos da Pedra", "pedra@gmail.com");
		
		//para instanciar o EntityManagerFactory eu preciso passar o nome do persistence-unit que está no persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		
		//com o Factory instanciado, instaciamos o EntityManager usando o factory
		EntityManager em = emf.createEntityManager();
		
		//para fazer operações q n sejam leitura de banco de dados, ele precisa de uma Transaction
		em.getTransaction().begin();
		em.persist(p1); //pega o objeto e salva no banco de dados
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		System.out.println("Pronto - Pessoas adicionados no banco de dados");
		
		//para buscar o objeto no banco de dados pelo ID:
		Pessoa p = em.find(Pessoa.class, 2); //em.find busca um objeto pelo ID, você vai passar a classe e o ID
		System.out.println(p);
		System.out.println("Pronto - Buscando no banco de dados pelo ID");
		
		//para remover do bando de dados
		//para remover, o objeto tem que estar monitorado, ou seja, tem que ser um objeto que você adicionou
		//ou que você buscou no banco de dados antes de fechar o EntityManager, ou seja, você precisa buscar
		//no banco de dados primeiro para conseguir remover ou ter adicionado anteriormente no codigo para remove-lo
		Pessoa pessoa = em.find(Pessoa.class, 1);
		em.getTransaction().begin();
		em.remove(pessoa);
		em.getTransaction().commit();
		System.out.println("Pronto - Removendo do banco de dados");

		em.close();
		emf.close();
	}

}
