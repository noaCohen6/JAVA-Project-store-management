package Store_Management;

public class FedEx extends DeliveryCompany {

	private static final double SHIPPING_FEES_EXPRESS_PER_10KG = 50;
	private static final double SHIPPING_FEES_STANDARD_PER_10KG = 10;
	private static final double IMPORT_TAX = 20.0;

	public FedEx(String contact, int mobileContact) {
		super(contact, mobileContact);

	}

	public static double getShippingFeesExpressPer10kg() {
		return SHIPPING_FEES_EXPRESS_PER_10KG;
	}

	public static double getShippingFeesStandardPer10kg() {
		return SHIPPING_FEES_STANDARD_PER_10KG;
	}

	public static double getImportTax() {
		return IMPORT_TAX;
	}

	@Override
	public double ShippingFeesExpress(SoldThroughWebsite p) {

		int size = p.getOrders().size();
		return (((p.getProduct_weight() * p.getOrders().get(size - 1).getNumOfProduct()) / 10)
				* SHIPPING_FEES_EXPRESS_PER_10KG + IMPORT_TAX) * getDollarrate();
	}

	@Override
	public double ShippingFeesStandard(SoldThroughWebsite p) {

		int size = p.getOrders().size();
		return (((p.getProduct_weight() * p.getOrders().get(size - 1).getNumOfProduct()) / 10)
				* SHIPPING_FEES_STANDARD_PER_10KG) * getDollarrate();
	}
}
