package br.com.Triadworks.dao;

import br.com.Triadworks.model.Usuario;

public interface UsuarioDao {

	public Usuario getUsuario(String login, String senha);
	public Usuario getUsuario(int id);
}
