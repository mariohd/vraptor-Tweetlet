package br.com.Triadworks.model;

import java.util.Date;

public class Tweet {

	private int id;

	private String corpoMensagem;

	private Usuario usuarioDono;

	private Date dataEnvio;

	private Integer respondeuTweet;

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

	public Integer getRespondeuTweet() {
		return respondeuTweet;
	}

	public void setRespondeuTweet(Integer respondeuTweet) {
		this.respondeuTweet = respondeuTweet;
	}
}
