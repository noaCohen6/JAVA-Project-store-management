package Store_Management;

public class OrderCreator implements Creator<Order> {

	@Override
	public Order create(int key) {
		switch (key) {
		case 1:
			return new OrderInWebsite(null, null, null, 0, null);
		case 2:
			return new OrderInStore(null, null, null, 0);
		case 3:
			return new OrderInWholesalers(null, null, null, 0);
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}
}
