package il.co.ilrd.sqlcrud;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import il.co.ilrd.ex1.CRUDSql;

/**
 * Servlet implementation class CrudSql
 */
@WebServlet("/login")
public class LoginReadSql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, Connection> mymap = new HashMap<String, Connection>();
	LinkedList<Consumer<JsonObject>> list = new LinkedList<Consumer<JsonObject>>();
	final private String host = "jdbc:mysql://localhost:3306/Iots";
	final private String user = "root";
	final private String passwd = "978645312";
	private final String database = "Iots";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginReadSql() {
    	super();
    }
    
    @Override
    public void init() {
    }
    /**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in Destory...");
		
		for (Entry<String, Connection> entry : mymap.entrySet()) {
		    Connection toClose = entry.getValue();
		    try {
				toClose.close();
			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
      		    System.out.println("SQLState: " + ex.getSQLState());
      		    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PreparedStatement pstmt = null; 
		PreparedStatement pstmt2 = null; 
		ResultSet rs = null;
		ResultSet rs1 = null;
		InputStream inputStream = request.getInputStream();
	    JsonParser parser = new JsonParser();
	    JsonObject json = (JsonObject)parser.parse(new InputStreamReader(inputStream));
	    String userInput = json.get("email").getAsString();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mymap.put(database, DriverManager.getConnection(host, user, passwd));
			Connection con = mymap.get(database);
			pstmt = con.prepareStatement("SELECT * FROM Company WHERE email = ?");
			pstmt.setString(1, userInput);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				response.setStatus(402);
				System.out.println("no user");
				doGet(request, response);
				return;
			}

			pstmt2 = con.prepareStatement("SELECT email FROM Company WHERE password = ?");
			pstmt2.setString(1, json.get("password").getAsString());
			rs1 = pstmt2.executeQuery();
			if(!rs1.next()) {
				response.setStatus(401);
				System.out.println("bad password");
				doGet(request, response);
				return;
			}
			
			response.setStatus(201);
			doGet(request, response);
			return;
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            try {
                if(pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}

	}
	private CRUDSql addmethodstolist(LinkedList<Consumer<JsonObject>> list2) {
		CRUDSql crudsql = new CRUDSql(mymap.get(database));
		list.add(crudsql::read);
		return crudsql;
		
	}

	private boolean validateTable(Connection conn, String table, JsonObject json) {
	    int i = 0;
		DatabaseMetaData meta;
		try {
			String[] types = {"TABLE"};
			meta = conn.getMetaData();
			ResultSet res = meta.getTables(null, null,table, types);
			while (res.next()) {
				++i;
				String tableName = res.getString(3);
				if (tableName.equals(table)) {
					json.addProperty("delete_row",i);
					return true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
		}
	}
