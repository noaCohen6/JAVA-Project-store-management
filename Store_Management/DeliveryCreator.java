package Store_Management;

public class DeliveryCreator implements Creator<DeliveryCompany> {

	public DeliveryCompany create(int key) {
		switch (key) {
		case 1:
			return new DHL("DHL", 33);
		case 2:
			return new FedEx("FedEx", 55);
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}
}
