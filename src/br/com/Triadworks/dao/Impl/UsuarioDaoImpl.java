package br.com.Triadworks.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.Triadworks.dao.UsuarioDao;
import br.com.Triadworks.model.Usuario;

@Component("usuarioDaoHibernate")
public class UsuarioDaoImpl implements UsuarioDao {

	SessionFactory sessionFactory;

	@Autowired
	public UsuarioDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Usuario getUsuario(String login, String senha) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Usuario user = (Usuario) session
				.createQuery(
						"from Usuario as u where u.login =:login and senha = :senha")
				.setParameter("login", login).setParameter("senha", senha)
				.uniqueResult();
		session.getTransaction().commit();
		return user;
	}

	public Usuario getUsuario(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Usuario user = (Usuario) session
				.createQuery(
						"from Usuario as u where u.id =:id")
				.setParameter("id", id).uniqueResult();
		session.getTransaction().commit();
		return user;
	}

}
