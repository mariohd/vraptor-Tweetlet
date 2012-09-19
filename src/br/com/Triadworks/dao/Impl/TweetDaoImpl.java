package br.com.Triadworks.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.Triadworks.dao.TweetDao;
import br.com.Triadworks.dao.UsuarioDao;
import br.com.Triadworks.model.Tweet;
import br.com.Triadworks.model.Usuario;

@Component("tweetDaoHibernate")
public class TweetDaoImpl implements TweetDao {

	private SessionFactory sessionFactory;
	private UsuarioDao usuarioDao;

	@Autowired
	public TweetDaoImpl(SessionFactory sessionFactory,
			@Qualifier("usuarioDaoHibernate") UsuarioDao usuarioDao) {
		this.sessionFactory = sessionFactory;
		this.usuarioDao = usuarioDao;
	}

	@SuppressWarnings("unchecked")
	public List<Tweet> getTweets() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Tweet> l = session.createQuery("from Tweet order by dataEnvio desc").list();
		for (Tweet t : l){
			t.setUsuarioDono(usuarioDao.getUsuario(t.getUsuarioDono().getId()));
		}
		return l;
	}

	public void insertTweet(Tweet tweet) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(tweet);
	}

	public void retweet(String mensagem, int usuarioId, int donoId) {
		Tweet rt = new Tweet();
		rt.setUsuarioDono(usuarioDao.getUsuario(usuarioId));
		Usuario dono = usuarioDao.getUsuario(donoId);
		rt.setCorpoMensagem("RT @" + dono.getLogin() + "/ " + mensagem);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(rt);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void responder(String resposta, int tweetId, int usuarioId) {

		Tweet resp = new Tweet();
		resp.setCorpoMensagem(resposta);
		resp.setUsuarioDono(usuarioDao.getUsuario(usuarioId));
		resp.setRespondeuTweet(tweetId);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(resp);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}

}
