package SQL;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class connect
 */
@WebServlet("/connect1")
public class connect1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public class Connect1 {

		private static final String SQLSERVER_IP = "192.168.2.103";
		private static final String SQLSERVER_PORT = "1433";
		private static final String SQLSERVER_DB_NAME = "dbo.test"; // Database名
		private static final String SQLSERVER_USER = "a";
		private static final String SQLSERVER_PASSWORD = "a";

		public ResultSet select() {
			
			ResultSet result = null;
			Connection con;
			// DB Connect
	        try {
	        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
	        	
	        	con = DriverManager.getConnection(getConnectionString()); 
	        	
	        	Statement stmt = con.createStatement();
	        	
	        	// SQL
	            String SQL = "SELECT TOP (1000) [a]\r\n" + 
	            		"      ,[b]\r\n" + 
	            		"  FROM [test].[dbo].[test]"; // SQL文
	            
	            // execute
	            ResultSet rs = stmt.executeQuery(SQL);
     
	            // Result
	            while (rs.next()) {
	                System.out.println(rs.getString("a") + " " + rs.getString("b"));
	            }
	        }
	        // Handle any errors that may have occurred.
	        catch (SQLException e) {
	            e.printStackTrace();
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
			
			return result;
		}
		/**
		 * 接続文字列を生成
		 * 
		 * @return
		 */
		private String getConnectionString() {
			return "jdbc:sqlserver://" + SQLSERVER_IP + ":" + SQLSERVER_PORT + ";" + "databaseName=" + SQLSERVER_DB_NAME
					+ ";" + "user=" + SQLSERVER_USER + ";" + "password=" + SQLSERVER_PASSWORD;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Connect db = new Connect();

		ResultSet res = db.select();
		

	    PrintWriter out = response.getWriter();
		if(res == null) {
		    out.println("ng");
		}else {
		    out.println("ok");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

