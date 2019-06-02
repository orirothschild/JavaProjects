package il.co.ilrd.sqlcrud;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Consumer;

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
@WebServlet("/CrudSql")
public class TomCatCrudSql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, Connection> mymap = new HashMap<String, Connection>();
	LinkedList<Consumer<JsonObject>> list = new LinkedList<Consumer<JsonObject>>();
	final private String host = "jdbc:mysql://localhost:3306/ilrd";
	final private String user = "root";
	final private String passwd = "978645312";
	private final String database = "ilrd";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TomCatCrudSql() {
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
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream inputStream = request.getInputStream();
	    JsonParser parser = new JsonParser();
	    JsonObject json = (JsonObject)parser.parse(new InputStreamReader(inputStream));//creates a new json object from inputted data
		Connection conn = null;
		String database = validateString(json.get("Company").getAsString());
		String table = validateString(json.get("Product").getAsString());
		Objects.requireNonNull(table,"table is null");
		Objects.requireNonNull(database,"database is null");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mymap.put(database, DriverManager.getConnection(host, user, passwd));
			addmethodstolist(list);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	    if (mymap.containsKey(json.get("Company").getAsString())) {
		   conn = mymap.get(json.get("Company").getAsString());
		   if ( json.get("Data") != null) {
			   String a = json.get("Data").toString();
			   System.out.println(a);
		   }
		   if (validateTable(conn, table, json)) {
			   list.get(0).accept(json);//create
			   response.setStatus(HttpServletResponse.SC_OK);
		   }
		   else {
		    	System.out.println("somthing wong with table");
		    }
	    }
	    else {
	    	System.out.println("somthing wong");
	    }
	    	
	}

	private CRUDSql addmethodstolist(LinkedList<Consumer<JsonObject>> list2) {
		CRUDSql crudsql = new CRUDSql(mymap.get(database));
		list.add(crudsql::create);
		list.add(crudsql::read);
		list.add(crudsql::delete0);
		list.add(crudsql::update);
		return crudsql;
		
	}
	private String validateString(String str) {
		str = str == null? null:str;
		return str;
		
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
