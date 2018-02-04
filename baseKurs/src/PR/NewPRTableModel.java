package PR;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class NewPRTableModel extends AbstractTableModel {

	private int columnCount = 8;
	private ArrayList<String[]> NewPRArrayList;

	public NewPRTableModel() {
		NewPRArrayList = new ArrayList<String[]>();
		for (int i = 0; i < NewPRArrayList.size(); i++) {
			NewPRArrayList.add(new String[getColumnCount()]);
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
			return "line";
		case 1:
			return "item code";
		case 2:
			return "description";
		case 3:
			return "unit";
		case 4:
			return "price";
		case 5:
			return "Q-ty";
		case 6:
			return "Amount";
		case 7:
			return "Currency";
		}
		return "";

	}

	@Override
	public int getRowCount() {
		return NewPRArrayList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String[] rows = NewPRArrayList.get(rowIndex);
		return rows[columnIndex];
	}

	public void addData(String[] row) {
		String[] rowTable = new String[getColumnCount()];
		rowTable = row;
		NewPRArrayList.add(rowTable);
	}

}
