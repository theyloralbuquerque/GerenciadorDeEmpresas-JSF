package com.algaworks.erp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;

public class CamadaPersistencia {

	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GerenciadorDeEmpresas");

		EntityManager em = emf.createEntityManager();

		//Inicia de uma transação no BD
		em.getTransaction().begin();

		//Declaração os repositórios
		RamoAtividades ramoAtividades = new RamoAtividades(em);
		Empresas empresas = new Empresas(em);

		//Buscando as informações do banco
		//Passando uma String vazia no método pesquisar("") ele vai retornar todos os registros da tabela RamoAtividade
		List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.pesquisar(""); 
		
		//Passando uma String vazia no método pesquisar("") ele vai retornar todos os registros da tabela Empresa
		List<Empresa> listaDeEmpresas = empresas.pesquisar("");
		
		//Exibe quantas empresas eu tenho
		System.out.println(listaDeEmpresas);

		//Criando uma empresa
		Empresa empresa = new Empresa();		
		empresa.setNomeFantasia("João da Silva");
		empresa.setCnpj("41.952.519/0001-57");
		empresa.setRazaoSocial("João da Silva 41952519000157");
		empresa.setTipo(TipoEmpresa.MEI);
		empresa.setDataFundacao(new Date());
		
		//Insere o primeiro registro da lista listaDeRamoAtividades na empresa 
		empresa.setRamoAtividade(listaDeRamoAtividades.get(0));

		//Salvando a empresa
		empresas.guardar(empresa);

		//Encerra uma transação no BD
		em.getTransaction().commit();

		//Verificando se a inserção funcionou
		List<Empresa> listaDeEmpresas2 = empresas.pesquisar("");
		System.out.println(listaDeEmpresas2);


		em.close();
		emf.close();
	}

}