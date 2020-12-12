package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.LoginController;
import controller.PaymentController;
import controller.StorageController;
import model.CartModel;
import model.StorageModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Cart implements ActionListener{

	private JFrame frame;
	private JTable table;
	private JTextField idAdd;
	private JTextField qtyAdd;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm_product;
	private JButton btnAdd;
	private JButton btnPayment;
	private JButton btnLogout;
	private JLabel lblCart;
	private JTable table_product;
	private JLabel lblProduct;
	private JScrollPane scrollPane_product;
	private JLabel lblDeleteFromCart;
	private JLabel lblNewLabel;
	private JTextField idDel;
	private JButton btnDelete;


	public Cart() {
		table(); //table cart utama
		table_product(); //table product
		initialize(); 
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table.setBounds(10, 11, 501, 160);
		frame.getContentPane().add(table);
		
		JLabel lblAddToCart = new JLabel("Add to Cart ");
		lblAddToCart.setBounds(158, 326, 87, 14);
		frame.getContentPane().add(lblAddToCart);
		
		JLabel lblProductId = new JLabel("Product ID :");
		lblProductId.setBounds(113, 358, 79, 14);
		frame.getContentPane().add(lblProductId);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setBounds(114, 390, 65, 14);
		frame.getContentPane().add(lblQuantity);
		
		idAdd = new JTextField();
		idAdd.setBounds(198, 357, 86, 20);
		frame.getContentPane().add(idAdd);
		idAdd.setColumns(10);
		
		qtyAdd = new JTextField();
		qtyAdd.setBounds(198, 386, 86, 20);
		frame.getContentPane().add(qtyAdd);
		qtyAdd.setColumns(10);
		
		btnAdd = new JButton("Add ");
		btnAdd.setBounds(198, 418, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(19, 69, 296, 218);
		frame.getContentPane().add(scrollPane);
		
		btnPayment = new JButton("Payment");
		btnPayment.setBounds(601, 482, 89, 23);
		frame.getContentPane().add(btnPayment);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(66, 482, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		lblCart = new JLabel("Cart");
		lblCart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCart.setBounds(128, 16, 107, 60);
		frame.getContentPane().add(lblCart);
		
		table_product.setBounds(333, 67, 385, 220);
		frame.getContentPane().add(table_product);
		
		lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProduct.setBounds(480, 19, 140, 46);
		frame.getContentPane().add(lblProduct);
		
		scrollPane_product = new JScrollPane(table_product);
		scrollPane_product.setBounds(332, 67, 386, 105);
		frame.getContentPane().add(scrollPane_product);
		
		lblDeleteFromCart = new JLabel("Delete from Cart");
		lblDeleteFromCart.setBounds(401, 326, 123, 14);
		frame.getContentPane().add(lblDeleteFromCart);
		
		lblNewLabel = new JLabel("Product ID :");
		lblNewLabel.setBounds(327, 358, 87, 14);
		frame.getContentPane().add(lblNewLabel);
		
		idDel = new JTextField();
		idDel.setBounds(401, 355, 86, 20);
		frame.getContentPane().add(idDel);
		idDel.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(401, 386, 89, 23);
		frame.getContentPane().add(btnDelete);
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnAdd.addActionListener(this);
		btnPayment.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogout.addActionListener(this);
	}
	
	//Fungsi yang memasukan data cart yang ada ke dalam row table
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<CartModel> cart = new Vector<CartModel>();
		cart = CartController.getInstance().getAllCartItem();
		
		table = new JTable(dtm);
		for(CartModel cart2 : cart) {
			Vector<Object> data = new Vector<Object>();
			data.add(cart2.getProductID());
			data.add(cart2.getQuantity());
			dtm.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table cart
	public void getHeader() {
		String header[]= {"Product ID","Quantity" };
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi yang memasukan data product yang ada ke dalam row table
	public void getAllProduct() {
		if (dtm_product.getRowCount() > 0) {
		    for (int i = dtm_product.getRowCount() - 1; i > -1; i--) {
		        dtm_product.removeRow(i);
		    }
		}
		
		Vector<StorageModel> storage = new Vector<StorageModel>();
		storage = StorageController.getInstance().getAllProduct();
		
		table_product = new JTable(dtm_product);
		for(StorageModel storage2 : storage) {
			//dtm hanya menerima vector of object
			Vector<Object> data = new Vector<Object>();
			data.add(storage2.getId());
			data.add(storage2.getProductName());
			data.add(storage2.getProductPrice());
			data.add(storage2.getStock());
			dtm_product.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table product
	public void getHeaderProduct() {
		String header[]= {"Product ID","Product Name","Product Price", "Product Stock"};
		dtm_product = new DefaultTableModel(header,0); 
	}
	
	//Fungsi untuk memasukan data kedalam table product
	void table_product() {
		getHeaderProduct();
		getAllProduct();
	}
	
	//Fungsi untuk mereset text
	public void reset() {
		idAdd.setText("");
		qtyAdd.setText("");
	}
	
	//Fungsi untuk memasukan data kedalam table cart
	void table() {
		getHeader();
		getAll();
	}
	
	//Fungsi untuk memanggil fungsi add
	public void add() {
		Integer productID = Integer.parseInt(idAdd.getText());
		Integer quantity = Integer.parseInt(qtyAdd.getText());
		
	    CartController.getInstance().addToCart(productID, quantity);
	    getAll();
	    reset(); 
	}
	
	//Fungsi untuk memanggil fungsi delete
	public void del() {
		Integer productID = Integer.parseInt(idDel.getText());
		
		CartController.getInstance().deleteFromCart(productID);
		getAll();
		reset();
	}
	
	
	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAdd)) {
			add();
		}else if(e.getSource().equals(btnPayment)){
			frame.dispose();
			PaymentController.getInstance().view();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}else if(e.getSource().equals(btnDelete)) {
			del();
		}
		
	}
	
}
