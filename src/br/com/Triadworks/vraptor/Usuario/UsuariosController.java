package br.com.Triadworks.vraptor.Usuario;

import br.com.Triadworks.vraptor.Tweet.TweetsController;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource	
public class UsuariosController {
	
	private UsuarioDao dao;
	Result result;
	Validator validator;
	
	public UsuariosController(UsuarioDao dao, Result result, Validator validator){
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Post @Path("/usuario")
	public void confirmar(String login, String senha){
		final Usuario usuario = dao.getUsuario(login, senha);	
		validator.checking(new Validations() { {
            that(usuario != null, "usuario.null", "Usuario nao existe.");
        } });
		validator.onErrorUsePageOf(UsuariosController.class).login();
		result.include(usuario);
		result.forwardTo(TweetsController.class).listar();
	}
	
	@Get @Path("/usuario")
	public void login(){
	}
}