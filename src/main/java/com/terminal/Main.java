package com.terminal;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MainWindow mainWindow = new MainWindow();
		SalesWindow saleWindow = new SalesWindow();
		AddProductWindow productWindow = new AddProductWindow();
		mainWindow.setSaleWindow(saleWindow);
		mainWindow.setAddProductWindow(productWindow);

		JFrame mainFrame = mainWindow.getJFrameVenta();
		mainFrame.add(saleWindow.getJPanelMain());
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setVisible(true);
	}

}
