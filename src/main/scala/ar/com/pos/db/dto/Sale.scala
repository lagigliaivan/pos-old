package ar.com.pos.db.dto

import java.util.Date

class Sale (val date:Date , val products: java.util.Map[Product, Integer], val totalPrice: Float){
	
}