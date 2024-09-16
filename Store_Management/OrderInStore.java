package Store_Management;

public class OrderInStore extends Order {

	private InvoiceAccountant invoiceA;
	private InvoiceCustomer invoiceC;

	public OrderInStore(String orderCatalogNumber, Products product, Customer customer, int numOfProduct) {
		super(orderCatalogNumber, product, customer, numOfProduct);
		this.invoiceC = new InvoiceCustomer();
		this.invoiceA = new InvoiceAccountant();

	}

	public InvoiceAccountant getInvoiceA() {
		return invoiceA;
	}

	public void setInvoiceA(InvoiceAccountant invoiceA) {
		this.invoiceA = invoiceA;
	}

	public InvoiceCustomer getInvoiceC() {
		return invoiceC;
	}

	public void setInvoiceC(InvoiceCustomer invoiceC) {
		this.invoiceC = invoiceC;
	}

	@Override
	public String toString() {
		return super.toString() + invoiceC.formatForCustomer(getProduct(), getNumOfProduct())
				+ invoiceA.formatForAccountant(getProduct(), getNumOfProduct());
	}

}
