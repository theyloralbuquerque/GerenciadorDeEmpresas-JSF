package com.algaworks.erp.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessages implements Serializable{

	private static final long serialVersionUID = 1L;

	private void add(String msg, FacesMessage.Severity severity) {
		// Cria uma nova instância de FacesMessage, que é usada para exibir mensagens na interface do usuário.
		FacesMessage facesMessage = new FacesMessage(msg);
		// Define a gravidade (severidade) da mensagem.
		facesMessage.setSeverity(severity);
		
		// Pega o contexto atual do ciclo de vida do JSF e o addMessage() adiciona a mensagem ao contexto JSF para ser exibida ao usuário.
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void info(String msg) {
		// Chama o método add() passando como parâmetro a String recebida do bean e a gravidade do message.
		add(msg, FacesMessage.SEVERITY_INFO);
	}
}
