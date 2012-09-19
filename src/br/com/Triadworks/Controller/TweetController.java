package br.com.Triadworks.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import br.com.Triadworks.dao.TweetDao;
import br.com.Triadworks.model.Tweet;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class TweetController {

	Result result;
	Validator validator;
	TweetDao tweetDao;

	public TweetController(Validator validator, Result result,
			@Qualifier("tweetDaoHibernate") TweetDao tweetDao) {
		this.validator = validator;
		this.result = result;
		this.tweetDao = tweetDao;
	}

	@Get
	@Path("/tweets")
	public List<Tweet> listar() {
		return tweetDao.getTweets();
	}

	@Post
	@Path("/tweets")
	public void adicionar(final Tweet tweet) {
		validator.checking(new Validations() {
			{
				that(tweet.getCorpoMensagem() != null, "Tweet.vazio",
						"tweet.vazio");
			}
		});
		validator.onErrorForwardTo(TweetController.class).listar();
		tweetDao.insertTweet(tweet);
		result.redirectTo(TweetController.class).listar();
	}

	@Path("/retweet")
	public void retweetar(String mensagem, int usuarioId, int donoId) {
		tweetDao.retweet(mensagem, usuarioId, donoId);
		result.redirectTo(TweetController.class).listar();
	}

	@Path("/responder")
	public void responder(int usuarioId, int tweetId, String resposta) {
		tweetDao.responder(resposta, tweetId, usuarioId);
		result.redirectTo(TweetController.class).listar();
	}

}
