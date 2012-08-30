package br.com.Triadworks.vraptor.Tweet;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Triadworks.vraptor.Hibernate.HibernateUtil;
import br.com.Triadworks.vraptor.Usuario.Usuario;
import br.com.Triadworks.vraptor.Usuario.UsuarioDao;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class TweetsDao {
	
	UsuarioDao usuarioDao =  new UsuarioDao();

	public List<Tweet> listarTweets() {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Query query = session.createQuery("from Tweet order by dataEnvio desc");
			@SuppressWarnings("unchecked")
			List<Tweet> tweets = query.list();
			for (Tweet t : tweets){
				t.setUsuarioDono(usuarioDao.getUsuario(t.getUsuarioDono().getId()));
			}
			trns.commit();
			return tweets;
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return null;
	}

	public void adicionar(Tweet tweet) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.save(tweet);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void retweetar(String mensagem, int usuarioId, int donoId) {
		Tweet rt = new Tweet();
		rt.setUsuarioDono(usuarioDao.getUsuario(usuarioId));
		Usuario dono = usuarioDao.getUsuario(donoId);
		rt.setCorpoMensagem("RT @"+dono.getLogin()+"/ " + mensagem);
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.save(rt);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void responder(String resposta, int idRespondido, int usuario) {
		Tweet resp = new Tweet();
		resp.setCorpoMensagem(resposta);
		resp.setUsuarioDono(usuarioDao.getUsuario(usuario));
		resp.setRespondeuTweet(idRespondido);
		Session session = HibernateUtil.getSessionFactory().openSession();
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
