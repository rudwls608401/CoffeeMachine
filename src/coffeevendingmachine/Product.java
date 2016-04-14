package coffeevendingmachine;

/**
 * 
 * @author kYEONGJIN ANN <rudwls608401 @ naver.com>
 * @since 2016.4.12
 *
 */

public class Product {

	private int Aspresso_num = 10;
	private int Americano_num = 10;
	private int Latte_num = 10;
	private int Cappuccino_num = 10;
	private int Macchiato_num = 10;

	public int Aspresso_price = 2000;
	public int Americano_price = 1700;
	public int Latte_price = 2550;
	public int Cappuccino_price = 2300;
	public int Macchiato_price = 2460;

	/**
	 * Constructor of Product
	 */
	public Product() {
		setAspressoNum(10);
		setAmericanoNum(10);
		setLatteNum(10);
		setCappuccinoNum(10);
		setMacchiatoNum(10);
	}

	/**
	 * Refill product
	 * 
	 * @param money
	 *            refill object
	 * @param num
	 *            refill number
	 */
	public void addProduct(String coffee, int num) {
		if (coffee == "Aspresso") {
			setAspressoNum(getAspressoNum() + num);
		} else if (coffee == "Americano") {
			setAmericanoNum(getAmericanoNum() + num);
		} else if (coffee == "Latte") {
			setLatteNum(getLatteNum() + num);
		} else if (coffee == "Cappuccino") {
			setCappuccinoNum(getCappuccinoNum() + num);
		} else {
			setMacchiatoNum(getMacchiatoNum() + num);
		}
	}

	/**
	 * Get Aspresso's number
	 *
	 * @return Aspresso's number
	 */
	public int getAspressoNum() {
		return this.Aspresso_num;
	}

	/**
	 * Set Aspresso's number
	 *
	 * @param num
	 *            the Aspresso's number set
	 */
	public void setAspressoNum(int num) {
		this.Aspresso_num = num;
	}

	/**
	 * Get Americano's number
	 *
	 * @return Americano's number
	 */
	public int getAmericanoNum() {
		return this.Americano_num;
	}

	/**
	 * Set Americano's number
	 *
	 * @param num
	 *            the Americano's number set
	 */
	public void setAmericanoNum(int num) {
		this.Americano_num = num;
	}

	/**
	 * Get Latte's number
	 *
	 * @return Latte's number
	 */
	public int getLatteNum() {
		return this.Latte_num;
	}

	/**
	 * Set Latte's number
	 *
	 * @param num
	 *            the Latte's number set
	 */
	public void setLatteNum(int num) {
		this.Latte_num = num;
	}

	/**
	 * Get Cappuccino's number
	 *
	 * @return Cappuccino's number
	 */
	public int getCappuccinoNum() {
		return this.Cappuccino_num;
	}

	/**
	 * Set Cappuccino's number
	 *
	 * @param num
	 *            the Cappuccino's number set
	 */
	public void setCappuccinoNum(int num) {
		this.Cappuccino_num = num;
	}

	/**
	 * Get Macchiato's number
	 *
	 * @return Macchiato's number
	 */
	public int getMacchiatoNum() {
		return this.Macchiato_num;
	}

	/**
	 * Set Macchiato's number
	 *
	 * @param num
	 *            the Macchiato's number set
	 */
	public void setMacchiatoNum(int num) {
		this.Macchiato_num = num;
	}

}
