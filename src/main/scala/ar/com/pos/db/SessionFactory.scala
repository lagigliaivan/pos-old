package ar.com.pos.db

import org.hibernate.cfg.Configuration
import org.hibernate.Session

object SessionFactory {

  private val _sessionFactory = new Configuration().configure().buildSessionFactory();

  def sessionFactory = _sessionFactory;
  
  def openSession(): Session = {
    return _sessionFactory.openSession();
  }
  
}