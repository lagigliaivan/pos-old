package ar.com.pos

import java.awt.Frame
import ar.com.terminal.AddProductWindow
import ar.com.terminal.SalesWindow
import javax.swing.JFrame
import ar.com.pos.ui.MainWindow


object Main {
	
  def main(args: Array[String]){
	  	
	  	val saleWindow = new SalesWindow();
		val addProductWindow = new AddProductWindow();
		val mainWindow = new MainWindow(saleWindow, addProductWindow);

		mainWindow.run();
  }
}