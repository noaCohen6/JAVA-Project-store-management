package Store_Management;

public abstract class DeliveryCompany implements StandardShipping, ExpressShipping {
	private static final double DOLLARRATE = 4.0;
	private String contact;
	private int mobileContact;

	public DeliveryCompany(String contact, int mobileContact) {
		setContact(contact);
		setMobileContact(mobileContact);
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getMobileContact() {
		return mobileContact;
	}

	public void setMobileContact(int mobileContact) {
		this.mobileContact = mobileContact;
	}

	public static double getDollarrate() {
		return DOLLARRATE;
	}

	@Override
	public String toString() {
		return "DeliveryCompany [contact=" + contact + ", mobileContact=" + mobileContact + "]";
	}

	public abstract double ShippingFeesStandard(SoldThroughWebsite p);

	public abstract double ShippingFeesExpress(SoldThroughWebsite p);

}
