package ar.com.pos

import ar.com.pos.ui.MainWindow
import ar.com.terminal.{SalesReportWindow, SalesWindow, AddProductWindow}


object Main {
	
  def main(args: Array[String]){
	  	
	  val saleWindow = new SalesWindow()
		val addProductWindow = new AddProductWindow()
    val salesWindowReport = new SalesReportWindow()
		val mainWindow = new MainWindow(saleWindow, addProductWindow, salesWindowReport)

		mainWindow.run()
  }
}