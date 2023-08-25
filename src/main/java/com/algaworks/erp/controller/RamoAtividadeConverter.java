package com.algaworks.erp.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.algaworks.erp.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter {
	
	private List<RamoAtividade> listaRamoAtividades;

	public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividades) {
		this.listaRamoAtividades = listaRamoAtividades;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) { // Recebe uma String do autoComplete da página.
		if (value == null) {
			return null;
		}
		
		Long id = Long.valueOf(value); // Converte o value em Long e armazena na variável id.
		
		for (RamoAtividade ramoAtividade : listaRamoAtividades) { // for each da listaRamoAtividades.
			if (id.equals(ramoAtividade.getId())) { // Se o id acima do laço (recebido da página como String e convertido em Long) for igual ao id da repetição.
				return ramoAtividade;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) { // Recebe um Object da página.
		if (value == null) {
			return null;
		}
		
		RamoAtividade ramoAtividade = (RamoAtividade) value; // Caso não entre no if, o value será convertido em RamoAtividade e armazenado na variável ramoAtividade.
		
		return ramoAtividade.getId().toString(); // O retorno será o id do objeto ramoAtividade convertido em String.
	}
}