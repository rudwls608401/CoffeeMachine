package coffeevendingmachine;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.*;

/**
 * 
 * @author kYEONGJIN ANN <rudwls608401 @ naver.com>
 * @since 2016.4.12
 *
 */

public class CoffeeVendingMachineFrame extends JFrame {

	private JTextField textField = null;
	static public Machine machine = new Machine();
	private int money = 0;// 투입한 돈
	private int totalmoney = 0;// 돈을 더 추가할 때 쓰인다.
	private int price = 0;// 커피의 가격
	private int sum = 0;// 커피를 여러개 눌렀을때 총 가격
	private int temp = 0;// 투입한 돈 잠시 저장
	private int stockmoney = 0;// 투입한 돈의 총량
	public int check = 0;// 돈에 비해서 커피 주문을 많이 했는지 확인
	private int returnmoney = -1;// 커피주문을 안했는데 돈을 다시 받고싶을 때 확인

	private ImageIcon AspressoIcon = new ImageIcon("images/Aspresso.jpg");
	private ImageIcon AmericanoIcon = new ImageIcon("images/Americano.jpg");
	private ImageIcon LatteIcon = new ImageIcon("images/Latte.jpg");
	private ImageIcon CappuccinoIcon = new ImageIcon("images/Cappuccino.jpg");
	private ImageIcon MacchiatoIcon = new ImageIcon("images/Macchiato.jpg");
	private ImageIcon coffeeIcon = new ImageIcon("images/vendingmachine.jpg");

	private JLabel AspressoImageLabel = new JLabel();
	private JLabel AmericanoImageLabel = new JLabel();
	private JLabel LatteImageLabel = new JLabel();
	private JLabel CappuccinoImageLabel = new JLabel();
	private JLabel MacchiatoImageLabel = new JLabel();
	private JLabel machineImageLabel = new JLabel(); // 커피의 이미지를 출력할 이미지 레이블

	private JLabel lblMoney1000;
	private JLabel lblMoney500;
	private JLabel lblMoney100;
	private JLabel lblMoney50;
	private JLabel lblMoney10;

	private JPanel panelCoffee;
	private JButton btnAspresso;
	private JButton btnAmericano;
	private JButton btnLatte;
	private JButton btnCappuccino;
	private JButton btnMacchiato;
	private JButton btnCoffeeReset;

	private JPanel panelMoney;
	private JLabel lbl1000;
	private JLabel lbl500;
	private JLabel lbl100;
	private JLabel lbl50;
	private JLabel lbl10;
	private JButton btnMoneyReset;

	private JPanel panel_1;
	private JPanel panelPriceCoffee;
	private JLabel lblPriceCoffee;
	private JLabel lblViewPriceCoffee;

	private JPanel panel_2;
	private JPanel panelInputMoney;
	private JLabel lblNewLabel_2;

	private JPanel panel_3;
	private JPanel panelChange;
	private JLabel lblChange;
	private JPanel panelViewChange;
	private Box verticalBox;
	private JLabel lblChange1000;
	private JLabel lblChange500;
	private JLabel lblChange100;
	private JLabel lblChange50;
	private JLabel lblChange10;
	private JLabel lblViewChange1000;
	private JLabel lblViewChange500;
	private JLabel lblViewChange100;
	private JLabel lblViewChange50;
	private JLabel lblViewChange10;
	private JButton btnEND;
	private JLabel lblEqual;
	private JLabel lblTotalMoney;
	private JComboBox comboBox1;// coffee refill combo's name
	static public JComboBox comboBox2;// coffee refill combo's quantity
	private JComboBox comboBox3;// money refill combo's name
	static public JComboBox comboBox4;// money refill combo's number
	private Vector<ProductRefill> pr = new Vector<ProductRefill>();// combo box에
																	// 넣을
																	// product
																	// refill내용
	static public Vector<Quantity> prq = new Vector<Quantity>();// combo box에 넣을
																// product
																// refill내용
	private Vector<MoneyRefill> mr = new Vector<MoneyRefill>();// combo box에 넣을
																// money
																// refill내용
	static public Vector<Number> mrn = new Vector<Number>();// combo box에 넣을
	static public JFrame frm; // product refill내용
	static public JFrame frm3; // money refill내용

