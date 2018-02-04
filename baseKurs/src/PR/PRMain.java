package PR;

import java.sql.SQLException;

public class PRMain {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		NewPR newPR = new NewPR();
		DBWorker worker;
		try {
			worker = new DBWorker();
			WinPR winPR = new WinPR(worker);

			winPR.setVisible(true);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.print("Loading driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Success");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("Cant Load Driver");
		}

		DBWorker connect = new DBWorker("localhost", "root", "");
		connect.setNameDataBasses("pr");
		connect.initProperties();

	}

}
