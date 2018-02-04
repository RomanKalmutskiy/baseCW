package PR;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WinPR extends JFrame {

	DBWorker dbWorker = null;

	DBWorker connect = new DBWorker("localhost", "root", "");

	String categoryFiltr;
	String itemFiltr;

	ListMaterialsTableModel listMaterialsTableModel = new ListMaterialsTableModel();

	String filterCategorMat = "";

	JLabel labelMain = new JLabel("Main menu of PR creator", SwingConstants.CENTER);
	JLabel labelLM = new JLabel("List of Materials");
	JLabel labelNewMat = new JLabel("New Material");
	JLabel labelNewMatEnt = new JLabel(
			"Enter new materials.  Please fill:   category,     description,   currency,   price");
	JLabel labelNewNumPR = new JLabel("Num PR:_____________");
	JLabel labelStatus = new JLabel("        ");
	JLabel labelCategoryList = new JLabel("Enter category of materials", SwingConstants.RIGHT);
	JLabel labelItemMater = new JLabel("Enter Item code", SwingConstants.RIGHT);
	JLabel labelNewMatItem = new JLabel("New Item");
	JLabel labelnewMatCategorys = new JLabel("Category");
	JLabel labelnewMatDescription = new JLabel("Description");
	JLabel labelnewMatCurrency = new JLabel("Currency");
	JLabel labelnewMatPrice = new JLabel("Price");
	JLabel labelPrItem = new JLabel("Fill Item:");
	JLabel labelPrLine = new JLabel("Line of PR");
	JLabel labelPrDescription = new JLabel("Description");
	JLabel labelPrUnit = new JLabel("Unit");
	JLabel labelPrPrice = new JLabel("Price");
	JLabel labelPrQty = new JLabel("Fill Q-ty");
	JLabel labelPrAmount = new JLabel("Amount");
	JLabel labelPrCurrency = new JLabel("Currency");

	JButton buttonM1;
	JButton buttonPNewM;
	JButton buttonM3;
	JButton buttonPNewPR;
	JButton buttonLM;
	JButton buttonBackLM;
	JButton buttonBackNM;
	JButton buttonBackNPR;
	JButton buttonBackLPR;

	JButton buttonCheckNewPR;
	JButton buttonNewPR;
	JButton buttonADDLinePR;
	JButton buttonCheckNewMaterials;
	JButton buttonAddNewMaterials;

	JTextField fieldCategory = new JTextField("");
	JTextField fieldItem = new JTextField("");
	JTextArea areaDescriptionNew = new JTextArea(5, 20);

	JTextField fieldnewMatCategory = new JTextField("");
	JTextField fieldnewMatItem = new JTextField();
	JTextField fieldnewMatDescription = new JTextField("");
	JTextField fieldnewMatCurrency = new JTextField("");
	JTextField fieldnewMatPrice = new JTextField("");
	JTextField fieldPrItem = new JTextField("");
	JTextField fieldPrLine = new JTextField("");
	JTextField fieldPrDescription = new JTextField("");
	JTextField fieldPrUnit = new JTextField("");
	JTextField fieldPrPrice = new JTextField("");
	JTextField fieldPrQty = new JTextField("");
	JTextField fieldPrAmount = new JTextField("");
	JTextField fieldPrCurrency = new JTextField("");

	NewMaterialsTableMode nmtm = new NewMaterialsTableMode();
	JTable tableNewM = new JTable(nmtm);
	JScrollPane scrolNewM = new JScrollPane(tableNewM);

	ListMaterialsTableModel lmtm = new ListMaterialsTableModel();
	JTable tableListM = new JTable(lmtm);
	JScrollPane scrolListM = new JScrollPane(tableListM);

	NewPRTableModel nprm = new NewPRTableModel();
	JTable tableNewPR = new JTable(nprm);
	JScrollPane scrolListNewPR = new JScrollPane(tableNewPR);

	JPanel panelM1 = new JPanel();
	JPanel panelNewM = new JPanel();
	JPanel panelLM2 = new JPanel();
	JPanel panelNewPR = new JPanel();

	JPanel panel5 = new JPanel();

	public WinPR() {
		getCategorLMFilter();

	}

	public WinPR(DBWorker dbWorker) {
		super("PR creator");
		this.dbWorker = dbWorker;
		setSize(900, 600);

		buttonM1 = new JButton("List of Materials");
		buttonPNewM = new JButton("New Materials");
		buttonM3 = new JButton("List of PR");
		buttonPNewPR = new JButton("New PR");
		buttonLM = new JButton("Chose Materials");
		buttonCheckNewMaterials = new JButton("Check Materials");
		buttonAddNewMaterials = new JButton("Add new materials");
		buttonBackLM = new JButton("Back");
		buttonBackNM = new JButton("Back");
		buttonBackNPR = new JButton("Back");
		buttonBackLPR = new JButton("Back");
		buttonCheckNewPR = new JButton("Check PR");
		buttonNewPR = new JButton("New PR");
		buttonADDLinePR = new JButton("Add line");

		getPanelM1();
		getPanelNM();
		getPanelLM2();
		getPanelNPR();

		getLMCatField();
		getLMItemFieltd();

		add(panelM1);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initListeners();
		setResizable(false);
	}

	private void initListeners() {
		buttonM1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseListMaterials();

			}
		});

		buttonBackNM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseMainMenu();

			}
		});

		buttonBackLM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseMainMenu();

			}
		});

		buttonBackNPR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseMainMenu();

			}
		});

		buttonBackLPR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseMainMenu();

			}
		});

		buttonPNewPR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseNewPR();

			}
		});

		buttonPNewM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseNewM();

			}
		});

		buttonAddNewMaterials.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addNewM();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		buttonLM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addListMaterials();

			}
		});

		buttonCheckNewPR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					newPrCheckMeterials();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	protected void newPrCheckMeterials() throws SQLException {
		int line = 10;
		NewPR newPR = null;
		NewMaterials newPRLine = null;
		String newPrItem = fieldPrItem.getText().trim();

		dbWorker.getNumberNewPR();
		labelNewNumPR.setText("Num PR: " + dbWorker.getNumberNewPR());
		fieldPrLine.setText(Integer.toString(line));
		int itemCode = Integer.parseInt(fieldPrItem.getText());
		dbWorker.newPRLine(itemCode);
		System.out.println("win" + dbWorker.newPRLine(itemCode).getDescription());
		fieldPrDescription.setText(dbWorker.newPRLine(itemCode).getDescription());
		fieldPrPrice.setText(Double.toString(dbWorker.newPRLine(itemCode).getPrice()));
		fieldPrCurrency.setText(dbWorker.newPRLine(itemCode).getCurency());
		double price = Double.parseDouble(fieldPrPrice.getText());
		int qty = Integer.parseInt(fieldPrQty.getText());
		double amount = price * qty;
		fieldPrAmount.setText(Double.toString(amount));

	}

	protected void addListMaterials() {

		scrolListM.updateUI();
		scrolListM.repaint();

		String categoryFiltr = getLMCatField();
		String itemFiltr = getLMItemFieltd();

		connect.getCatFilter();
		// listMaterialsTableModel.setFilter(categoryFiltr, itemFiltr);

		// System.out.println("Win filter:="+categoryFiltr);

		connect.setFilter(categoryFiltr, itemFiltr);
		lmtm.addData(connect, categoryFiltr, itemFiltr);
		scrolListM.updateUI();
		scrolListM.repaint();

		repaint();
		revalidate();

	}

	protected void addNewM() throws SQLException {
		fieldnewMatItem.setText("");
		String category = fieldnewMatCategory.getText().trim();
		String description = fieldnewMatDescription.getText().trim();
		String carrency = fieldnewMatCurrency.getText().trim();
		double price = Double.parseDouble(fieldnewMatPrice.getText().trim());

		NewMaterials newMaterials = new NewMaterials(category, description, carrency, price);
		dbWorker.sqlAdd(newMaterials);
		dbWorker.getItemNewMat();
		System.out.println(newMaterials.getCategory() + " " + newMaterials.getDescription());
		fieldnewMatItem.setText(dbWorker.getItemNewMat());
		System.out.println("New item code:= " + dbWorker.getItemNewMat());
		repaint();
		revalidate();
	}

	protected void chooseNewM() {
		this.panelNewM = new JPanel();
		getPanelNM();
		add(panelNewM);
		remove(panelM1);
		repaint();
		revalidate();

	}

	protected void chooseNewPR() {
		this.panelNewPR = new JPanel();
		getPanelNPR();
		add(panelNewPR);
		remove(panelM1);
		repaint();
		revalidate();
	}

	protected void chooseMainMenu() {
		this.panelM1 = new JPanel();
		getPanelM1();
		add(panelM1);
		remove(panelLM2);
		remove(panelNewPR);
		remove(panelNewM);
		repaint();
		revalidate();
	}

	protected void chooseListMaterials() {
		this.panelLM2 = new JPanel();
		getPanelLM2();
		add(panelLM2);
		remove(panelM1);
		repaint();
		revalidate();

	}

	private JPanel getPanelM1() {

		panelM1.setLayout(null);
		labelMain.setBounds(350, 50, 200, 20);
		buttonM1.setBounds(350, 100, 200, 80);
		buttonPNewM.setBounds(350, 200, 200, 80);
		buttonM3.setBounds(350, 300, 200, 80);
		buttonPNewPR.setBounds(350, 400, 200, 80);

		panelM1.add(labelMain);
		panelM1.add(buttonM1);
		panelM1.add(buttonPNewM);
		panelM1.add(buttonM3);
		panelM1.add(buttonPNewPR);

		return panelM1;

	}

	private JPanel getPanelLM2() {

		panelLM2.setLayout(null);
		labelLM.setBounds(350, 20, 200, 20);
		labelCategoryList.setBounds(15, 85, 170, 30);
		fieldCategory.setBounds(300, 85, 130, 30);
		labelItemMater.setBounds(15, 120, 170, 30);
		fieldItem.setBounds(300, 120, 130, 30);

		buttonLM.setBounds(450, 85, 130, 30);
		buttonBackLM.setBounds(600, 85, 130, 30);
		scrolListM.setBounds(50, 200, 700, 350);

		scrolListM.setPreferredSize(new Dimension(700, 350));

		panelLM2.add(scrolListM);

		panelLM2.add(scrolListM, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		panelLM2.add(scrolListM);

		panelLM2.add(labelLM);
		panelLM2.add(buttonLM);
		panelLM2.add(buttonBackLM);
		panelLM2.add(fieldCategory);
		panelLM2.add(fieldItem);
		panelLM2.add(labelCategoryList);
		panelLM2.add(labelItemMater);

		return panelLM2;

	}

	private JPanel getPanelNM() {

		panelNewM.setLayout(null);
		labelNewMat.setBounds(350, 30, 200, 20);
		labelNewMatEnt.setBounds(50, 100, 700, 20);
		buttonCheckNewMaterials.setBounds(50, 300, 200, 50);
		buttonAddNewMaterials.setBounds(300, 300, 200, 50);
		buttonBackNM.setBounds(550, 300, 200, 50);
		scrolNewM.setBounds(50, 200, 700, 70);
		labelNewMatItem.setBounds(50, 150, 140, 25);
		fieldnewMatItem.setBounds(50, 170, 140, 25);
		labelnewMatCategorys.setBounds(190, 150, 140, 25);
		fieldnewMatCategory.setBounds(190, 170, 140, 25);
		labelnewMatDescription.setBounds(330, 150, 140, 25);
		fieldnewMatDescription.setBounds(330, 170, 140, 25);
		labelnewMatCurrency.setBounds(470, 150, 139, 25);
		fieldnewMatCurrency.setBounds(470, 170, 139, 25);
		labelnewMatPrice.setBounds(610, 150, 140, 25);
		fieldnewMatPrice.setBounds(610, 170, 140, 25);

		scrolNewM.setPreferredSize(new Dimension(700, 50));

		panelNewM.add(labelNewMat);
		panelNewM.add(labelNewMatEnt);
		panelNewM.add(buttonCheckNewMaterials);
		panelNewM.add(buttonAddNewMaterials);
		panelNewM.add(buttonBackNM);
		panelNewM.add(fieldnewMatCategory);
		panelNewM.add(fieldnewMatItem);
		panelNewM.add(labelNewMatItem);
		panelNewM.add(fieldnewMatDescription);
		panelNewM.add(fieldnewMatCurrency);
		panelNewM.add(fieldnewMatPrice);
		panelNewM.add(labelnewMatCategorys);
		panelNewM.add(labelnewMatDescription);
		panelNewM.add(labelnewMatCurrency);
		panelNewM.add(labelnewMatPrice);

		return panelNewM;

	}

	private JPanel getPanelNPR() {

		panelNewPR.setLayout(null);
		labelNewNumPR.setBounds(30, 30, 100, 20);
		areaDescriptionNew.setBounds(170, 20, 300, 120);
		buttonCheckNewPR.setBounds(500, 30, 100, 50);
		buttonNewPR.setBounds(500, 90, 100, 50);
		buttonBackNPR.setBounds(650, 30, 100, 50);
		buttonADDLinePR.setBounds(650, 90, 100, 50);

		labelPrLine.setBounds(30, 150, 105, 25);
		fieldPrLine.setBounds(30, 175, 105, 25);

		labelPrItem.setBounds(137, 150, 105, 25);
		fieldPrItem.setBounds(137, 175, 105, 25);

		labelPrDescription.setBounds(243, 150, 105, 25);
		fieldPrDescription.setBounds(243, 175, 105, 25);

		labelPrUnit.setBounds(349, 150, 105, 25);
		fieldPrUnit.setBounds(349, 175, 105, 25);

		labelPrPrice.setBounds(455, 150, 105, 25);
		fieldPrPrice.setBounds(455, 175, 105, 25);

		labelPrQty.setBounds(561, 150, 105, 25);
		fieldPrQty.setBounds(561, 175, 105, 25);

		labelPrAmount.setBounds(667, 150, 105, 25);
		fieldPrAmount.setBounds(667, 175, 105, 25);

		labelPrCurrency.setBounds(773, 150, 105, 25);
		fieldPrCurrency.setBounds(773, 175, 105, 25);

		scrolListNewPR.setBounds(30, 200, 850, 350);
		scrolListNewPR.setPreferredSize(new Dimension(700, 350));
		panelNewPR.add(scrolListNewPR);

		panelNewPR.add(scrolListNewPR, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		panelNewPR.add(scrolListNewPR);

		panelNewPR.add(labelNewNumPR);
		panelNewPR.add(areaDescriptionNew);
		panelNewPR.add(buttonCheckNewPR);
		panelNewPR.add(buttonNewPR);
		panelNewPR.add(buttonBackNPR);
		panelNewPR.add(buttonADDLinePR);
		panelNewPR.add(labelPrItem);
		panelNewPR.add(fieldPrItem);
		panelNewPR.add(labelPrLine);
		panelNewPR.add(fieldPrLine);
		panelNewPR.add(labelPrDescription);
		panelNewPR.add(fieldPrDescription);
		panelNewPR.add(labelPrUnit);
		panelNewPR.add(fieldPrUnit);
		panelNewPR.add(labelPrPrice);
		panelNewPR.add(fieldPrPrice);
		panelNewPR.add(labelPrQty);
		panelNewPR.add(fieldPrQty);
		panelNewPR.add(labelPrAmount);
		panelNewPR.add(fieldPrAmount);
		panelNewPR.add(labelPrCurrency);
		panelNewPR.add(fieldPrCurrency);

		return panelNewPR;
	}

	public String getLMCatField() {
		return fieldCategory.getText();
	}

	public String getLMItemFieltd() {
		return fieldItem.getText();
	}

	public String getCategorLMFilter() {
		filterCategorMat = fieldCategory.getText();
		return filterCategorMat;

	}

}
