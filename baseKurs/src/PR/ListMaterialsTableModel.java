package PR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ListMaterialsTableModel extends AbstractTableModel {
	WinPR categori = null;
	DBWorker connect;
	String filtr;

	private int columnCount = 5;
	private ArrayList<String[]> matArrayList;

	public ListMaterialsTableModel() {

		matArrayList = new ArrayList<String[]>();
		for (int i = 0; i < matArrayList.size(); i++) {
			matArrayList.add(new String[getColumnCount()]);

		}

	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public String getColumnName(int colunmIndex) {
		switch (colunmIndex) {
		case 0:
			return "item";
		case 1:
			return "category";
		case 2:
			return "description";
		case 3:
			return "currency";
		case 4:
			return "pricesfwsf";

		}
		return "";

	}

	@Override
	public int getRowCount() {
		return matArrayList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String[] rows = matArrayList.get(rowIndex);
		return rows[columnIndex];
	}

	public void addData(String[] row) {
		String[] rowTable = new String[getColumnCount()];
		rowTable = row;
		matArrayList.add(rowTable);
	}

	public void addData(DBWorker connect, String categoryFiltr, String itemFiltr) {

		for (int x = 1; x <= getRowCount(); x++) {
			removeRow(x);
		}

		System.out.println("Catfiltr:=" + categoryFiltr);
		System.out.println("ItemFiltr:=" + itemFiltr);
		String query = "SELECT * FROM materials";
		if ((itemFiltr.equals("")) && (categoryFiltr.equals(""))) {
			query = "SELECT * FROM materials";
			System.out.println(query);
		} else if (itemFiltr.equals("")) {
			query = "SELECT * FROM materials WHERE mat_category = '" + categoryFiltr + "'";
			System.out.println(query);
		} else if (categoryFiltr.equals("")) {
			query = "SELECT * FROM materials WHERE mat_item = " + itemFiltr;
			System.out.println(query);
		} else
			query = "SELECT * FROM materials WHERE mat_item = " + itemFiltr + " AND mat_category = '" + categoryFiltr
					+ "'";
		System.out.println(query);

		ResultSet result = connect.resultSetQuery(query);
		try {
			while (result.next()) {

				String[] row1 = { result.getString("mat_item"), result.getString("mat_category"),
						result.getString("mat_description"), result.getString("mat_currency"),
						result.getString("mat_price"), };
				addData(row1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeRow(int x) {
		matArrayList.removeAll(matArrayList);

	}

}
