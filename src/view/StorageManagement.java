package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.StorageController;
import model.StorageModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;

public class StorageManagement implements ActionListener{

	private JFrame frame;
	private JTable table;
	private JTextField pIdUpdate;
	private JTextField pNameUpdate;
	private JTextField pPriceUpdate;
	private DefaultTableModel dtm;
	private JButton btnUpdate;
	private JLabel lblDeleteProduct;
	private JLabel lblProductid_1;
	private JTextField pIdDel;
	private JButton btnDelete;
	private JLabel lblProductDescription;
	private JTextField pDescUpdate;
	private JLabel lblInsertProduct;
	private JLabel lblProductName;
	private JLabel lblProductDescription_1;
	private JTextField pNameAdd;
	private JTextField pDescAdd;
	private JTextField pPriceAdd;
	private JTextField pStockAdd;
	private JButton btnInsert;
	private JLabel lblNewLabel;
	private JLabel lblProductid_2;
	private JLabel lblProductStock_1;
	private JTextField pIdRestock;
	private JTextField pStockRestock;
	private JButton btnRestock;
	private JLabel lblStorageManagementForm;
	private JButton btnLogout;




	public StorageManagement() {
		table(); //table utama
		initialize();
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 709);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 634, 219);
		frame.getContentPane().add(table);
		
		
		JLabel lblUpdateProduct = new JLabel("Update Product");
		lblUpdateProduct.setBounds(108, 241, 122, 14);
		frame.getContentPane().add(lblUpdateProduct);
		
		JLabel lblProductid = new JLabel("ProductID :");
		lblProductid.setBounds(31, 277, 86, 14);
		frame.getContentPane().add(lblProductid);
		
		JLabel lblProduct = new JLabel("Product Name :");
		lblProduct.setBounds(31, 306, 112, 14);
		frame.getContentPane().add(lblProduct);
		
		JLabel lblProductPrice = new JLabel("Product Price :");
		lblProductPrice.setBounds(31, 331, 86, 14);
		frame.getContentPane().add(lblProductPrice);
		
		pIdUpdate = new JTextField();
		pIdUpdate.setBounds(148, 277, 86, 20);
		frame.getContentPane().add(pIdUpdate);
		pIdUpdate.setColumns(10);
		
		pNameUpdate = new JTextField();
		pNameUpdate.setBounds(148, 303, 86, 20);
		frame.getContentPane().add(pNameUpdate);
		pNameUpdate.setColumns(10);
		
		pPriceUpdate = new JTextField();
		pPriceUpdate.setBounds(148, 328, 86, 20);
		frame.getContentPane().add(pPriceUpdate);
		pPriceUpdate.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(145, 384, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 634, 150);
		frame.getContentPane().add(scrollPane);
		
		lblDeleteProduct = new JLabel("Delete Product");
		lblDeleteProduct.setBounds(108, 445, 86, 18);
		frame.getContentPane().add(lblDeleteProduct);
		
		lblProductid_1 = new JLabel("ProductID :");
		lblProductid_1.setBounds(65, 482, 78, 14);
		frame.getContentPane().add(lblProductid_1);
		
		pIdDel = new JTextField();
		pIdDel.setBounds(144, 479, 86, 20);
		frame.getContentPane().add(pIdDel);
		pIdDel.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(144, 510, 86, 23);
		frame.getContentPane().add(btnDelete);
		
		lblProductDescription = new JLabel("Product Desc :");
		lblProductDescription.setBounds(31, 356, 149, 14);
		frame.getContentPane().add(lblProductDescription);
		
		pDescUpdate = new JTextField();
		pDescUpdate.setBounds(148, 353, 86, 20);
		frame.getContentPane().add(pDescUpdate);
		pDescUpdate.setColumns(10);
		
		lblInsertProduct = new JLabel("Add Product");
		lblInsertProduct.setBounds(428, 241, 99, 14);
		frame.getContentPane().add(lblInsertProduct);
		
		lblProductName = new JLabel("Product Name : ");
		lblProductName.setBounds(338, 277, 112, 14);
		frame.getContentPane().add(lblProductName);
		
		lblProductDescription_1 = new JLabel("Product Desc : ");
		lblProductDescription_1.setBounds(338, 306, 149, 14);
		frame.getContentPane().add(lblProductDescription_1);
		
		JLabel lblProductPrice_1 = new JLabel("Product Price : ");
		lblProductPrice_1.setBounds(338, 331, 112, 14);
		frame.getContentPane().add(lblProductPrice_1);
		
		JLabel lblProductStock = new JLabel("Product Stock :");
		lblProductStock.setBounds(338, 356, 112, 14);
		frame.getContentPane().add(lblProductStock);
		
		pNameAdd = new JTextField();
		pNameAdd.setBounds(460, 274, 86, 20);
		frame.getContentPane().add(pNameAdd);
		pNameAdd.setColumns(10);
		
		pDescAdd = new JTextField();
		pDescAdd.setBounds(460, 303, 86, 20);
		frame.getContentPane().add(pDescAdd);
		pDescAdd.setColumns(10);
		
		pPriceAdd = new JTextField();
		pPriceAdd.setBounds(460, 328, 86, 20);
		frame.getContentPane().add(pPriceAdd);
		pPriceAdd.setColumns(10);
		
		pStockAdd = new JTextField();
		pStockAdd.setBounds(460, 353, 86, 20);
		frame.getContentPane().add(pStockAdd);
		pStockAdd.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInsert.setBounds(460, 384, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		lblNewLabel = new JLabel("Restock");
		lblNewLabel.setBounds(441, 447, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblProductid_2 = new JLabel("ProductID : ");
		lblProductid_2.setBounds(354, 482, 104, 14);
		frame.getContentPane().add(lblProductid_2);
		
		lblProductStock_1 = new JLabel("Product Stock");
		lblProductStock_1.setBounds(354, 514, 89, 14);
		frame.getContentPane().add(lblProductStock_1);
		
		pIdRestock = new JTextField();
		pIdRestock.setBounds(460, 479, 86, 20);
		frame.getContentPane().add(pIdRestock);
		pIdRestock.setColumns(10);
		
		pStockRestock = new JTextField();
		pStockRestock.setBounds(460, 511, 86, 20);
		frame.getContentPane().add(pStockRestock);
		pStockRestock.setColumns(10);
		
		btnRestock = new JButton("Restock");
		btnRestock.setBounds(460, 545, 89, 23);
		frame.getContentPane().add(btnRestock);
		
		lblStorageManagementForm = new JLabel("Storage Management Form");
		lblStorageManagementForm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblStorageManagementForm.setBounds(194, 174, 307, 37);
		frame.getContentPane().add(lblStorageManagementForm);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(262, 601, 89, 23);
		frame.getContentPane().add(btnLogout);
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnInsert.addActionListener(this);
		btnRestock.addActionListener(this);
		btnLogout.addActionListener(this);
	}
	
	//Fungsi untuk mereset textfield
	public void reset(){
		pIdUpdate.setText("");
		pPriceUpdate.setText("");
		pNameUpdate.setText("");
		pDescAdd.setText("");
		pNameAdd.setText("");
		pPriceAdd.setText("");
		pStockAdd.setText("");
		pIdDel.setText("");
		pIdRestock.setText("");
		pStockRestock.setText("");
	}
	
	//Fungsi yang memasukan data yang ada ke dalam row table
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<StorageModel> storage = new Vector<StorageModel>();
		storage = StorageController.getInstance().getAllProduct();
		
		table = new JTable(dtm);
		for(StorageModel storage2 : storage) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(storage2.getId());
			data.add(storage2.getProductName());
			data.add(storage2.getProductDescription());
			data.add(storage2.getProductPrice());
			data.add(storage2.getStock());
			dtm.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table
	public void getHeader() {
		String header[]= {"Product ID","Product Name","Product Description" , "Product Price" , "Product Stock"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk memasukan data kedalam table
	void table() {
		getHeader();
		getAll();
	}
	
	//Fungsi untuk melakukan update product
	public void update(){
		Integer productId = Integer.parseInt(pIdUpdate.getText());
		Integer productPrice = Integer.parseInt(pPriceUpdate.getText());
		String productName = pNameUpdate.getText();
		String productDesc = pDescUpdate.getText();
		
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Update"); 
	    if(option == JOptionPane.YES_OPTION){
	    	StorageController.getInstance().updateProduct(productId, productPrice, productName, productDesc);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk melakukan delee product
	public void delete() {
		Integer productId = Integer.parseInt(pIdDel.getText());
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Update"); 
	    if(option == JOptionPane.YES_OPTION){
	    	StorageController.getInstance().deleteProduct(productId);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk melakukan insert product
	public void insert() {
		String productName = pNameAdd.getText();
		String productDesc = pDescAdd.getText();
		Integer productPrice = Integer.parseInt(pPriceAdd.getText());
		Integer productStock = Integer.parseInt(pStockAdd.getText());
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Insert"); 
	    if(option == JOptionPane.YES_OPTION){
	    	StorageController.getInstance().insertProduct(productName, productDesc, productPrice, productStock);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk merestock stock product
	public void restock() {
		Integer productId = Integer.parseInt(pIdRestock.getText());
		Integer productStock = Integer.parseInt(pStockRestock.getText());
		
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Restock"); 
	    if(option == JOptionPane.YES_OPTION){
	    	StorageController.getInstance().restock(productStock, productId);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnUpdate)) {
			update();
		}else if(e.getSource().equals(btnDelete)) {
			delete();
		}else if(e.getSource().equals(btnInsert)) {
			insert();
		}else if(e.getSource().equals(btnRestock)) {
			restock();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}
	}
}
