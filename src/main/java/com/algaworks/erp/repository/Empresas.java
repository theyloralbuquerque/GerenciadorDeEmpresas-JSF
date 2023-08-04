package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.erp.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager; // O manager vai gerenciar as entidades em um contexto de persistência. 

	public Empresas() {
	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}

	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id); // find() busca uma entidade específica no banco de dados com base em sua chave primária.
	}

	public List<Empresa> pesquisar(String nome) {		
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		
		TypedQuery<Empresa> query = manager
				.createQuery(jpql, Empresa.class); // Cria uma query com a String contida em jpql e armazena o retorno em query.
		
		// setParameter() define os parâmetros de uma consulta no Java. (O primeiro parâmetro é a coluna e o segundo parâmetro é o valor)
		query.setParameter("nomeFantasia", nome + "%"); 
		
		// getResultList() retorna os resultados da consulta como uma lista de objetos.
		return query.getResultList();
	}
	
	// Método que retornará do BD todas as empresas em uma lista.
	public List<Empresa> todas() {		
		return manager.createQuery("from Empresa", Empresa.class).getResultList();
	}

	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa); // merge() vai atualizar (ou inserir) uma entidade (empresa) existente (ou não) no BD.
	}

	public void remover(Empresa empresa) {
		empresa = porId(empresa.getId()); // O retorno será uma empresa (que foi buscada pelo id).
		manager.remove(empresa); // remove() vai remover a empresa armazenada na variável empresa.
	}
}