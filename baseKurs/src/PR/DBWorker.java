package PR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBWorker {
	private String host;
	private String dataDb;
	private String root;
	private String password;
	private String url;

	WinPR winPR;
	private String categorFiltr;
	private String itemFiltr;

	private Properties properties = new Properties();
	private Connection conn = null;

	public DBWorker(String host, String root, String password) {
		this.host = host;
		this.root = root;
		this.password = password;
	}

	public DBWorker(String url, Properties properties) {
		this.url = url;
		this.properties = properties;
	}

	public void setNameDataBasses(String dataDb) {
		this.dataDb = dataDb;
	}

	public void initProperties() {
		this.url = "jdbc:mysql://" + this.host + "/" + this.dataDb;

		properties.setProperty("user", this.root);
		properties.setProperty("password", this.password);
		properties.setProperty("useUnicode", "true");
		properties.setProperty("characterEncoding", "UTF-8");

		System.out.println(url);

	}

	private Statement st;

	String itemM;
	String numPR;

	DBWorker() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/pr?" + "user=root&password=");
		st = conn.createStatement();

	}

	public void finalaze() {
		try {
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet resultSetQuery(String query) {
		try {
			// Statement stmt =conn.createStatement();
			Statement stmt = DriverManager.getConnection("jdbc:mysql://localhost/pr?" + "user=root&password=")
					.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public void sqlQuery(String query) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void sqlAdd(NewMaterials newMaterials) throws SQLException {

		String query = "INSERT INTO Materials(mat_category, mat_description, mat_currency, mat_price) values (?,?,?,?)";
		PreparedStatement m = conn.prepareStatement(query);

		m.setString(1, newMaterials.getCategory());
		m.setString(2, newMaterials.getDescription());
		m.setString(3, newMaterials.getCurency());
		m.setDouble(4, newMaterials.getPrice());
		m.execute();

	}

	public String getItemNewMat() throws SQLException {
		ResultSet rsItem = null;

		String query = "SELECT MAX(mat_item) AS mat_item FROM Materials";
		try {
			System.out.print("Executing query...");
			rsItem = st.executeQuery(query);
			// System.out.println("Query success");
			while (rsItem.next()) {
				// System.out.println(rsItem.getString(1));
				itemM = rsItem.getString(1);
			}
			rsItem.close();
		} catch (SQLException e) {
			System.out.println("Query Errror");
			System.exit(1);
		}
		return itemM;

	}

	public void setFilter(String catFiltr, String itemFilt) {
		this.categorFiltr = catFiltr;
		this.itemFiltr = itemFilt;

	}

	public String getCatFilter() {
		return categorFiltr;

	}

	public String getNumberNewPR() throws SQLException {
		ResultSet rsNumPR = null;

		String query = "SELECT MAX(pr_id) AS pr_id FROM pr";
		try {
			System.out.print("Executing query...");
			rsNumPR = st.executeQuery(query);
			while (rsNumPR.next()) {
				numPR = rsNumPR.getString(1);
			}
			rsNumPR.close();
		} catch (SQLException e) {
			System.out.println("Query Errror");
			System.exit(1);
		}
		return numPR;

	}

	public NewMaterials newPRLine(int mat_id) throws SQLException {
		ResultSet rs = null;

		String query = "SELECT * FROM materials WHERE mat_item=" + mat_id;
		NewMaterials newPRLine = null;
		rs = st.executeQuery(query);
		if (rs.next()) {
			newPRLine = new NewMaterials(rs.getString(3), rs.getDouble(5), rs.getString(4));
			System.out.println(rs.getString(3) + " " + rs.getDouble(5) + " " + rs.getString(4));
		}

		rs.close();
		return newPRLine;
	}

}
