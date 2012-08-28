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
			Query query = session.createQuery("from Tweet");
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

	public void retweetar(Tweet tweet, Usuario usuario) {
	}

	public void excluir(Tweet tweet) {
	}

}
