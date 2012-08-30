package br.com.Triadworks.vraptor.Tweet;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class TweetsController {
	private TweetsDao dao;
	private Result result;
	
	private Validator validator;
	
	public TweetsController(TweetsDao dao, Result result, Validator validator){
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}
	
	@Get @Path("/tweets")
	public List<Tweet> listar(){
		return dao.listarTweets();
	}
	
	@Post @Path("/tweets")
	public void adicionar(final Tweet tweet){
		validator.checking(new Validations() { {
            that(tweet.getCorpoMensagem() != null, "Tweet.vazio", "tweet.vazio");
        } });
		validator.onErrorForwardTo(TweetsController.class).listar();
		dao.adicionar(tweet);
		result.redirectTo(TweetsController.class).listar();
	}
	
	
	@Path ("/retweet")
	public void retweetar(String mensagem, int usuarioId, int donoId){
		dao.retweetar(mensagem, usuarioId, donoId);
		result.redirectTo(TweetsController.class).listar();
	}
	@Path ("/responder")
	public void responder(int usuarioId, int tweetId, String resposta){
		dao.responder(resposta, tweetId, usuarioId);
		result.redirectTo(TweetsController.class).listar();
	}

}
