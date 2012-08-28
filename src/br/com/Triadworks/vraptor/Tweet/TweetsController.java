package br.com.Triadworks.vraptor.Tweet;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class TweetsController {
	private TweetsDao dao;
	private Result result;
	
	public TweetsController(TweetsDao dao, Result result){
		this.dao = dao;
		this.result = result;
	}
	
	@Get @Path("/tweets")
	public List<Tweet> listar(){
		return dao.listarTweets();
	}
	
	@Post @Path("/tweets")
	public void adicionar(Tweet tweet){
		dao.adicionar(tweet);
		result.redirectTo(TweetsController.class).listar();
	}

}
