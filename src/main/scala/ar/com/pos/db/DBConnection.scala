package ar.com.pos.db

import java.security.InvalidParameterException
import java.util.ArrayList
import java.util.HashSet
import java.util.List

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.JavaConverters.mapAsScalaMapConverter
import scala.collection.JavaConverters.asScalaSetConverter

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
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm"))
    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when getting products from DB", e.getMessage())
      }
    }

    products
  }

  def getProductsbyId(id: String): List[Product] = {

    if ((id == null) || (id.isEmpty())) {
      throw new InvalidParameterException("Product ID cannot be null or empty")
    }

    val products = new ArrayList[Product]()

    try {
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.id like '" + id + "%'"))
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
      products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.description like '%" + productDesc + "%'"))
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


  override def getSales(): List[ar.com.pos.db.dto.Sale] = {

    val existingSales = new ArrayList[ar.com.pos.db.dto.Sale]()

    try {

      val session = SessionFactory.openSession

      session.beginTransaction

      val hqlQuery = session.createQuery("from SaleHbm")
      val sales = hqlQuery.list()

      for (sale <- sales.asScala) {
        val s = sale.asInstanceOf[ar.com.pos.db.SaleHbm]
        val existingSale = new Sale(s.getDate, new java.util.HashMap[Product, Integer], s.getTotalAmount)
        existingSale.id_= (s.getIdSale)

        var productsTotalAmount = 0

        for (saleDetail <- s.getDetail.asScala){
         val sDetail = saleDetail.asInstanceOf[ar.com.pos.db.SaleDetailHbm]
         productsTotalAmount += saleDetail.getAmount
        }

        existingSale.productsTotalAmount_=(productsTotalAmount)
        existingSales.add(existingSale)
      }
      session.getTransaction.commit
      session.close
    }catch {
      case e: HibernateException => {
        println(e)
        println(e.printStackTrace())
      }
    }
    existingSales
  }


  def save(sale: Sale) = {

    try {

      val session = SessionFactory.openSession()
      val transaction = session.beginTransaction

      val saleHbm = new SaleHbm
      saleHbm.setDate(sale.date)
      saleHbm.setTotalAmount(sale.totalPrice)

      val saleId = session.save(saleHbm).asInstanceOf[Long]

      val productsEntry = sale.products.asScala

      for (productAndAmount <- productsEntry) {
        val product = productAndAmount._1
        val amount = productAndAmount._2
        val saleDetail = getSaleDetail(saleId, product.id, amount)
        session.save(saleDetail)
      }

      session.flush()
      transaction.commit()
      session.close()

    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when saving Sale", e)
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

  protected def getSaleDetail(saleId: Long, productId: String, amount: Integer): SaleDetailHbm = {

    val saleDetailHbm  = new SaleDetailHbm()

    saleDetailHbm.setIdSale(saleId)
    saleDetailHbm.setIdProduct(productId)
    saleDetailHbm.setAmount(amount)

    saleDetailHbm
  }

  def save(product: Product) = {

    try {
      val session = SessionFactory.openSession
      val transaction = session.beginTransaction()

      val productHbm = new ProductHbm()
      productHbm.setIdproduct(product.id)
      productHbm.setPrice(product.price)
      productHbm.setDescription(product.description)
      productHbm.setStock(product.stock)

      session.save(productHbm)
      session.flush()
      transaction.commit()
      session.close()

    } catch {
      case e: Exception => e match {
        case _: HibernateException | _: Exception => log.error("Error when saving products in the DB", e)
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
        existingProduct.stock = p.getStock
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