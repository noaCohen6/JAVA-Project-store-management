package Store_Management;

public class InvoiceCustomer implements FormatForCustomer {

	public String formatForCustomer(Products p, int index) {
		StringBuffer sb = new StringBuffer("");
		double res = p.selling_price * index;
		double tax = res * 0.17;
		sb.append("\nInvoice Customer:\n\tProduct name: " + p.getProduct_name() + "\t Price: " + p.getSelling_price()
				+ "₪\t Quantity: " + index + "\t\n\n");
		sb.append("\tTax: " + tax + "₪\n" + "\tTotal Price: " + res + "₪\n");
		return sb.toString();
	}

}
