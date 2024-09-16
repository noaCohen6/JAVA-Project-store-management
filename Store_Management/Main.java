package Store_Management;

import java.util.Scanner;

import Store_Management.SoldThroughWebsite.eDelivery;

public class Main {
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		StoredMemento cm = new StoredMemento();
		Company company = Company.getInstance();

		Creator<DeliveryCompany> creator = new DeliveryCreator();
		DeliveryCompany d;
		d = creator.create(1);
		company.addDeliveryCompany(d);
		d = creator.create(2);
		company.addDeliveryCompany(d);
		

		boolean fContinue = true;
		do {
			System.out.println("Choose one of the following options:");
			System.out.println("A - 4.1 import system");
			System.out.println("B - 4.2 Add Product");
			System.out.println("C - 4.3 Remove Product");
			System.out.println("D - 4.4 Update Product Stock");
			System.out.println("F - 4.5 Add Order");
			System.out.println("G - 4.6 Remove Order");
			System.out.println("H - 4.7 Print Product By Catalog Number");
			System.out.println("I - 4.8 Print Products and profit");
			System.out.println("J - 4.9 Print Orders Of Product and profit for Product");
			System.out.println("K - 4.10 BackUp System");
			System.out.println("E - Exit and export to binary file");

			System.out.println("Enter your choice ---> ");
			char choice = s.next().charAt(0);

			switch (Character.toUpperCase(choice)) {
			case 'A':
				importSystem(company);
				break;
			case 'B':
				addProduct(company);
				break;
			case 'C':
				removeProduct(company);
				break;
			case 'D':
				updateProductStock(company);
				break;
			case 'F':
				addOrderToList(company);
				break;
			case 'G':
				removeOrderFromList(company);
				break;
			case 'H':
				PrintProductByCatalogNumber(company);
				break;
			case 'I':
				System.out.println(company.toStringallProducts());
				break;
			case 'J':
				PrintOrdersOfProduct(company);
				break;
			case 'K':
				saveAndRestor(company, cm);
				break;
			case 'E':
				System.out.println("Bye bye");
				fContinue = false;
				break;
			default:
				System.out.println("Wrong option");
			}
			System.out.println();

		} while (fContinue);

	}

	public static void addProduct(Company company) {
		Creator<Products> creator = new ProductsCreator();
		Products p;

		System.out.println("Enter the number for the product do you want to create");
		System.out.println("1 - Sold Through Website");
		System.out.println("2 - Sold In Store");
		System.out.println("3 - Sold To Wholesalers");

		int num = s.nextInt();
		if (num < 1 || num > 3) {
			System.out.println("Wrong option");
			return;
		}

		System.out.println("Enter the catalog number of the product <Exemple: trh>");
		String temp = s.next();
		for (Products ptemp : company.getAllProducts()) {
			if (ptemp.getCatalogNumber().equals(temp)) {
				System.out.println("Error: The product already exists");
				return;
			}
		}
		p = creator.create(num);
		p.setCatalogNumber(temp);
		System.out.println("Enter the name of the product <Exemple: car>");
		p.setProduct_name(s.next());
		System.out.println("Enter the cost price of the product <Exemple: 3>");
		p.setCost_price(s.nextInt());
		System.out.println("Enter the selling price of the product <Exemple: 10>");
		p.setSelling_price(s.nextInt());
		System.out.println("Enter the stock of the product <Exemple: 15>");
		p.setStock(s.nextInt());
		if (num == 1) {
			System.out.println("Enter the type of delivery:");
			SoldThroughWebsite p1 = (SoldThroughWebsite) p;
			p1.printEnum();
			int choice = s.nextInt();
			p1.setTheDelivery(eDelivery.values()[choice - 1]);
		}
		if (company.addProduct(p) == true)
			System.out.println("The product was added");
		else
			System.out.println("The product was not added");
	}

	public static void removeProduct(Company company) {
		System.out.println(company.toStringallProducts());
		System.out.println("Enter the catalog number of the product that you want remove <Exemple: thr>");
		String temp = s.next();
		if (company.removeProduct(temp) == true)
			System.out.println("The product was removed");
		else
			System.out.println("The product was not removed");
		return;
	}

	public static void updateProductStock(Company company) {
		System.out.println(company.toStringallProducts());
		System.out.println("Enter the catalog number of the product that you want update stock <Exemple: thr>");
		String temp = s.next();
		System.out.println("Enter the number of the new stock <Exemple: 30>");
		int num = s.nextInt();
		if (company.updateStock(temp, num) == true)
			System.out.println("the stock was updated");
		else
			System.out.println("the stock was not updated");
	}

	public static void addOrderToList(Company company) {
		System.out.println("Enter the catalog number of the order <Exemple: d4>");
		String otemp = s.next();
		for (Order o : company.getAllOrders()) {
			if (o.getOrderCatalogNumber().equals(otemp)) {
				System.out.println("Error: The order already exists");
				return;
			}
		}
		System.out.println("Enter the type of the product that you want to order");
		System.out.println("1 - Sold Through Website");
		System.out.println("2 - Sold In Store");
		System.out.println("3 - Sold To Wholesalers");

		int numType = s.nextInt();
		if (numType < 1 || numType > 3) {
			System.out.println("Wrong option");
			return;
		}
		if (printProdutsByType(company, numType) == 0) {
			System.out.println("There are no products of this type\n");
			return;
		}

		System.out.println("Enter the catalog number of the product that you want to order <Exemple: thr>");
		String temp = s.next();
		System.out.println("Enter How many units do you want of the product <Exemple: 3>");
		int num = s.nextInt();
		for (Products ptemp : company.getAllProducts()) {
			if (ptemp.getCatalogNumber().equals(temp)) {
				if (ptemp.getStock() < num) {
					System.out.println("There is not enough stock of the product");
					return;
				} else
					break;
			}
		}
		System.out.println("Enter the customer name <Exemple: tali>");
		String tempName = s.next();
		System.out.println("Enter the customer number <Exemple: 339>");
		int tempNum = s.nextInt();
		if (company.addOrder(otemp, temp, num, tempName, tempNum) == true) {
			System.out.println("The order was added");
			System.out.println("The stock was updated\n");
			if (company.createInvoice(temp, company.getAllOrders().size()) == false) {
				orderWebsiteProduct(company, temp);
				System.out.println("You purchased from the website, the invoice was sent to your email\n");
			}

		} else
			System.out.println("The order was not added");

	}

	public static void removeOrderFromList(Company company) {
		if (company.getAllOrders().size() == 0) {
			System.out.println("There are no Orders");
			return;
		}
		company.removeOrder();
		System.out.println("The undo operation for orders from the product has ended");
	}

	public static void PrintProductByCatalogNumber(Company company) {
		System.out.println("Enter the catalog number of the product that you want to print <Exemple: thr>");
		String temp = s.next();
		for (Products ptemp : company.getAllProducts()) {
			if (ptemp.getCatalogNumber().equals(temp)) {
				System.out.println(ptemp.toString());
				break;
			}
		}
	}

	public static void PrintOrdersOfProduct(Company company) {
		System.out.println(
				"Enter the catalog number of the product that you want to print the orders for <Exemple: thr>");
		String temp = s.next();
		for (Products ptemp : company.getAllProducts()) {
			if (ptemp.getCatalogNumber().equals(temp)) {
				System.out.println(ptemp.toStringOrders());
				break;
			}
		}
	}

	public static void orderWebsiteProduct(Company company, String num) {
		SoldThroughWebsite p = null;
		for (Products ptemp : company.getAllProducts()) {
			if (ptemp.getCatalogNumber().equals(num)) {
				p = (SoldThroughWebsite) ptemp;
				System.out.println("Enter the dest countery <Exemple: USA>");
				p.setDest_countery(s.next());
				System.out.println("Enter product weight <Exemple: 4>");
				p.setProduct_weight(s.nextInt());
			}
		}
		if (p.getTheDelivery() == eDelivery.values()[0]) {
			onlyStandard(company, p);
		} else if (p.getTheDelivery() == eDelivery.values()[1]) {
			onlyExpress(company, p);
		} else {
			StandardAndExpress(company, p);
		}
	}

	public static void onlyStandard(Company company, SoldThroughWebsite p) {
		double min = company.getAllDelivery().get(0).ShippingFeesStandard(p);
		DeliveryCompany d = company.getAllDelivery().get(0);
		for (int i = 1; i < company.getAllDelivery().size(); i++) {
			if (company.getAllDelivery().get(i).ShippingFeesStandard(p) < min) {
				min = company.getAllDelivery().get(i).ShippingFeesStandard(p);
				d = company.getAllDelivery().get(i);
			}
		}
		OrderInWebsite o = (OrderInWebsite) p.getOrders().get(p.getOrders().size() - 1);
		o.setDeliveryCost(min);
		o.setDeliveryC(d);
		o.setDeliveryType(0);
	}

	public static void onlyExpress(Company company, SoldThroughWebsite p) {
		double min = company.getAllDelivery().get(0).ShippingFeesExpress(p);
		DeliveryCompany d = company.getAllDelivery().get(0);
		for (int i = 1; i < company.getAllDelivery().size(); i++) {
			if (company.getAllDelivery().get(i).ShippingFeesExpress(p) < min) {
				min = company.getAllDelivery().get(i).ShippingFeesExpress(p);
				d = company.getAllDelivery().get(i);
			}
		}
		OrderInWebsite o = (OrderInWebsite) p.getOrders().get(p.getOrders().size() - 1);
		o.setDeliveryCost(min);
		o.setDeliveryC(d);
		o.setDeliveryType(1);
	}

	public static void StandardAndExpress(Company company, SoldThroughWebsite p) {
		double min = company.getAllDelivery().get(0).ShippingFeesExpress(p);
		DeliveryCompany d = company.getAllDelivery().get(0);
		int type = 0;

		for (int i = 1; i < company.getAllDelivery().size(); i++) {
			if (company.getAllDelivery().get(i).ShippingFeesStandard(p) < min) {
				min = company.getAllDelivery().get(i).ShippingFeesStandard(p);
				d = company.getAllDelivery().get(i);
				type = 0;
			}
		}
		for (int i = 0; i < company.getAllDelivery().size(); i++) {
			if (company.getAllDelivery().get(i).ShippingFeesExpress(p) < min) {
				min = company.getAllDelivery().get(i).ShippingFeesExpress(p);
				d = company.getAllDelivery().get(i);
				type = 1;
			}
		}
		OrderInWebsite o = (OrderInWebsite) p.getOrders().get(p.getOrders().size() - 1);
		o.setDeliveryCost(min);
		o.setDeliveryC(d);
		o.setDeliveryType(type);
	}

	public static void saveAndRestor(Company company, StoredMemento cm) {

		System.out.println("Enter the number for save or restor the system");
		System.out.println("1 - Save the system");
		System.out.println("2 - Restor the system");
		int choice = s.nextInt();
		if (choice == 1) {
			CompanyMemento m = company.saveToMemento();
			cm.setMemento(m);
			System.out.println("The system has been saved");

		} else {
			CompanyMemento m = cm.getMemento();
			if (m == null) {
				System.out.println("You didnt save the system");
			} else {
				company.restoreFromMemento(m);
				System.out.println("The system have been restored");
			}
		}
	}

	public static int printProdutsByType(Company company, int num) {
		int count = 0;
		if (num == 1) {
			for (Products ptemp : company.getAllProducts()) {
				if (ptemp instanceof SoldThroughWebsite) {
					System.out.println(ptemp.toString());
					count++;
				}
			}
		} else if (num == 2) {
			for (Products ptemp : company.getAllProducts()) {
				if (ptemp instanceof SoldInStore) {
					System.out.println(ptemp.toString());
					count++;
				}
			}
		} else {
			for (Products ptemp : company.getAllProducts()) {
				if (ptemp instanceof SoldToWholesalers) {
					System.out.println(ptemp.toString());
					count++;
				}
			}
		}
		return count;
	}

	public static void importSystem(Company company) {
		Creator<Products> creatorp = new ProductsCreator();
		Products p1;
		Products p2;
		Products p3;
		Products p4;
		Products p5;
		Products p6;
		Products p7;
		Products p8;
		Products p9;
		
		p1 = creatorp.create(1);
		p1.setCatalogNumber("aaa");
		p1.setProduct_name("car");
		p1.setCost_price(3000);
		p1.setSelling_price(6000);
		p1.setStock(33);
		
		SoldThroughWebsite p1a = (SoldThroughWebsite) p1;
		p1a.setDest_countery("Paris");
		p1a.setProduct_weight(1000);
		p1a.setTheDelivery(eDelivery.StandardShipping);
		company.addProduct(p1);
		
		p2 = creatorp.create(1);
		p2.setCatalogNumber("bbb");
		p2.setProduct_name("phoen");
		p2.setCost_price(100);
		p2.setSelling_price(900);
		p2.setStock(66);
		SoldThroughWebsite p2b = (SoldThroughWebsite) p2;
		p2b.setDest_countery("Tel Aviv");
		p2b.setProduct_weight(1);
		p2b.setTheDelivery(eDelivery.ExpressShipping);
		company.addProduct(p2);

		p3 = creatorp.create(1);
		p3.setCatalogNumber("ccc");
		p3.setProduct_name("broom");
		p3.setCost_price(22);
		p3.setSelling_price(33);
		p3.setStock(88);
		SoldThroughWebsite p3c = (SoldThroughWebsite) p3;
		p3c.setDest_countery("USA");
		p3c.setProduct_weight(2);
		p3c.setTheDelivery(eDelivery.StandardShippingAndExpressShipping);
		company.addProduct(p3);

		p4 = creatorp.create(2);
		p4.setCatalogNumber("ddd");
		p4.setProduct_name("doll");
		p4.setCost_price(12);
		p4.setSelling_price(23);
		p4.setStock(234);
		company.addProduct(p4);

		p5 = creatorp.create(2);
		p5.setCatalogNumber("eee");
		p5.setProduct_name("shirt");
		p5.setCost_price(24);
		p5.setSelling_price(65);
		p5.setStock(123);
		company.addProduct(p5);

		p6 = creatorp.create(2);
		p6.setCatalogNumber("fff");
		p6.setProduct_name("dress");
		p6.setCost_price(14);
		p6.setSelling_price(87);
		p6.setStock(190);
		company.addProduct(p6);

		p7 = creatorp.create(3);
		p7.setCatalogNumber("ggg");
		p7.setProduct_name("computer");
		p7.setCost_price(1500);
		p7.setSelling_price(4000);
		p7.setStock(50);
		company.addProduct(p7);

		p8 = creatorp.create(3);
		p8.setCatalogNumber("hhh");
		p8.setProduct_name("shose");
		p8.setCost_price(100);
		p8.setSelling_price(300);
		p8.setStock(250);
		company.addProduct(p8);

		p9 = creatorp.create(3);
		p9.setCatalogNumber("iii");
		p9.setProduct_name("earring");
		p9.setCost_price(50);
		p9.setSelling_price(120);
		p9.setStock(300);
		company.addProduct(p9);
		

		company.addOrder("a1", p1.getCatalogNumber(), 1, "Noa", 123);
		onlyStandard(company, p1a);
		company.addOrder("a2", p1.getCatalogNumber(), 2, "Eden", 555);
		onlyStandard(company, p1a);
		company.addOrder("a3", p1.getCatalogNumber(), 1, "Omer", 666);
		onlyStandard(company, p1a);
		
		company.addOrder("b1", p2.getCatalogNumber(), 23, "Adir", 345);
		onlyExpress(company, p2b);
		company.addOrder("b2", p2.getCatalogNumber(), 4, "Almog", 986);
		onlyExpress(company, p2b);
		company.addOrder("b3", p2.getCatalogNumber(), 2, "Adi", 234);
		onlyExpress(company, p2b);
		
		company.addOrder("c1", p3.getCatalogNumber(), 12, "Noa", 123);
		StandardAndExpress(company, p3c);
		company.addOrder("c2", p3.getCatalogNumber(), 22, "Eden", 555);
		StandardAndExpress(company, p3c);
		company.addOrder("c3", p3.getCatalogNumber(), 5, "Adi", 234);
		StandardAndExpress(company, p3c);
		
		company.addOrder("d1", p4.getCatalogNumber(), 50, "Omer", 666);
		company.addOrder("d2", p4.getCatalogNumber(), 21, "Amit", 222);
		company.addOrder("d3", p4.getCatalogNumber(), 11, "Noa", 123);
		
		company.addOrder("e1", p5.getCatalogNumber(), 33, "Almog", 986);
		company.addOrder("e2", p5.getCatalogNumber(), 14, "Omer", 666);
		company.addOrder("e3", p5.getCatalogNumber(), 19, "Noa", 123);
		
		company.addOrder("f1", p6.getCatalogNumber(), 23, "Almog", 986);
		company.addOrder("f2", p6.getCatalogNumber(), 2, "Adi", 234);
		company.addOrder("f3", p6.getCatalogNumber(), 3, "Eden", 555);
		
		company.addOrder("g1", p7.getCatalogNumber(), 6, "Adir", 345);
		company.addOrder("g2", p7.getCatalogNumber(), 2, "Lior", 777);
		company.addOrder("g3", p7.getCatalogNumber(), 3, "Bar", 147);
		
		company.addOrder("h1", p8.getCatalogNumber(), 100, "Adir", 345);
		company.addOrder("h2", p8.getCatalogNumber(), 80, "Lior", 777);
		company.addOrder("h3", p8.getCatalogNumber(), 11, "Bar", 147);
		
		company.addOrder("i1", p9.getCatalogNumber(), 100, "Adir", 345);
		company.addOrder("i2", p9.getCatalogNumber(), 20, "Noa", 123);
		company.addOrder("i3", p9.getCatalogNumber(), 47, "Omer", 666);
	}
}