	/**
	 * Create the frame.
	 */
	public CoffeeVendingMachineFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(700, 700);
		setResizable(false); // 사용자가 프레임의 크기 조절할 수 없도록 설정

		// ****************************************<<<<<<<<Coffee>>>>>>>>****************************************//

		panelCoffee = new JPanel();
		getContentPane().add(panelCoffee, BorderLayout.NORTH);
		panelCoffee.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		machineImageLabel.setLocation(10, 100);
		machineImageLabel.setSize(coffeeIcon.getIconWidth(), coffeeIcon.getIconHeight());
		machineImageLabel.setIcon(coffeeIcon);
		getContentPane().add(machineImageLabel);

		AspressoImageLabel.setLocation(350, 100);
		AspressoImageLabel.setSize(AspressoIcon.getIconWidth(), AspressoIcon.getIconHeight());
		getContentPane().add(AspressoImageLabel);

		AmericanoImageLabel.setLocation(350, 100);
		AmericanoImageLabel.setSize(AmericanoIcon.getIconWidth(), AmericanoIcon.getIconHeight());
		getContentPane().add(AmericanoImageLabel);

		LatteImageLabel.setLocation(350, 100);
		LatteImageLabel.setSize(LatteIcon.getIconWidth(), LatteIcon.getIconHeight());
		getContentPane().add(LatteImageLabel);

		CappuccinoImageLabel.setLocation(350, 100);
		CappuccinoImageLabel.setSize(CappuccinoIcon.getIconWidth(), CappuccinoIcon.getIconHeight());
		getContentPane().add(CappuccinoImageLabel);

		MacchiatoImageLabel.setLocation(350, 100);
		MacchiatoImageLabel.setSize(MacchiatoIcon.getIconWidth(), MacchiatoIcon.getIconHeight());
		getContentPane().add(MacchiatoImageLabel);

