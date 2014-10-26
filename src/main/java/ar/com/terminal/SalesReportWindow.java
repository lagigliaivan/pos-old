package ar.com.terminal;

import ar.com.pos.db.dto.Sale;
import ar.com.pos.ui.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by yo on 19/10/14.
 */
public class SalesReportWindow {

    private JTextField textField1;
    private JTextField textField2;
    private JPanel JPanelSalesReport;
    private JTable tableSales;
    private JScrollPane scrollPane;

    public SalesReportWindow(){
        List <String> columnsName = new ArrayList<String>();
        columnsName.add("#");
        columnsName.add("Fecha");
        columnsName.add("Total Vendido");
        columnsName.add("Cantidad de productos");

        DefaultTableModel tableModel = (DefaultTableModel)tableSales.getModel();

        for(String column : columnsName){
            tableModel.addColumn(column);
        }
    }

    public JPanel getJPanelSalesReport() {
        return JPanelSalesReport;
    }

    public void setJPanelSalesReport(JPanel JPanelSalesReport) {
        this.JPanelSalesReport = JPanelSalesReport;
    }

    public void setData(List<Sale> data) {

        ar.com.pos.ui.View view = new ar.com.pos.ui.View();
        clearTableData();
        view.addSalesToTheFollowingTable((DefaultTableModel)tableSales.getModel(), data);

        getJPanelSalesReport().setVisible(true);

        scrollPane.setVisible(true);
        scrollPane.repaint();
        tableSales.setVisible(true);
        tableSales.repaint();
    }

    private void clearTableData(){
        DefaultTableModel model = (DefaultTableModel)tableSales.getModel();

        for(int i= model.getRowCount(); i>0; --i){
            model.removeRow(i-1);
        }
    }

    public boolean isModified(SalesReportListData data) {
        return false;
    }
}
