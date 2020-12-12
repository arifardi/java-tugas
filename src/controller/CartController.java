package controller;

import java.util.Vector;

import model.CartModel;
import view.Cart;

public class CartController {

	public CartController() {
		// TODO Auto-generated constructor stub
	}
	
	public static CartController cartController;
	private CartModel cart = new CartModel();

	
	public static CartController getInstance() {
		if(cartController == null) {
			return new CartController();
		}else {
			return cartController;
		}
	}
	
	//Untuk memanggil view dari Cart
	public Cart view() {
		return new Cart();
	}
	
	//Memanggil fungsi getAllcart yang ada dalam model
	public Vector<CartModel> getAllCartItem(){
		return cart.getAllCart();
	}
	
	//Memanggil fungsi addToCart yang ada dalam model
	public void addToCart(Integer productID,Integer quantity) {
		cart.addToCart(productID, quantity);
	}
	
	//Memanggil fungsi deleteFromCart yang ada dalam model
	public void deleteFromCart(Integer productId) {
		cart.deleteProduct(productId);
	}
	
	//Memanggil fungsi resetCart yang ada dalam model
	public void resetCart() {
		cart.resetCart();
	}

}
