package br.com.Triadworks.Controller;

import org.springframework.beans.factory.annotation.Qualifier;

import br.com.Triadworks.dao.UsuarioDao;
import br.com.Triadworks.model.Usuario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class UsuarioController {

	private UsuarioDao usuarioDao;
	private Result result;
	private Validator validator;

	public UsuarioController(Result result, Validator validator,
			@Qualifier("usuarioDaoHibernate") UsuarioDao usuarioDao) {
		this.result = result;
		this.validator = validator;
		this.usuarioDao = usuarioDao;
	}

	@Get
	@Path("/")
	public void login() {
	}

	@Post
	@Path("/usuario")
	public void confirmar(String login, String senha) {
		final Usuario usuario = usuarioDao.getUsuario(login, senha);
		validator.checking(new Validations() {
			{
				that(usuario != null, "usuario.null","Usuario nulo");
			}
		});
		validator.onErrorUsePageOf(UsuarioController.class).login();
		result.include("usuario", usuario);
		result.forwardTo(TweetController.class).listar();
	}
}
