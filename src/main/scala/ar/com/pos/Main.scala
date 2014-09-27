package ar.com.pos

import ar.com.pos.ui.MainWindow
import ar.com.terminal.SalesWindow
import ar.com.terminal.AddProductWindow


object Main {
	
  def main(args: Array[String]){
	  	
	  	val saleWindow = new SalesWindow();
		val addProductWindow = new AddProductWindow();
		val mainWindow = new MainWindow(saleWindow, addProductWindow);

		mainWindow.run();
  }
}