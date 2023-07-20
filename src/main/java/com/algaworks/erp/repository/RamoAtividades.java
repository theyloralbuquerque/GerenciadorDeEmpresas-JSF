package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algaworks.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager; //O manager vai gerenciar as entidades em um contexto de persistência.

	public RamoAtividades() {
	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}

	public List<RamoAtividade> pesquisar(String descricao) {
		
		//CriteriaBuilder permite construir consultas SQL de forma mais flexível em tempo de execução usando métodos Java.
		//getCriteriaBuilder() é usado para obter uma instância do CriteriaBuilder.
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		//CriteriaQuery<RamoAtividade>: Isso define o tipo de retorno da consulta.
		/*
		 * createQuery(RamoAtividade.class): Este é o método createQuery do CriteriaBuilder, 
		 * que é usado para criar uma nova instância de CriteriaQuery. 
		 * Aqui, estamos criando um objeto CriteriaQuery<RamoAtividade> que será usado para 
		 * construir a consulta para a entidade RamoAtividade.
		 */
		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
		
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		
		//Faça um serlect do que está armazenado no meu root.
		criteriaQuery.select(root);
		
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));

		//Armazena na variável query a query criada que está armazenada em criteriaQuery.
		TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);

		//Retorna em Lista o que estiver armazenado em	query (que será os valores armazenados na entidade RamoAtividade).
		return query.getResultList();
	}

}
