package Store_Management;

public class ProductsCreator implements Creator<Products> {

	@Override
	public Products create(int key) {
		switch (key) {
		case 1:
			return new SoldThroughWebsite(null, null, 0, 0, 0, null, null, 0);
		case 2:
			return new SoldInStore(null, null, 0, 0, 0);
		case 3:
			return new SoldToWholesalers(null, null, 0, 0, 0);
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}
}
