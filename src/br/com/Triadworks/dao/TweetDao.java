package br.com.Triadworks.dao;

import java.util.List;

import br.com.Triadworks.model.Tweet;

public interface TweetDao {

	public List<Tweet> getTweets();
	public void insertTweet(Tweet tweet);
	public void retweet(String mensagem, int usuarioId, int donoId);
	public void responder(String resposta, int tweetId, int usuarioId);
}
