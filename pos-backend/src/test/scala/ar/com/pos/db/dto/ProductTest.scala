package ar.com.pos.db.dto

import org.slf4j.Logger
import org.junit.Assert
import org.slf4j.LoggerFactory
import java.security.InvalidParameterException
import org.junit.Test


class ProductTest {

  val logger = LoggerFactory.getLogger("ProductTest")

  @Test
  def productCanBeCreatedWhenParametersAreOk() = {
    val id = "1"
    val price = 0.0F
    val desc = "producto1"

    val product = new Product(id, price, desc)

    Assert.assertEquals(id, product.id)
    Assert.assertEquals(price, product.price,1)
    Assert.assertEquals(desc, product.description)
  }

  @Test
  def productCanNotBeCreatedWithNegativeOrNullPrice(): Unit = {
    val id = "1"
    val desc = "producto1"

    try{
      val product = new Product(id, -1F, desc)
    }catch{
      case exception: IllegalArgumentException => {
        logger.info("Product could not be created with a negative or null price")
        return
      }
    }

    Assert.fail()

  }

  @Test
  def productCanNotBeCreatedWithEmptyOrNullDescription() : Unit= {
    val id = "1"
    val price = 1F

    try{
      val product = new Product(id, price, null)
    }catch{
      case exception: IllegalArgumentException => {
        logger.info("Product could not be created with a empty or null name")
        return
      }
    }

    Assert.fail()
  }
  @Test
  def productCanNotBeCreatedWithEmptyOrNullId() : Unit = {

    val price = 1.0F
    val desc = "producto1"

    try{
      val product = new Product("", price, desc)
    }catch{
      case exception: IllegalArgumentException => {
        logger.info("Product could not be created with a negative or null id")
        return
      }

    }
    Assert.fail()

  }

}
