package Store_Management;

import Store_Management.SoldThroughWebsite.eDelivery;

public class OrderInWebsite extends Order {
	private static final double DOLLARRATE = 4.0;
	private double deliveryCost;
	private DeliveryCompany deliveryC;
	private int deliveryType;

	public OrderInWebsite(String orderCatalogNumber, Products product, Customer customer, int numOfProduct,
			DeliveryCompany deliveryC) {
		super(orderCatalogNumber, product, customer, numOfProduct);

	}

	public DeliveryCompany getDeliveryC() {
		return deliveryC;
	}

	public void setDeliveryC(DeliveryCompany deliveryC) {
		this.deliveryC = deliveryC;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}

	public static double getDollarrate() {
		return DOLLARRATE;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tThe price for one prduct is: " + (getProduct().getSelling_price() * DOLLARRATE)
				+ "₪\n\tThe Delivery price: " + deliveryCost + "\n\tDelivery type: " + eDelivery.values()[deliveryType]
				+ "\n\tThe Delivery company: " + deliveryC.getClass().getSimpleName() + "\n\tTotal price: "
				+ ((getProduct().getSelling_price() * getNumOfProduct() * DOLLARRATE) + deliveryCost) + "₪";
	}

}
