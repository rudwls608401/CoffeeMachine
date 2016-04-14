package coffeevendingmachine;

/**
 * 
 * @author kYEONGJIN ANN <rudwls608401 @ naver.com>
 * @since 2016.4.12
 *
 */

public class Charge {

	private int issamemoney = 0;// �ܵ��� ����� �Ž��������� �Ǵ��ϴ� ����
	public int checkmoney = 0;// ���� ������� check

	private int tenWon;// 10��
	private int fiftyWon;// 50��
	private int oneHundredWon;// 100��
	private int fiveHundredWon;// 500��
	private int oneThousandWon;// 1000��

	public int changetenWon = 0;// �ܵ� 10���� ����
	public int changefiftyWon = 0;// �ܵ� 50���� ����
	public int changeoneHundredWon = 0;// �ܵ� 100���� ����
	public int changefiveHundredWon = 0;// �ܵ� 500���� ����
	public int changeoneThousandWon = 0;// �ܵ� 1000���� ����

	/**
	 * Constructor of Charge
	 */
	public Charge() {
		setOneThousandWon(20);
		setFiveHundredWon(20);
		setOneHundredWon(20);
		setFiftyWon(20);
		setTenWon(20);
	}

	/**
	 * Initial each money number
	 */
	public void initCharge() {// ���� ���� �ʱ�ȭ
		setOneThousandWon(20);
		setFiveHundredWon(20);
		setOneHundredWon(20);
		setFiftyWon(20);
		setTenWon(20);
	}

	/**
	 * Initial each change number
	 */
	public void initChange() {// �ܵ� �ʱ�ȭ
		changetenWon = 0;
		changefiftyWon = 0;
		changeoneHundredWon = 0;
		changefiveHundredWon = 0;
		changeoneThousandWon = 0;
	}

	/**
	 * Refill money
	 * 
	 * @param money
	 *            refill object
	 * @param num
	 *            refill number
	 */
	public void addMoney(String money, int num) {
		if (money == "1000원") {
			setOneThousandWon(getOneThousandWon() + num);
		} else if (money == "500원") {
			setFiveHundredWon(getFiveHundredWon() + num);
		} else if (money == "100원") {
			setOneHundredWon(getOneHundredWon() + num);
		} else if (money == "50원") {
			setFiftyWon(getFiftyWon() + num);
		} else {
			setTenWon(getTenWon() + num);
		}
	}

	/**
	 * Decrease money number and Get change
	 * 
	 * @param money
	 *            input money
	 * @param price
	 *            coffee's total price
	 */
	public void changeMoney(int money, int price) {
		System.out.println(money + "  " + price);
		int change = money - price;
		int temp = change;
		int backup1 = getOneThousandWon();// 잔돈이 기계에 없을때 되돌리기 위해서 임시 저장해 놓는다.
		int backup2 = getFiveHundredWon();
		int backup3 = getOneHundredWon();
		int backup4 = getFiftyWon();
		int backup5 = getTenWon();
		while (change >= 1000 && this.getOneThousandWon() > 0) {
			change = change - 1000;
			issamemoney = issamemoney + 1000;
			setOneThousandWon(getOneThousandWon() - 1);
			changeoneThousandWon++;
		}
		while (change >= 500 && this.getFiveHundredWon() > 0) {
			change = change - 500;
			issamemoney = issamemoney + 500;
			setFiveHundredWon(getFiveHundredWon() - 1);
			changefiveHundredWon++;
		}
		while (change >= 100 && this.getOneHundredWon() > 0) {
			change = change - 100;
			issamemoney = issamemoney + 100;
			setOneHundredWon(getOneHundredWon() - 1);
			changeoneHundredWon++;
		}
		while (change >= 50 && this.getFiftyWon() > 0) {
			change = change - 50;
			issamemoney = issamemoney + 50;
			setFiftyWon(getFiftyWon() - 1);
			changefiftyWon++;
		}
		while (change >= 10 && this.getTenWon() > 0) {
			change = change - 10;
			issamemoney = issamemoney + 10;
			setTenWon(getTenWon() - 1);
			changetenWon++;
		}
		if (issamemoney != temp) {
			this.checkmoney = -1;
			setOneThousandWon(backup1);
			setFiveHundredWon(backup2);
			setOneHundredWon(backup3);
			setFiftyWon(backup4);
			setTenWon(backup5);
		}
		issamemoney = 0;
	}

	/**
	 * Get tenWon's number
	 * 
	 * @return tenWon's number
	 */
	public int getTenWon() {
		return tenWon;
	}

	/**
	 * Set tenWon's number
	 * 
	 * @param tenWon
	 *            the tenWon's number set
	 */
	public void setTenWon(int tenWon) {
		this.tenWon = tenWon;
	}

	/**
	 * Get fiftyWon's number
	 * 
	 * @return fiftyWon's number
	 */
	public int getFiftyWon() {
		return fiftyWon;
	}

	/**
	 * Set fiftyWon's number
	 * 
	 * @param fiftyWon
	 *            the fiftyWon's number set
	 */
	public void setFiftyWon(int fiftyWon) {
		this.fiftyWon = fiftyWon;
	}

	/**
	 * Get oneHundredWon's number
	 * 
	 * @return oneHundredWon's number
	 */
	public int getOneHundredWon() {
		return oneHundredWon;
	}

	/**
	 * Set oneHundredWon's number
	 * 
	 * @param oneHundredWon
	 *            the oneHundredWon's number set
	 */
	public void setOneHundredWon(int oneHundredWon) {
		this.oneHundredWon = oneHundredWon;
	}

	/**
	 * Get fiveHundredWon's number
	 * 
	 * @return fiveHundredWon's number
	 */
	public int getFiveHundredWon() {
		return fiveHundredWon;
	}

	/**
	 * Set FiveHundredWon's number
	 * 
	 * @param FiveHundredWon
	 *            the FiveHundredWon's number set
	 */
	public void setFiveHundredWon(int fiveHundredWon) {
		this.fiveHundredWon = fiveHundredWon;
	}

	/**
	 * Get OneThousandWon's number
	 * 
	 * @return OneThousandWon's number
	 */
	public int getOneThousandWon() {
		return oneThousandWon;
	}

	/**
	 * Set OneThousandWon's number
	 * 
	 * @param tenWon
	 *            the OneThousandWon's number set
	 */
	public void setOneThousandWon(int oneThousandWon) {
		this.oneThousandWon = oneThousandWon;
	}

}
