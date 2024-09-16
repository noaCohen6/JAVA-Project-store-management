package Store_Management;

import java.util.ArrayList;
import java.util.TreeSet;

public class CompanyMemento {
	private final TreeSet<Products> allProducts;
	private final ArrayList<Order> allOrders;
	private final ArrayList<DeliveryCompany> allDelivery;

	public CompanyMemento(TreeSet<Products> allProducts, ArrayList<Order> allOrders,
			ArrayList<DeliveryCompany> allDelivery) {
		this.allProducts = new TreeSet<>(allProducts);
		this.allOrders = new ArrayList<>(allOrders);
		this.allDelivery = new ArrayList<>(allDelivery);
	}

	public TreeSet<Products> getAllProducts() {
		return allProducts;
	}

	public ArrayList<Order> getAllOrders() {
		return allOrders;
	}

	public ArrayList<DeliveryCompany> getAllDelivery() {
		return allDelivery;
	}

}
