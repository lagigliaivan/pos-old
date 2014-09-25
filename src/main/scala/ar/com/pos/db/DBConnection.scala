package ar.com.pos.db

import java.security.InvalidParameterException
import java.util.ArrayList
import java.util.HashSet
import java.util.List

import scala.collection.JavaConverters._

import org.hibernate.HibernateException
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import ar.com.pos.db.dto.Product
import ar.com.pos.db.dto.Sale
import ar.com.terminal.db.SessionFactoryUtil
import javax.persistence.NoResultException

object DBConnection extends Database {

  private val log = LoggerFactory.getLogger(DBConnection.getClass());

  def getAllProducts(): List[Product] = {

    var products = new ArrayList[Product]();

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from Product"));
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage());
      }
    }

    return products;
  }

  def getProductsbyId(id: String): List[Product] = {

    if ((id == null) || (id.isEmpty())) {
      throw new InvalidParameterException("Product ID cannot be null or empty");
    }

    var products = new ArrayList[Product]();

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from Product u where u.id like " + id + "%"));
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage());
      }
    }

    return products;

  }

  def getProductsbyDescription(productDesc: String): List[Product] = {

    if ((productDesc == null) || (productDesc.isEmpty())) {
      throw new InvalidParameterException("Product description cannot be null or empty");
    }

    var products = new ArrayList[Product]();

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.description like %" + productDesc + "%"));
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage());
      }
    }

    return products;
  }

  def getProductById(id: String): Product = {

    if ((id == null) || (id.isEmpty())) {
      throw new InvalidParameterException("Product ID cannot be null or empty");
    }

    var products = new ArrayList[Product]();

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.idproduct = " + id));
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage());
      }
    }

    if (products.isEmpty()) {
      throw new NoResultException("Product " + id + " was not found.");
    }

    return products.get(0);
  }

  def save(sale: Sale) = {

    try {
      var session = SessionFactoryUtil.getSessionFactory().openSession();
      var transaction = session.beginTransaction();
      var products = new HashSet[ProductHbm]();

      var productsEntry = sale.products;

      for (productAndStock <- productsEntry) {
        var product = productAndStock._1;
        var productHbm = getProductHbm(product);
        productHbm.stock = productAndStock._2;
        products.add(productHbm);
      }

      var saleHbm = new SaleHbm();
      saleHbm.date = sale.date;
      saleHbm.products = products;
      saleHbm.totalAmount = sale.totalPrice;

      session.save(saleHbm);
      session.flush();
      transaction.commit();
      session.close();

    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage());
      }
    }
  }

  protected def getProductHbm(product: Product): ProductHbm = {

    var productHbm = new ProductHbm();
    productHbm.idproduct = product.id;
    productHbm.description = product.description;
    productHbm.price = product.price;

    return productHbm;
  }

  def save(product: Product) = {

    try {
      var session = SessionFactoryUtil.getSessionFactory().openSession();
      var transaction = session.beginTransaction();

      session.save(product);
      session.flush();
      transaction.commit();
      session.close();

    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage());
      }
    }

  }

  protected def getItemsUsingTheFollowingQuery(query: String): List[Product] = {

    var existingProducts = new ArrayList[Product]();

    var session = SessionFactoryUtil.getSessionFactory.openSession;
    
    session.beginTransaction;

    var hqlQuery = session.createQuery(query);

    var products = hqlQuery.asInstanceOf[List[ProductHbm]];

    for (product <- products.asScala) {
      var existingProduct = new Product(product.idproduct, product.price, product.description);
      existingProducts.add(existingProduct);
    }

    session.getTransaction.commit;
    session.close;

    return existingProducts;
  }

  def addProduct(product: ar.com.pos.db.dto.Product, amount: Integer): Unit = {}
  def getStock(id: String): Integer = { return Integer.getInteger(id) }
  def save(sale: ar.com.terminal.db.dto.Sale): Unit = {}

}