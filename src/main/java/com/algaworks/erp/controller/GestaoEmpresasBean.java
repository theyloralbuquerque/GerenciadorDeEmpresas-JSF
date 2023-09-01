package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.repository.RamoAtividades;
import com.algaworks.erp.service.CadastroEmpresaService;
import com.algaworks.erp.util.FacesMessages;

@Named // @Named deixa a classe acessível a qualquer uma das páginas xhtml do projeto.
@ViewScoped // Define o escopo de um bean gerenciado para ser associado ao ciclo de vida da visualização (view) de uma página JSF.
public class GestaoEmpresasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService; // Injeção do Facade/Service CadastroEmpresaService.
	
	private List<Empresa> listaEmpresas;
	
	private String termoPesquisa; // Propriedade que será vinculada com o primeiro inputText da toolbar (input de pesquisar).
	
	private Converter ramoAtividadeConverter;
	
	private Empresa empresa; // Propriedade quer será usada para vincular o formulário ao bean.
	
	public void prepararNovaEmpresa() { // Método invocado a partir do momento que o botão "Nova" for clicado.
		empresa = new Empresa();
	}
	
	public void salvar() { // Método que salva uma empresa no BD conforme os dados recebidos pela page.
		cadastroEmpresaService.salvar(empresa);
		
		if (jaHouvePesquisa()) { 
			pesquisar(); // Se já tiver acontecido a pesquisa desse termo, chama o método pesquisar(). 
		} else {
			todasEmpresas();
		}
		messages.info("Empresa salva com sucesso!");
		
		RequestContext.getCurrentInstance().update(Arrays.asList(
				"frm:empresasDataTable", "frm:messages"));
	}
	
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
	
	// Método que realiza o autoComplete e retorna uma lista com os elementos do banco.
	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
		
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
		
		return listaRamoAtividades;
	}
	
	private boolean jaHouvePesquisa() { // Método que verifica se já houve uma pesquisa desse termo no sistema.
		return termoPesquisa != null && !"".equals(termoPesquisa);
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
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

}