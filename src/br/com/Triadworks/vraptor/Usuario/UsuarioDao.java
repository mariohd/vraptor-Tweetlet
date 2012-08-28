package br.com.Triadworks.vraptor.Usuario;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Triadworks.vraptor.Hibernate.HibernateUtil;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDao {

	public Usuario getUsuario(String login, String senha) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Query query = session.createQuery(
					"from Usuario as u where u.login =:login and senha = :senha");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			Usuario usuario = (Usuario) query.uniqueResult();
			trns.commit();
			return usuario;
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

	
	public Usuario getUsuario(int id) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			Query query = session.createQuery(
					"from Usuario as u where u.id =:id");
			query.setParameter("id", id);
			Usuario usuario = (Usuario) query.uniqueResult();
			trns.commit();
			return usuario;
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
}
