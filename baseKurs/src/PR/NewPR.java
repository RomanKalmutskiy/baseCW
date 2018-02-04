package PR;

public class NewPR {

	private int line;
	private int itemCode;
	private String description;
	private String unit;
	private double price;
	private int quantity;
	private double amount;
	private String currency;

	NewPR() {
		setLine(line);
		setItemCode(itemCode);
		setDescription(description);
		setUnit(unit);
		setPrice(price);
		setQuantity(quantity);
		setAmount(amount);
		setCurrensy(currency);
	}

	private void setLine(int line) {
		this.line = line;

	}

	private void setCurrensy(String currency) {
		this.currency = currency;

	}

	private void setAmount(double amount) {
		this.amount = amount;

	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;

	}

	private void setPrice(double price) {
		this.price = price;

	}

	private void setUnit(String unit) {
		this.unit = unit;

	}

	private void setDescription(String description) {
		this.description = description;

	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;

	}

	public int getLine() {
		return line;
	}

	public int getItemCode() {
		return itemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}
}
