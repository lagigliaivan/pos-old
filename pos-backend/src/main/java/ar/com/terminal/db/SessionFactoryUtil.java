package ar.com.terminal.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

	private static SessionFactory sessionFactory = null;
		
	private SessionFactoryUtil(){
		
	}
	
	public static SessionFactory getSessionFactory() throws Exception {
	    // A SessionFactory is set up once for an application
		if(sessionFactory == null){
		    sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
	
}