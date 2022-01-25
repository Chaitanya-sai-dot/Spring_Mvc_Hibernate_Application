package crudHibernate.mvcHibernate.dao;

	import java.util.List;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.query.Query;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;

import crudHibernate.mvcHibernate.model.User;


	@Repository
	public class UserDao {
	@Autowired
	    private SessionFactory sessionFactory;
	 
	    public void setSessionFactory(SessionFactory sf) {
	        this.sessionFactory = sf;
	    }
	 
	    public List getAllUsers() {
	        Session session = this.sessionFactory.getCurrentSession();
	        List userList = session.createQuery("from User").list();
	        return userList;
	    }
	 
	    public User getUser(int id) {
	        Session session = this.sessionFactory.getCurrentSession();
	        User user = (User) session.load(User.class, new Integer(id));
	        return user;
	    }
	 
	    public User addUser(User user) {
	        Session session = this.sessionFactory.getCurrentSession();
	        session.persist(user);
	        return user;
	    }
	 
	    public void updateUser(User user) {
	        Session session = this.sessionFactory.getCurrentSession();
	        session.update(user);
	    }
	    public void deleteUser(int id) {
	        Session session = this.sessionFactory.getCurrentSession();
	        User p = (User) session.load(User.class, new Integer(id));
	        if (null != p) {
	            session.delete(p);
	        }
	    } 
		
	}