		btnAspresso = new JButton("Aspresso\r\n(2.0)");
		btnAspresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnmoney = -1;
				setImage();
				if (machine.product.getAspressoNum() == 0) {// 예외처리->커피가 없는데
															// 주문했을때
					error("커피가 부족합니다. 채워주세요.");
				} else {
					if (totalmoney < machine.product.Aspresso_price) {// 예외처리->주문전
																		// 투입한
																		// 돈보다
																		// 커피가격이
																		// 더 많이
																		// 나갈때
						error("돈을 더 넣어주세요.");
					} else {
						if (totalmoney < machine.product.Aspresso_price) {// 예외처리->주문을
																			// 하는
																			// 도중에
																			// 투입한
																			// 돈보다
																			// 커피가격이
																			// 더
																			// 많이
																			// 나갈때
							error("돈이 부족해서 더이상 주문 하실 수 없습니다.");
						} else {
							returnmoney = 0;
							machine.machineManage("Aspresso", stockmoney);
							sum = price + machine.product.Aspresso_price;// 총
																			// 커피의
																			// 가격
							lblViewPriceCoffee.setText(String.format("%d", machine.product.Aspresso_price));
							price = sum;
							AspressoImageLabel.setIcon(AspressoIcon);
							temp = temp - machine.product.Aspresso_price;
							totalmoney = totalmoney - machine.product.Aspresso_price;
							lblTotalMoney.setText(String.format("%d", temp));
							textField.setText("");
						}
					}
				}
			}
		});
		panelCoffee.add(btnAspresso);

		btnAmericano = new JButton("Americano(1.7)");
		btnAmericano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setImage();
				if (machine.product.getAmericanoNum() == 0) {
					error("커피가 부족합니다. 채워주세요.");
				} else {
					if (totalmoney < machine.product.Americano_price) {
						error("돈을 더 넣어주세요.");
					} else {
						if (totalmoney < machine.product.Americano_price) {
							error("돈이 부족해서 더이상 주문 하실 수 없습니다.");
						} else {
							returnmoney = 0;
							machine.machineManage("Americano", stockmoney);
							sum = price + machine.product.Americano_price;
							lblViewPriceCoffee.setText(String.format("%d", machine.product.Americano_price));
							price = sum;
							AmericanoImageLabel.setIcon(AmericanoIcon);
							temp = temp - machine.product.Americano_price;
							totalmoney = totalmoney - machine.product.Americano_price;
							lblTotalMoney.setText(String.format("%d", temp));
							textField.setText("");
						}
					}
				}
			}
		});
		panelCoffee.add(btnAmericano);

		btnLatte = new JButton("Latte(2.55)");
		btnLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnmoney = -1;
				setImage();
				if (machine.product.getLatteNum() == 0) {
					error("커피가 부족합니다. 채워주세요.");
				} else {
					if (totalmoney < machine.product.Latte_price) {
						error("돈을 더 넣어주세요.");
					} else {
						if (totalmoney < machine.product.Latte_price) {
							error("돈이 부족해서 더이상 주문 하실 수 없습니다.");
						} else {
							machine.machineManage("Latte", stockmoney);
							sum = price + machine.product.Latte_price;
							lblViewPriceCoffee.setText(String.format("%d", machine.product.Latte_price));
							price = sum;
							LatteImageLabel.setIcon(LatteIcon);
							temp = temp - machine.product.Latte_price;
							totalmoney = totalmoney - machine.product.Latte_price;
							lblTotalMoney.setText(String.format("%d", temp));
							textField.setText("");
						}
					}
				}
			}
		});
		panelCoffee.add(btnLatte);

		btnCappuccino = new JButton("Cappuccino(2.3)");
		btnCappuccino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnmoney = -1;
				setImage();
				if (machine.product.getCappuccinoNum() == 0) {
					error("커피가 부족합니다. 채워주세요.");
				} else {
					if (totalmoney < machine.product.Cappuccino_price) {
						error("돈을 더 넣어주세요.");
					} else {
						if (totalmoney < machine.product.Cappuccino_price) {
							error("돈이 부족해서 더이상 주문 하실 수 없습니다.");
						} else {
							returnmoney = 0;
							machine.machineManage("Cappuccino", stockmoney);
							sum = price + machine.product.Cappuccino_price;
							lblViewPriceCoffee.setText(String.format("%d", machine.product.Cappuccino_price));
							price = sum;
							CappuccinoImageLabel.setIcon(CappuccinoIcon);
							temp = temp - machine.product.Cappuccino_price;
							totalmoney = totalmoney - machine.product.Cappuccino_price;
							lblTotalMoney.setText(String.format("%d", temp));
							textField.setText("");
						}
					}
				}
			}
		});
		panelCoffee.add(btnCappuccino);

		btnMacchiato = new JButton("Macchiato(2.46)");
		btnMacchiato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnmoney = -1;
				setImage();
				if (machine.product.getMacchiatoNum() == 0) {
					error("커피가 부족합니다. 채워주세요.");
				} else {
					if (totalmoney < machine.product.Macchiato_price) {
						error("돈을 더 넣어주세요.");
					} else {
						if (totalmoney < machine.product.Macchiato_price) {
							error("돈이 부족해서 더이상 주문 하실 수 없습니다.");
						} else {
							returnmoney = 0;
							machine.machineManage("Macchiato", stockmoney);
							sum = price + machine.product.Macchiato_price;
							lblViewPriceCoffee.setText(String.format("%d", machine.product.Macchiato_price));
							price = sum;
							MacchiatoImageLabel.setIcon(MacchiatoIcon);
							temp = temp - machine.product.Macchiato_price;
							totalmoney = totalmoney - machine.product.Macchiato_price;
							lblTotalMoney.setText(String.format("%d", temp));
							textField.setText("");
						}
					}
				}
			}
		});
		panelCoffee.add(btnMacchiato);

		btnCoffeeReset = new JButton("Refill");
		btnCoffeeReset.addActionListener(new ActionListener() {// 커피의 수량을
																// refill시킨다.
			public void actionPerformed(ActionEvent e) {
				frm = new JFrame("Coffee");
				frm.setBounds(120, 120, 350, 150);
				frm.getContentPane().setLayout(new GridLayout(0, 1));

				pr.add(new ProductRefill(""));
				pr.add(new ProductRefill("Aspresso"));
				pr.add(new ProductRefill("Americano"));
				pr.add(new ProductRefill("Latte"));
				pr.add(new ProductRefill("Cappuccino"));
				pr.add(new ProductRefill("Macchiato"));

				JLabel label1 = new JLabel("리필할 커피를 선택해주세요.");
				comboBox1 = new JComboBox(pr);
				comboBox1.setMaximumRowCount(6);
				frm.getContentPane().add(label1);
				frm.getContentPane().add(comboBox1);
				frm.setVisible(true);
				frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
				dcbm.addElement("");
				dcbm.addElement("Aspresso");
				dcbm.addElement("Americano");
				dcbm.addElement("Latte");
				dcbm.addElement("Cappuccino");
				dcbm.addElement("Macchiato");
				comboBox1.setModel(dcbm);

				comboBox1.addItemListener(new ProductChoiceHandler());
			}
		});
		panelCoffee.add(btnCoffeeReset);

		// ****************************************<<<<<<<<Money>>>>>>>>****************************************//

		panelMoney = new JPanel();
		getContentPane().add(panelMoney, BorderLayout.SOUTH);
		panelMoney.setLayout(new GridLayout(2, 3, 5, 5));

		lbl1000 = new JLabel("1000원");
		panelMoney.add(lbl1000);

		lbl500 = new JLabel("500원");
		panelMoney.add(lbl500);

		lbl100 = new JLabel("100원");
		panelMoney.add(lbl100);

		lbl50 = new JLabel("50원");
		panelMoney.add(lbl50);

		lbl10 = new JLabel("10원");
		panelMoney.add(lbl10);

		btnMoneyReset = new JButton("Refill");// 돈의 수량을 refill시킨다.
		btnMoneyReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frm3 = new JFrame("Money");
				frm3.setBounds(120, 120, 350, 150);
				frm3.getContentPane().setLayout(new GridLayout(0, 1));

				mr.add(new MoneyRefill(""));
				mr.add(new MoneyRefill("1000원"));
				mr.add(new MoneyRefill("500원"));
				mr.add(new MoneyRefill("100원"));
				mr.add(new MoneyRefill("50원"));
				mr.add(new MoneyRefill("10원"));

				JLabel label3 = new JLabel("리필할 돈을 선택해주세요.");
				comboBox3 = new JComboBox(mr);
				comboBox3.setMaximumRowCount(6);
				// comboBox3.addItemListener(new MoneyChoiceHandler());
				frm3.getContentPane().add(label3);
				frm3.getContentPane().add(comboBox3);
				frm3.setVisible(true);
				frm3.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
				dcbm.addElement("");
				dcbm.addElement("1000원");
				dcbm.addElement("500원");
				dcbm.addElement("100원");
				dcbm.addElement("50원");
				dcbm.addElement("10원");
				comboBox3.setModel(dcbm);

				comboBox3.addItemListener(new MoneyChoiceHandler());

				// machine.charge.initCharge();// 돈 초기화
				updateMoney();// 돈이 초기화된 것을 보인다.
				machine.charge.initChange();// 잔돈 초기화
				updateChange();// 잔돈이 초기화된 것을 보인다.

				machine.charge.checkmoney = 0;// 돈이 부족하지 않은지 판별해주는 flag역할 초기화.
				machine.checkmoney = 0;// 돈이 부족하지 않은지 판별해주는 flag역할 초기화.

			}
		});
		panelMoney.add(btnMoneyReset);

		lblMoney1000 = new JLabel("  ");
		panelMoney.add(lblMoney1000);

		lblMoney500 = new JLabel("  ");
		panelMoney.add(lblMoney500);

		lblMoney100 = new JLabel("  ");
		panelMoney.add(lblMoney100);

		lblMoney50 = new JLabel("  ");
		panelMoney.add(lblMoney50);

		lblMoney10 = new JLabel("  ");
		panelMoney.add(lblMoney10);

		btnEND = new JButton("주문끝!!");
		btnEND.addActionListener(new ActionListener() {// '주문끝!!'버튼을 눌렀을떄 수행
			public void actionPerformed(ActionEvent arg0) {
				// machine.startCal();// 투입된 돈과 총 커피가격을 가지고 돈을 감소시키고, 잔돈을 출력한다.
				if (machine.checkmoney == -1) {// 예외처리->돈이 부족했을 떄
					machine.startCal();
					error("돈이 부족합니다. 채워주세요.");
				} else {// 새로운 주문을 받을지 유무를 물어본다.
					if (returnmoney == -1) {// 커피주문을 안했는데 들어있는 돈을 받고 싶을때
						machine.money = temp;
						machine.price = 0;
						machine.startCal();
					} else {
						machine.startCal();
					}
					restart("새로운 주문을 받겠습니까?");
				}
				updateMoney();
				updateChange();
			}
		});
		panelMoney.add(btnEND);

		// ****************************************<<<<<<<<CoffeePrice>>>>>>>>****************************************//

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panelPriceCoffee = new JPanel();
		panel_1.add(panelPriceCoffee, BorderLayout.SOUTH);
		panelPriceCoffee.setLayout(new GridLayout(0, 2, 0, 0));

		lblPriceCoffee = new JLabel("커피가격          ");
		lblPriceCoffee.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPriceCoffee.add(lblPriceCoffee);

		lblViewPriceCoffee = new JLabel("  ");
		panelPriceCoffee.add(lblViewPriceCoffee);

		// ****************************************<<<<<<<<InputMoney>>>>>>>>****************************************//

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		panelInputMoney = new JPanel();
		panel_2.add(panelInputMoney, BorderLayout.SOUTH);

		lblNewLabel_2 = new JLabel("지폐투입구");
		panelInputMoney.add(lblNewLabel_2);

		textField = new JTextField();// 돈 투입구
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				money = Integer.parseInt(textField.getText());// 돈이 string으로
																// 저장이되는데 int로
																// 변환한다.
				stockmoney = stockmoney + money;
				lblTotalMoney.setText(String.format("%d", temp + money));
				totalmoney = temp + money;
				temp = totalmoney;
				// price = 0;
				textField.setText("");
				updateMoney();
				returnmoney = -1;
			}
		});
		panelInputMoney.add(textField);
		textField.setColumns(10);

		lblEqual = new JLabel("=");
		panelInputMoney.add(lblEqual);

		lblTotalMoney = new JLabel("        ");
		lblTotalMoney.setHorizontalAlignment(SwingConstants.TRAILING);
		panelInputMoney.add(lblTotalMoney);

		// ****************************************<<<<<<<<Change>>>>>>>>****************************************//

		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		panelChange = new JPanel();
		panel_3.add(panelChange, BorderLayout.EAST);
		panelChange.setLayout(new BorderLayout(0, 0));

		lblChange = new JLabel("***\uC794\uB3C8***");
		panelChange.add(lblChange, BorderLayout.NORTH);

		panelViewChange = new JPanel();
		panelChange.add(panelViewChange, BorderLayout.CENTER);
		panelViewChange.setLayout(null);

		verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(0, 0, 90, 479);
		panelViewChange.add(verticalBox);

		lblChange1000 = new JLabel("1000원");
		verticalBox.add(lblChange1000);

		lblViewChange1000 = new JLabel("  ");
		verticalBox.add(lblViewChange1000);

		lblChange500 = new JLabel("500원");
		verticalBox.add(lblChange500);

		lblViewChange500 = new JLabel("  ");
		verticalBox.add(lblViewChange500);

		lblChange100 = new JLabel("100원");
		verticalBox.add(lblChange100);

		lblViewChange100 = new JLabel("  ");
		verticalBox.add(lblViewChange100);

		lblChange50 = new JLabel("50원");
		verticalBox.add(lblChange50);

		lblViewChange50 = new JLabel("  ");
		verticalBox.add(lblViewChange50);

		lblChange10 = new JLabel("10원");
		verticalBox.add(lblChange10);

		lblViewChange10 = new JLabel("  ");
		verticalBox.add(lblViewChange10);

		// ****************************************<<<<<<<<Image>>>>>>>>****************************************//

	}

	/**
	 * Change money label view
	 */
	public void updateMoney() {// update된 돈을 보인다.
		lblMoney1000.setText(String.format("%d", machine.charge.getOneThousandWon()));
		lblMoney500.setText(String.format("%d", machine.charge.getFiveHundredWon()));
		lblMoney100.setText(String.format("%d", machine.charge.getOneHundredWon()));
		lblMoney50.setText(String.format("%d", machine.charge.getFiftyWon()));
		lblMoney10.setText(String.format("%d", machine.charge.getTenWon()));
	}

	/**
	 * Change change label view
	 */
	public void updateChange() {// update된 잔돈을 보인다.
		lblViewChange1000.setText(String.format("%d", machine.charge.changeoneThousandWon));
		lblViewChange500.setText(String.format("%d", machine.charge.changefiveHundredWon));
		lblViewChange100.setText(String.format("%d", machine.charge.changeoneHundredWon));
		lblViewChange50.setText(String.format("%d", machine.charge.changefiftyWon));
		lblViewChange10.setText(String.format("%d", machine.charge.changetenWon));
	}

	/**
	 * Show error message
	 * 
	 * @param msg
	 *            error message
	 */
	public void error(String msg) { // 경고창을 출력하는 메소드
		JOptionPane.showMessageDialog(null, msg, "커피 자판기 경고", JOptionPane.INFORMATION_MESSAGE);
		return;
	}

	/**
	 * Show new order message
	 * 
	 * @param msg
	 *            order message
	 */
	public void restart(String msg) { // 새로운 주문을 받기 위한 메소드
		int put = JOptionPane.showConfirmDialog(null, msg, "다음 주문", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null);
		if (put == JOptionPane.YES_OPTION) {// 예(Y)를 눌렀을때 if문이 수행이된다.
			machine.charge.initChange();
			updateChange();
			price = 0;
			sum = 0;
			totalmoney = 0;
			temp = 0;
			stockmoney = 0;
			lblViewPriceCoffee.setText(String.format("%d", sum));
			lblTotalMoney.setText(String.format("%d", 0));
			textField.setText("");
		} else {
			System.exit(0);
		}
		return;
	}

	/**
	 * Set image, image is disappear
	 */
	public void setImage() {
		AspressoImageLabel.setIcon(null);
		AmericanoImageLabel.setIcon(null);
		LatteImageLabel.setIcon(null);
		CappuccinoImageLabel.setIcon(null);
		MacchiatoImageLabel.setIcon(null);
	}

	/**
	 * Launch the application.
	 */
	static public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeeVendingMachineFrame frame = new CoffeeVendingMachineFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

