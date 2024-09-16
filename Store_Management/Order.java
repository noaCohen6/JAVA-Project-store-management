package Store_Management;

public abstract class Order {

	protected Products product;
	protected Customer customer;
	protected int numOfProduct;
	protected String orderCatalogNumber;

	public Order(String orderCatalogNumber, Products product, Customer customer, int numOfProduct) {
		setProduct(product);
		setCustomer(customer);
		setNumOfProduct(numOfProduct);
		setOrderCatalogNumber(orderCatalogNumber);
	}

	public String getOrderCatalogNumber() {
		return orderCatalogNumber;
	}

	public void setOrderCatalogNumber(String orderCatalogNumber) {
		this.orderCatalogNumber = orderCatalogNumber;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getNumOfProduct() {
		return numOfProduct;
	}

	public void setNumOfProduct(int numOfProduct) {
		this.numOfProduct = numOfProduct;
	}

	@Override
	public String toString() {
		return "Order [order catalogNumber = " + orderCatalogNumber + " catalog number product= "
				+ getProduct().getCatalogNumber() + " " + customer.toString() + ", numOfProduct=" + numOfProduct + "]";
	}

}
