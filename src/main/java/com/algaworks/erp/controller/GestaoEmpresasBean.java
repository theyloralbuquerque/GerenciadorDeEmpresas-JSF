package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.util.FacesMessages;

@Named // @Named deixa a classe acessível a qualquer uma das páginas xhtml do projeto.
@ViewScoped // Define o escopo de um bean gerenciado para ser associado ao ciclo de vida da visualização (view) de uma página JSF.
public class GestaoEmpresasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private FacesMessages messages;
	
	private List<Empresa> listaEmpresas;
	
	private String termoPesquisa; // Propriedade que será vinculada com o primeiro inputText da toolbar (input de pesquisar).
	
	public void pesquisar() { // Método que será invocado pelo botão 'pesquisar'.
		listaEmpresas = empresas.pesquisar(termoPesquisa);
		
		if (listaEmpresas.isEmpty()) {
			// Chama o método info na classe FacesMessages.
			messages.info("Sua consulta não retornou registros.");
		}
	}
	
	/*  Método de que irá chamar o método todas() na Imp do repository Empresas,
	 *  como retorno terá uma lista de empresa armazenada na listaEmmpresas.
	 */
	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}
	
	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
}