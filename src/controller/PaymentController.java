package controller;

import java.util.Vector;

import model.PaymentModel;
import view.Payment;

public class PaymentController {

	public PaymentController() {
		
	}

	public static PaymentController paymentController;
	private PaymentModel payment = new PaymentModel();

	
	public static PaymentController getInstance() {
		if(paymentController == null) {
			return new PaymentController();
		}else {
			return paymentController;
		}
	}
	
	//Untuk memanggil view Payment
	public Payment view() {
		return new Payment();
	}
	
	//Untuk memanggil fungsi getAllFromCart
	public Vector<PaymentModel> getAllFromCart(){
		return payment.getAllFromCart();
	}
}
