package Store_Management;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

public class Company implements OrderStockObserver, ProfitCalculation {

	private static Company instance = null;
	private TreeSet<Products> allProducts;
	private ArrayList<Order> allOrders;
	private ArrayList<DeliveryCompany> allDelivery;
	protected double profitCompany;

	private Company() {
		this.allProducts = new TreeSet<>();
		this.setAllOrders(new ArrayList<>());
		this.setAllDelivery(new ArrayList<>());
		this.profitCompany = 0;
	}

	public static Company getInstance() {
		if (instance == null) {
			instance = new Company();
		}
		return instance;
	}

	public ArrayList<DeliveryCompany> getAllDelivery() {
		return allDelivery;
	}

	public void setAllDelivery(ArrayList<DeliveryCompany> allDelivery) {
		this.allDelivery = allDelivery;
	}

	public TreeSet<Products> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(TreeSet<Products> allProducts) {
		this.allProducts = allProducts;
	}

	public ArrayList<Order> getAllOrders() {
		return allOrders;
	}

	public void setAllOrders(ArrayList<Order> allOrders) {
		this.allOrders = allOrders;
	}

	public boolean addProduct(Products p) {
		return allProducts.add(p);
	}

	public boolean addDeliveryCompany(DeliveryCompany d) {
		return allDelivery.add(d);
	}

	public boolean addOrder(String oCatalog, String pCatalog, int num, String name, int mobile) {
		Creator<Order> creator = new OrderCreator();
		Order o = null;
		Customer c = new Customer(name, mobile);
		for (Products ptemp : allProducts) {
			if (ptemp.getCatalogNumber().equals(pCatalog)) {
				if (ptemp instanceof SoldThroughWebsite) {
					o = creator.create(1);
				} else if (ptemp instanceof SoldInStore) {
					o = creator.create(2);
				} else {
					o = creator.create(3);
				}
				o.setProduct(ptemp);
				o.setCustomer(c);
				o.setNumOfProduct(num);
				o.setOrderCatalogNumber(oCatalog);
				allOrders.add(o);
				ptemp.addOrder(o);
				updateStockMakeOrder(pCatalog, num);
				return true;
			}
		}
		return false;
	}

	public boolean removeOrder() {
		int size = allOrders.size() - 1;
		for (Products pTemp : allProducts) {
			if (pTemp.getCatalogNumber().equals(allOrders.get(size).getProduct().getCatalogNumber())) {
				updateStockRemoveOrder(pTemp.getCatalogNumber(), allOrders.get(size).getNumOfProduct());
				pTemp.getOrders().remove(pTemp.getOrders().size() - 1);
				break;
			}
		}
		allOrders.remove(size);
		return true;
	}

	public boolean removeProduct(String item) {
		for (Products ptemp : allProducts) {
			if (ptemp.getCatalogNumber().equals(item)) {
				allProducts.remove(ptemp);
				return true;
			}

		}
		return false;
	}

	public boolean updateStock(String item, int num) {
		for (Products p : allProducts) {
			if (p.getCatalogNumber().equals(item)) {
				p.setStock(num);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(allProducts, other.allProducts);
	}

	@Override
	public boolean updateStockMakeOrder(String catalogNumber, int numUnits) {
		int currentStock, updatedStock;
		for (Products p : allProducts) {
			if (p.getCatalogNumber().equals(catalogNumber)) {
				currentStock = p.getStock();
				updatedStock = currentStock - numUnits;
				updateStock(catalogNumber, updatedStock);
				return true;
			}
		}
		return false;
	}

	public boolean updateStockRemoveOrder(String catalogNumber, int numUnits) {
		int currentStock, updatedStock;
		for (Products p : allProducts) {
			if (p.getCatalogNumber().equals(catalogNumber)) {
				currentStock = p.getStock();
				updatedStock = currentStock + numUnits;
				updateStock(catalogNumber, updatedStock);
				return true;
			}
		}
		return false;
	}

	public boolean createInvoice(String catalogNumber, int numInOrder) {
		for (Products pTemp : allProducts) {
			if (pTemp.getCatalogNumber().equals(catalogNumber) && pTemp instanceof SoldInStore) {
				SoldInStore p = (SoldInStore) pTemp;
				OrderInStore o = (OrderInStore) p.getOrders().get(p.getOrders().size() - 1);
				o.toString();
				return true;
			} else if (pTemp.getCatalogNumber().equals(catalogNumber) && pTemp instanceof SoldToWholesalers) {
				SoldToWholesalers p = (SoldToWholesalers) pTemp;
				OrderInWholesalers o = (OrderInWholesalers) p.getOrders().get(p.getOrders().size() - 1);
				o.toString();
				return true;
			}
		}
		return false;
	}

	@Override
	public double sumProfit() {
		profitCompany = 0;
		for (Products pTemp : allProducts) {
			profitCompany += pTemp.sumProfit();
		}
		return profitCompany;
	}

	public CompanyMemento saveToMemento() {

		return new CompanyMemento(allProducts, allOrders, allDelivery);
	}

	public void restoreFromMemento(CompanyMemento memento) {
		allProducts = memento.getAllProducts();
		allOrders = memento.getAllOrders();
		allDelivery = memento.getAllDelivery();
	}

	public String toStringallProducts() {
		StringBuffer sb = new StringBuffer("");
		int i = 0;
		Iterator<Products> itP = allProducts.iterator();
		while (itP.hasNext()) {
			sb.append("  " + (i + 1) + ". " + itP.next().toString() + "\n");
			i++;
		}
		sb.append("The profit company: " + sumProfit() + "₪\n");
		return sb.toString();
	}

	public String toStringallOrders() {
		StringBuffer sb = new StringBuffer("");
		int j = 0;
		Iterator<Order> itO = allOrders.iterator();
		while (itO.hasNext()) {
			sb.append("  " + (j + 1) + ". " + itO.next().toString() + "\n");
			j++;
		}
		return sb.toString();
	}
}
