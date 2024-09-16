package Store_Management;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Products implements Comparable<Products>, ProfitCalculation {
	protected String product_name;
	protected int cost_price;
	protected int selling_price;
	protected int stock;
	protected String catalogNumber;
	protected ArrayList<Order> orders;
	protected double profitPerProduct;

	public Products(String catalogNumber, String product_name, int cost_price, int selling_price, int stock) {
		setCatalogNumber(catalogNumber);
		setProduct_name(product_name);
		setCost_price(cost_price);
		setSelling_price(selling_price);
		setStock(stock);
		this.orders = new ArrayList<>();
		this.profitPerProduct = 0;
	}

	public String getProduct_name() {
		return product_name;
	}

	public double getprofitPerProduct() {
		return profitPerProduct;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCost_price() {
		return cost_price;
	}

	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}

	public int getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> o) {
		orders = o;
	}

	public boolean addOrder(Order o) {
		if (o == null) {
			return false;
		}
		if (!orders.contains(o)) {
			orders.add(o);
			return true;
		}
		return false;
	}

	public boolean removeOrder(Order o) {
		if (o == null) {
			return false;
		}
		if (!orders.contains(o)) {
			orders.remove(o);
			return true;
		}
		return false;
	}

	public double sumProfit() {
		profitPerProduct = 0;
		for (Order oTemp : orders) {
			if (oTemp instanceof OrderInWebsite) {
				profitPerProduct += (double) ((selling_price - cost_price) * oTemp.getNumOfProduct()
						* OrderInWebsite.getDollarrate());
			} else {
				profitPerProduct += (double) (selling_price - cost_price) * oTemp.getNumOfProduct();
			}
		}
		return profitPerProduct;
	}

	@Override
	public int compareTo(Products p) {
		return this.getCatalogNumber().compareTo(p.getCatalogNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catalogNumber == null) ? 0 : catalogNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Products product = (Products) o;
		return catalogNumber.equals(product.catalogNumber);
	}

	public String toStringOrders() {
		int j = 0;
		StringBuffer sb = new StringBuffer("");
		Iterator<Order> itO = orders.iterator();
		while (itO.hasNext()) {
			sb.append("  " + (j + 1) + ". " + itO.next().toString() + "\n");
			j++;
		}
		sb.append("\nThe profit for product: " + sumProfit() + "₪\n");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Products [catalogNumber=" + catalogNumber + ", product name=" + product_name + ", cost price="
				+ cost_price + ", selling price=" + selling_price + ", stock=" + stock + ",  product type ="
				+ getClass().getSimpleName() + ", \nOrders=\n" + toStringOrders() + "]";
	}
}
