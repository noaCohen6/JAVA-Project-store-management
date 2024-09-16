package Store_Management;

public class Customer {

	private String customer_name;
	private int mobile;

	public Customer(String customer_name, int mobile) {
		setCustomer_name(customer_name);
		setMobile(mobile);
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [customer_name=" + customer_name + ", mobile=" + mobile + "]";
	}
}
