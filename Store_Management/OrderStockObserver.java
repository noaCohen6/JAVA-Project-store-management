package Store_Management;

public interface OrderStockObserver {
	public boolean updateStockMakeOrder(String catalogNumber, int numUnits);

	public boolean updateStockRemoveOrder(String catalogNumber, int numUnits);

}
