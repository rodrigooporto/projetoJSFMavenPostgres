package rodrigooporto.modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import rodrigooporto.util.JPAUtil;
import rodrigooporto.util.UtilScanner;

public class PesistidorAutomovelTeste {
	public Automovel automovel = new Automovel();
	
	public static void main(String[] agrs) {
		PesistidorAutomovelTeste automovelTeste = new PesistidorAutomovelTeste();
		int opcao;
		Long idAutomovel;
		
		do {
			UtilScanner.saidaTexto("Digite a opção desejada. \n"
					+ "0 - Sair\n"
					+ "1 - Cadastrar\n"
					+ "2 - Listar\n"
					+ "3 - Alterar\n"
					+ "4 - Excluir\n");
			opcao = UtilScanner.entrada.nextInt();
			if(opcao == 1) {
				automovelTeste.inserir();
				automovelTeste.listaAutomovel();
				UtilScanner.saidaTexto("Veículo Cadastrado com sucesso.");
			} else if(opcao == 2 ) {
				automovelTeste.listaAutomovel();
			} else if(opcao == 4) {
				try {
					UtilScanner.saidaTexto("Digite o ID do veículo a ser excluido.");
					idAutomovel = UtilScanner.entrada.nextLong();
					automovelTeste.remover(idAutomovel);
					automovelTeste.listaAutomovel();
					UtilScanner.saidaTexto("Veículo Excluido com sucesso.");
				} catch (EntityNotFoundException e) {
					UtilScanner.saidaTexto("O ID do veículo não existe");
				}
			}
			
		} while(opcao !=0);
	}
	
	public void inserir() {
		EntityManager em = JPAUtil.getEntityManager();	
		automovel = new Automovel();
		if(UtilScanner.entrada.hasNextLine()) {
			UtilScanner.entrada.nextLine();
		}
		
		UtilScanner.saidaTexto("Digite o Modelo do veículo");
		String modelo = UtilScanner.entrada.nextLine();
		automovel.setModelo(modelo);		

		UtilScanner.saidaTexto("Digite a Marca do veículo");
		String marca = UtilScanner.entrada.nextLine();
		automovel.setMarca(marca);
		
		
		UtilScanner.saidaTexto("Digite o Ano de Fabricação do veículo");
		int anoFabricacao = UtilScanner.entrada.nextInt();
		automovel.setAnoFabricacao(anoFabricacao);
		
		
		UtilScanner.saidaTexto("Digite o Ano do Modelo do veículo");
		int anoModelo = UtilScanner.entrada.nextInt();
		automovel.setAnoModelo(anoModelo);
		
		if(UtilScanner.entrada.hasNextLine()) {
			UtilScanner.entrada.nextLine();
		}
		UtilScanner.saidaTexto("Digite mais Informações do veículo");
		String observacoes = UtilScanner.entrada.nextLine();		
		automovel.setObservacoes(observacoes);

		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(automovel);
		tx.commit();
		em.close();
	}
	
	public void listaAutomovel() {
		EntityManager em = JPAUtil.getEntityManager();
		Query q = em.createQuery("select a from Automovel a", Automovel.class);
		List<Automovel> autos = q.getResultList();
		for(Automovel a : autos) {
			System.out.println(a.getId() +" - "+ a.getMarca()+" - "+a.getModelo());
		}
	}
	
	private void remover(Long automovel) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();		
		Automovel autoRemover = em.getReference(Automovel.class, automovel);
		tx.begin();
		em.remove(autoRemover);
		tx.commit();
	}
	
}