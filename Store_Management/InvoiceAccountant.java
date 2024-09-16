package Store_Management;

public class InvoiceAccountant implements FormatForAccountant {
	private double res;
	private double profit;

	public String formatForAccountant(Products p, int index) {
		StringBuffer sb = new StringBuffer("");
		res = p.selling_price * index;
		profit = (p.selling_price - p.cost_price) * index;
		sb.append("\nInvoice Accountant:\n\tProduct name: " + p.getProduct_name() + "\t Price: " + p.getSelling_price()
				+ "₪\t Quantity: " + index + "\t\n\n");
		sb.append("\tTotal Price: " + res + "₪\n" + "\tProfit: " + profit + "₪\n");
		return sb.toString();
	}

	public double getRes() {
		return res;
	}

	public void setRes(double res) {
		this.res = res;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}
}
