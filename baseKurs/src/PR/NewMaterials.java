package PR;

public class NewMaterials {
	private String category;
	private int item;
	private String description;
	private String currency;
	private double price;

	NewMaterials(String category, String description, String currency, double price) {
		this.category = category;
		this.description = description;
		this.currency = currency;
		this.price = price;

	}

	public NewMaterials(String description, double price, String currency) {
		this.description = description;
		this.price = price;
		this.currency = currency;
		getDescription();
		getPrice();
		getCurency();
	}

	public int getItem() {
		return item;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getCurency() {
		return currency;
	}

	public double getPrice() {
		return price;
	}

}
