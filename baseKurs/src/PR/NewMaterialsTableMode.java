package PR;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class NewMaterialsTableMode extends AbstractTableModel {

	private int columnCount = 5;
	private int rowCount = 2;
	private ArrayList<String[]> newMatArrayList;

	public NewMaterialsTableMode() {
		newMatArrayList = new ArrayList<String[]>();
		for (int i = 0; i < newMatArrayList.size(); i++) {
			newMatArrayList.add(new String[getColumnCount()]);
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
			return "price";

		}
		return "";

	}

	@Override
	public int getRowCount() {
		return newMatArrayList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String[] rows = newMatArrayList.get(rowIndex);
		return rows[columnIndex];
	}

	public void addData(String[] row) {
		String[] rowTable = new String[getColumnCount()];
		rowTable = row;
		newMatArrayList.add(rowTable);
	}

}
