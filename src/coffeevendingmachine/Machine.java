package coffeevendingmachine;

/**
 * 
 * @author kYEONGJIN ANN <rudwls608401 @ naver.com>
 * @since 2016.4.12
 *
 */

public class Machine {

	private String coffee;
	public int money;
	public int price = 0;
	public int checkmoney = 0;

	Product product = new Product();
	Charge charge = new Charge();

	/**
	 * Set coffee, money and call productManage(), chargeManage()
	 * 
	 * @param coffee
	 *            the coffee set
	 * @param money
	 *            the money set
	 */
	public void machineManage(String coffee, int money) {
		this.coffee = coffee;
		this.money = money;
		productManage();
		chargeManage();
	}

	/**
	 * Manage product
	 * 
	 * Decrease product number
	 */
	public void productManage() {
		if (coffee == "Aspresso") {
			product.setAspressoNum(product.getAspressoNum() - 1);
		} else if (coffee == "Americano") {
			product.setAmericanoNum(product.getAmericanoNum() - 1);
		} else if (coffee == "Latte") {
			product.setLatteNum(product.getLatteNum() - 1);
		} else if (coffee == "Cappuccino") {
			product.setCappuccinoNum(product.getCappuccinoNum() - 1);
		} else {
			product.setMacchiatoNum(product.getMacchiatoNum() - 1);
		}
	}

	/**
	 * Manage charge
	 * 
	 * Add product's price
	 */
	public void chargeManage() {
		if (coffee == "Aspresso") {
			price = price + product.Aspresso_price;
		} else if (coffee == "Americano") {
			price = price + product.Americano_price;
		} else if (coffee == "Latte") {
			price = price + product.Latte_price;
		} else if (coffee == "Cappuccino") {
			price = price + product.Cappuccino_price;
		} else {
			price = price + product.Macchiato_price;
		}
	}

	/**
	 * call changeMoney
	 */
	public void startCal() {
		// System.out.println(money + " " + price);
		charge.changeMoney(money, price);
		if (charge.checkmoney == -1) {
			this.checkmoney = -1;
		} else {
			price = 0;
		}
	}

}
