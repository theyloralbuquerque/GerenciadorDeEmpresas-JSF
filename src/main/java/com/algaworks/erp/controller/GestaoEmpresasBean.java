package com.algaworks.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.TipoEmpresa;

@Named // @Named deixa a classe acessível a qualquer uma das páginas xhtml do projeto.
@ViewScoped // Define o escopo de um bean gerenciado para ser associado ao ciclo de vida da visualização (view) de uma página JSF.
public class GestaoEmpresasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	
	public void salvar() {
		System.out.println("Razão social: " + empresa.getRazaoSocial()
				+ " - Nome fantasia : " + empresa.getNomeFantasia()
				+ " - Tipo: " + empresa.getTipo());
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
}