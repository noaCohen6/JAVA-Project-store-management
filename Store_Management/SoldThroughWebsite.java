package Store_Management;

public class SoldThroughWebsite extends Products {

	private String dest_countery;
	private double product_weight;

	protected enum eDelivery {
		StandardShipping, ExpressShipping, StandardShippingAndExpressShipping
	};

	private eDelivery theDelivery;

	public SoldThroughWebsite(String catalogNumber, String product_name, int cost_price, int selling_price, int stock,
			String dest_countery, eDelivery theDelivery, double product_weight) {
		super(catalogNumber, product_name, cost_price, selling_price, stock);
		setDest_countery(dest_countery);
		setTheDelivery(theDelivery);
		setProduct_weight(product_weight);
	}

	public double getProduct_weight() {
		return product_weight;
	}

	public void setProduct_weight(double product_weight) {
		this.product_weight = product_weight;
	}

	public String getDest_countery() {
		return dest_countery;
	}

	public void setDest_countery(String dest_countery) {
		this.dest_countery = dest_countery;
	}

	public eDelivery getTheDelivery() {
		return theDelivery;
	}

	public void setTheDelivery(eDelivery theDelivery) {
		this.theDelivery = theDelivery;
	}

	public void printEnum() {
		for (eDelivery d : eDelivery.values()) {
			System.out.println(d.ordinal() + 1 + ". " + d);
		}
	}

}
