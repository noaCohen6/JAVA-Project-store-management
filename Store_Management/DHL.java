package Store_Management;

public class DHL extends DeliveryCompany {

	public DHL(String contact, int mobileContact) {
		super(contact, mobileContact);
	}

	private static final double SHIPPING_FEES_EXPRESS = 100.0;
	private static final double IMPORT_TAX = 20.0;

	public static double getShippingFeesExpress() {
		return SHIPPING_FEES_EXPRESS;
	}

	public static double getImportTax() {
		return IMPORT_TAX;
	}

	@Override
	public double ShippingFeesExpress(SoldThroughWebsite p) {
		return (SHIPPING_FEES_EXPRESS + IMPORT_TAX) * getDollarrate();
	}

	@Override
	public double ShippingFeesStandard(SoldThroughWebsite p) {

		int size = p.getOrders().size();
		if ((double) p.selling_price * 0.1 * p.getOrders().get(size - 1).getNumOfProduct() < SHIPPING_FEES_EXPRESS) {
			return ((double) p.selling_price * 0.1 * p.getOrders().get(size - 1).getNumOfProduct()) * getDollarrate();
		}
		return (SHIPPING_FEES_EXPRESS) * getDollarrate();
	}

}
