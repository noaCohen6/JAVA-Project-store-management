package Store_Management;

public class OrderInWholesalers extends Order {

	private InvoiceAccountant invoiceA;

	public OrderInWholesalers(String orderCatalogNumber, Products product, Customer customer, int numOfProduct) {
		super(orderCatalogNumber, product, customer, numOfProduct);
		this.invoiceA = new InvoiceAccountant();

	}

	public InvoiceAccountant getInvoiceA() {
		return invoiceA;
	}

	public void setInvoiceA(InvoiceAccountant invoiceA) {
		this.invoiceA = invoiceA;
	}

	@Override
	public String toString() {
		return super.toString() + invoiceA.formatForAccountant(getProduct(), getNumOfProduct());
	}

}
