package ar.com.pos.db

import java.security.InvalidParameterException
import java.util.ArrayList
import java.util.HashSet
import java.util.List

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.JavaConverters.mapAsScalaMapConverter

import org.hibernate.HibernateException
import org.slf4j.LoggerFactory

import ar.com.pos.db.dto.Product
import ar.com.pos.db.dto.Sale
import javax.persistence.NoResultException

object DBConnection extends Database {

  private val log = LoggerFactory.getLogger("DBConnection")

  def getAllProducts(): List[Product] = {

    val products = new ArrayList[Product]()

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from Product"))
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage())
      }
    }

    return products
  }

  def getProductsbyId(id: String): List[Product] = {

    if ((id == null) || (id.isEmpty())) {
      throw new InvalidParameterException("Product ID cannot be null or empty")
    }

    val products = new ArrayList[Product]()

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from Product u where u.id like " + id + "%"))
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage())
      }
    }

    return products

  }

  def getProductsbyDescription(productDesc: String): List[Product] = {

    if ((productDesc == null) || (productDesc.isEmpty())) {
      throw new InvalidParameterException("Product description cannot be null or empty")
    }

    val products = new ArrayList[Product]()

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.description like %" + productDesc + "%"))
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage())
      }
    }

    return products
  }

  def getProductById(id: String): Product = {

    if ((id == null) || (id.isEmpty())) {
      throw new InvalidParameterException("Product ID cannot be null or empty")
    }

    val products = new ArrayList[Product]()

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.idproduct = " + id ))
    } catch {
      case e: RuntimeException => log.error("Error when getting products from DB", e.getMessage())

    }

    if (products.isEmpty()) {
      throw new NoResultException("Product " + id + " was not found.")
    }

    return products.get(0)
  }

  def save(sale: Sale) = {

    try {
      val session = SessionFactory.openSession
      val transaction = session.beginTransaction
      val saleDetails = new HashSet[SaleDetailHbm]()

      val productsEntry = sale.products.asScala

      for (productAndAmount <- productsEntry) {
        val product = productAndAmount._1
        val amount = productAndAmount._2

        val saleDetail = getSaleDetail(product.id, amount)

        saleDetails.add(saleDetail)
      }

      val saleHbm = new SaleHbm
      saleHbm.setDate(sale.date)
      saleHbm.setDetail(saleDetails)
      saleHbm.setTotalAmount(sale.totalPrice)

      session.save(saleHbm)
      session.flush()
      transaction.commit()
      session.close()

    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage())
      }
    }
  }

  protected def getProductHbm(product: Product): ProductHbm = {

    val productHbm = new ProductHbm()
    productHbm.setIdproduct (product.id)
    productHbm.setDescription (product.description)
    productHbm.setPrice (product.price)

    return productHbm
  }

  protected def getSaleDetail(productId: String, amount: Integer): SaleDetailHbm = {

    val saleDetailKeyHbm = new SaleDetailKeyHbm()
    val saleDetailHbm  = new SaleDetailHbm()

    saleDetailKeyHbm.setIdproduct(productId)

    saleDetailHbm.setSaleDetailKey(saleDetailKeyHbm)
    saleDetailHbm.setAmount(amount)

    saleDetailHbm
  }

  def save(product: Product) = {

    try {
      val session = SessionFactory.openSession
      val transaction = session.beginTransaction()

      session.save(product)
      session.flush()
      transaction.commit()
      session.close()

    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage())
      }
    }

  }

  protected def getItemsUsingTheFollowingQuery(query: String): List[Product] = {

    val existingProducts = new ArrayList[Product]()

    try {

      val session = SessionFactory.openSession

      session.beginTransaction

      val hqlQuery = session.createQuery(query)
      val products = hqlQuery.list()

      for (product <- products.asScala) {
        val p = product.asInstanceOf[ar.com.pos.db.ProductHbm]
        val existingProduct = new Product(p.getIdproduct, p.getPrice, p.getDescription)
        existingProducts.add(existingProduct)
      }

      session.getTransaction.commit
      session.close
    }catch {
      case e: HibernateException => {
        println(e)
        println(e.printStackTrace())
      }


    }

    existingProducts
  }

  def addProduct(product: ar.com.pos.db.dto.Product, amount: Integer): Unit = {}
  def getStock(id: String): Integer = { return Integer.getInteger(id) }

}