/**
 * 
 */
class ProductRefill {
	String p;

	public ProductRefill(String p) {
		this.p = p;
	}

	public String toString() {
		return p;
	}
}

/**
 * 
 */
class Quantity {
	String q;

	public Quantity(String q) {
		this.q = q;
	}

	public String toString() {
		return q;
	}
}

/**
 * 
 */
class MoneyRefill {
	String m;

	public MoneyRefill(String m) {
		this.m = m;
	}

	public String toString() {
		return m;
	}
}

/**
 * 
 */
class Number {
	String n;

	public Number(String n) {
		this.n = n;
	}

	public String toString() {
		return n;
	}
}

/**
 * ProductChoiceHandler -> Fill Combo Box
 */
class ProductChoiceHandler implements ItemListener {
	static public int size = 0;
	static public String x;
	CoffeeVendingMachineFrame f;
	private JFrame frm2 = new JFrame("Quantity");

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			x = String.valueOf(e.getItem());
			if (x == "Aspresso") {
				size = 10 - CoffeeVendingMachineFrame.machine.product.getAspressoNum();
			} else if (x == "Americano") {
				size = 10 - CoffeeVendingMachineFrame.machine.product.getAmericanoNum();
			} else if (x == "Latte") {
				size = 10 - CoffeeVendingMachineFrame.machine.product.getLatteNum();
			} else if (x == "Cappuccino") {
				size = 10 - CoffeeVendingMachineFrame.machine.product.getCappuccinoNum();
			} else if (x == "Macchiato") {
				size = 10 - CoffeeVendingMachineFrame.machine.product.getMacchiatoNum();
			}
			frm2.setBounds(120, 120, 350, 150);
			frm2.setLayout(new GridLayout(0, 1));
			int cnt = 1;
			CoffeeVendingMachineFrame.prq.add(new Quantity(""));
			for (int i = 0; i < size; i++) {
				String num = String.valueOf(cnt);
				f.prq.add(new Quantity(num));
				cnt++;
			}

