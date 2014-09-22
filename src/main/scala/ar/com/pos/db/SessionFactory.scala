package ar.com.pos.db

import org.hibernate.cfg.Configuration

object SessionFactory {

  private val _sessionFactory = new Configuration().configure().buildSessionFactory();

  def sessionFactory = _sessionFactory;
}