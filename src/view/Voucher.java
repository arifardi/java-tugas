package view;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.LoginController;
import controller.StorageController;
import controller.VoucherController;
import model.VoucherModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Voucher implements ActionListener{

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField discountInsert;
	private JTextField dateVoucher;
	private JButton btnInsert;
	private JLabel lblUpdateVoucher;
	private JLabel lblVoucherId;
	private JLabel lblNewLabel_2;
	private JLabel lblValidDate;
	private JTextField idUpdate;
	private JTextField discountUpdate;
	private JTextField dateUpdate;
	private JButton btnUpdate;
	private JLabel lblDeleteVoucher;
	private JLabel lblVoucherId_1;
	private JTextField idDelete;
	private JButton btnDelete;
	private JButton btnLogout;

	public Voucher() {
		table(); //table utama
		initialize();
		addlistener(); //Fungsi yang berisi berbagai macam tombol agar dapat di tekan
		frame.setVisible(true);
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 735, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		table.setBounds(5, 4, 709, 196);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(3, 3, 712, 198);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblInsertVoucher = new JLabel("Insert Voucher");
		lblInsertVoucher.setBounds(61, 275, 103, 14);
		frame.getContentPane().add(lblInsertVoucher);
		
		JLabel lblVoucherManagementForm = new JLabel("Voucher Management Form");
		lblVoucherManagementForm.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblVoucherManagementForm.setBounds(215, 220, 292, 38);
		frame.getContentPane().add(lblVoucherManagementForm);
		
		JLabel lblNewLabel = new JLabel("Discount : ");
		lblNewLabel.setBounds(20, 317, 77, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Valid Date :");
		lblNewLabel_1.setBounds(20, 342, 77, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		discountInsert = new JTextField();
		discountInsert.setBounds(92, 314, 86, 20);
		frame.getContentPane().add(discountInsert);
		discountInsert.setColumns(10);
		
		dateVoucher = new JTextField();
		dateVoucher.setBounds(92, 339, 86, 20);
		frame.getContentPane().add(dateVoucher);
		dateVoucher.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(89, 375, 89, 23);
		frame.getContentPane().add(btnInsert);
		
		lblUpdateVoucher = new JLabel("Update Voucher");
		lblUpdateVoucher.setBounds(329, 275, 124, 14);
		frame.getContentPane().add(lblUpdateVoucher);
		
		lblVoucherId = new JLabel("Voucher ID :");
		lblVoucherId.setBounds(248, 317, 86, 14);
		frame.getContentPane().add(lblVoucherId);
		
		lblNewLabel_2 = new JLabel("Discount :");
		lblNewLabel_2.setBounds(247, 342, 77, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblValidDate = new JLabel("Valid Date : ");
		lblValidDate.setBounds(248, 367, 76, 14);
		frame.getContentPane().add(lblValidDate);
		
		idUpdate = new JTextField();
		idUpdate.setBounds(340, 314, 86, 20);
		frame.getContentPane().add(idUpdate);
		idUpdate.setColumns(10);
		
		discountUpdate = new JTextField();
		discountUpdate.setBounds(340, 339, 86, 20);
		frame.getContentPane().add(discountUpdate);
		discountUpdate.setColumns(10);
		
		dateUpdate = new JTextField();
		dateUpdate.setBounds(340, 364, 86, 20);
		frame.getContentPane().add(dateUpdate);
		dateUpdate.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(337, 395, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		lblDeleteVoucher = new JLabel("Delete Voucher");
		lblDeleteVoucher.setBounds(568, 275, 89, 14);
		frame.getContentPane().add(lblDeleteVoucher);
		
		lblVoucherId_1 = new JLabel("Voucher ID :");
		lblVoucherId_1.setBounds(493, 317, 86, 14);
		frame.getContentPane().add(lblVoucherId_1);
		
		idDelete = new JTextField();
		idDelete.setBounds(571, 314, 86, 20);
		frame.getContentPane().add(idDelete);
		idDelete.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(568, 358, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(337, 458, 89, 23);
		frame.getContentPane().add(btnLogout);
	}
	
	//Fungsi yang memasukan data yang ada ke dalam row table
	public void getAll(){
		if (dtm.getRowCount() > 0) {
		    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
		        dtm.removeRow(i);
		    }
		}
		
		Vector<VoucherModel> voucher = new Vector<VoucherModel>();
		voucher = VoucherController.getInstance().getAllVoucher();
		
		table = new JTable(dtm);
		for(VoucherModel voucher2 : voucher) {
			Vector<Object> data = new Vector<Object>();
			data.add(voucher2.getVoucherId());
			data.add(voucher2.getDiscount());
			data.add(voucher2.getValidDate());
			data.add(voucher2.getStatus());
			dtm.addRow(data);
		}
	}
	
	//Fungsi yang membuat header dari table
	public void getHeader() {
		String header[]= {"Voucher ID", "Discount" , "Valid Date" , "Status"};
		dtm = new DefaultTableModel(header,0); 
	}
	
	//Fungsi yang berisi berbagai macam tombol agar dapat di tekan
	void addlistener() {
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogout.addActionListener(this);
	}
	
	//Fungsi untuk mereset textfield
	void reset() {
		dateVoucher.setText("");
		discountInsert.setText("");
		idUpdate.setText("");
		discountUpdate.setText("");
		dateUpdate.setText("");
		idDelete.setText("");
}
	
	//Fungsi untuk memasukan data kedalam table
	void table() {
		getHeader();
		getAll();
	}
	
	//Fungsi untuk melakukan insert product
	public void insert() {
		
		float discount =  Float.parseFloat(discountInsert.getText());
		String validDate = dateVoucher.getText();
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Insert"); 
	    if(option == JOptionPane.YES_OPTION){
	    	VoucherController.getInstance().insertVoucher(discount, validDate);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk melakukan update product
	public void update() {
		Integer voucherId = Integer.parseInt(idUpdate.getText());
		float discount =  Float.parseFloat(discountUpdate.getText());
		String validDate = dateUpdate.getText();
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Update"); 
	    if(option == JOptionPane.YES_OPTION){
	    	VoucherController.getInstance().updateVoucher(voucherId, discount, validDate);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	
	//Fungsi untuk melakukan delee product
	public void delete() {
		Integer voucherId = Integer.parseInt(idDelete.getText());
		
	    int option = JOptionPane.showConfirmDialog(null, "Success Update"); 
	    if(option == JOptionPane.YES_OPTION){
	    	VoucherController.getInstance().deleteVocuher(voucherId);
	    }else{
	    	
	    }
	    reset();
	    getAll();
	}
	


	//Fungsi yang akan dijalankan ketika terjadi sebuah action berupa penekana sebuah button akan di check ke dalam sini
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnInsert)) {
			insert();
		}else if(e.getSource().equals(btnUpdate)) {
			update();
		}else if(e.getSource().equals(btnDelete)) {
			delete();
		}else if(e.getSource().equals(btnLogout)) {
			frame.dispose();
			LoginController.getInstance().view();
		}
	}
}