			JLabel label2 = new JLabel("리필할 수량을 선택해주세요.");
			f.comboBox2 = new JComboBox(f.prq);
			f.comboBox2.setMaximumRowCount(size);
			frm2.add(label2);
			frm2.add(f.comboBox2);
			frm2.setVisible(true);
			frm2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			cnt = 1;
			dcbm.addElement("");
			for (int i = 0; i < size; i++) {
				String num = String.valueOf(cnt);
				dcbm.addElement(num);
				cnt++;
			}
			f.comboBox2.setModel(dcbm);

			f.comboBox2.addItemListener(new QuantityHandler());

			f.frm.setVisible(false);
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {

		}
	}
}

/**
 * QuantityHandler -> Fill Combo Box
 */
class QuantityHandler implements ItemListener {
	CoffeeVendingMachineFrame f;

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String x = String.valueOf(e.getItem());
			int num = Integer.parseInt(x);
			f.machine.product.addProduct(ProductChoiceHandler.x, num);
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {

		}
	}
}

/**
 * MoneyChoiceHandler -> Fill Combo Box
 */
class MoneyChoiceHandler implements ItemListener {
	static public int size2 = 0;
	static public String x2;
	CoffeeVendingMachineFrame f2;
	private JFrame frm4 = new JFrame("Number");

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			x2 = String.valueOf(e.getItem());
			if (x2 == "1000원") {
				size2 = 20 - CoffeeVendingMachineFrame.machine.charge.getOneThousandWon();
			} else if (x2 == "500원") {
				size2 = 20 - CoffeeVendingMachineFrame.machine.charge.getFiveHundredWon();
			} else if (x2 == "100원") {
				size2 = 20 - CoffeeVendingMachineFrame.machine.charge.getOneHundredWon();
			} else if (x2 == "50원") {
				size2 = 20 - CoffeeVendingMachineFrame.machine.charge.getFiftyWon();
			} else if (x2 == "10원") {
				size2 = 20 - CoffeeVendingMachineFrame.machine.charge.getTenWon();
			}
			frm4.setBounds(120, 120, 350, 150);
			frm4.setLayout(new GridLayout(0, 1));
			int cnt = 1;
			CoffeeVendingMachineFrame.mrn.add(new Number(""));
			for (int i = 0; i < size2; i++) {
				String num = String.valueOf(cnt);
				f2.mrn.add(new Number(num));
				cnt++;
			}

			JLabel label2 = new JLabel("리필할 돈의 갯수를 선택해주세요.");
			f2.comboBox4 = new JComboBox(f2.mrn);
			f2.comboBox4.setMaximumRowCount(size2);
			frm4.add(label2);
			frm4.add(f2.comboBox4);
			frm4.setVisible(true);
			frm4.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			cnt = 1;
			dcbm.addElement("");
			for (int i = 0; i < size2; i++) {
				String num = String.valueOf(cnt);
				dcbm.addElement(num);
				cnt++;
			}
			f2.comboBox4.setModel(dcbm);

			f2.comboBox4.addItemListener(new NumberHandler());

			f2.frm3.setVisible(false);

		} else if (e.getStateChange() == ItemEvent.DESELECTED) {

		}
	}
}

/**
 * NumberHandler -> Fill Combo Box
 */
class NumberHandler implements ItemListener {
	CoffeeVendingMachineFrame f2;

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String x = String.valueOf(e.getItem());
			int num = Integer.parseInt(x);
			f2.machine.charge.addMoney(MoneyChoiceHandler.x2, num);
			// f2.updateMoney();
			// System.out.println("**" + f2.machine.charge.getOneThousandWon());
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {

		}
	}
}
