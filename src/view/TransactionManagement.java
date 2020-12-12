package view;

import controller.CartController;

public class TransactionManagement {

	public TransactionManagement() {
		CartController.getInstance().view(); //Fungsi untuk memanggil view cart
	}

}
