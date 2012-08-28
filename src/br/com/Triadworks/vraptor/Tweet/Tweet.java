package br.com.Triadworks.vraptor.Tweet;

import java.util.Date;

import br.com.Triadworks.vraptor.Usuario.Usuario;

public class Tweet {

	private int id;
	private String corpoMensagem;
	private Usuario usuarioDono;
	private Date dataEnvio;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCorpoMensagem() {
		return corpoMensagem;
	}
	public void setCorpoMensagem(String corpoMensagem) {
		this.corpoMensagem = corpoMensagem;
	}
	public Usuario getUsuarioDono() {
		return usuarioDono;
	}
	public void setUsuarioDono(Usuario usuarioDono) {
		this.usuarioDono = usuarioDono;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
}
