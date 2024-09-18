package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import java.util.List;

public class UserDaoImpl implements UserDao {
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@Override
	public void save(User user) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
			}
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public User findById(Long id) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(User.class, id);
		}
	}

	@Override
	public List<User> findAll() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from User", User.class).list();
		}
	}

	@Override
	public User findByEmail(String email) {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("from User where email = :email", User.class).setParameter("email", email)
					.uniqueResult();
		}
	}
}
