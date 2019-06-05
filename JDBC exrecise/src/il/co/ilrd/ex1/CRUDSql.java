package il.co.ilrd.ex1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.google.gson.JsonObject;

import il.co.ilrd.crudy.CRUD;

	public class CRUDSql implements CRUD<JsonObject, Integer> {
		private JsonObject Data = null;
		private Connection mySQL = null;
		private PreparedStatement stmt;
		public CRUDSql(Connection conn) {
			mySQL = conn;
		}
		
		@Override
		public Integer create(JsonObject json) {
			if (json.get("SerialNumber").getAsString() == null) {return null;}
			String table = json.get("Product").getAsString();
			
			String insertTableSQL = "INSERT INTO "+table+""
					+ "(id, serial_number, data, insert_time) VALUES"
					+ "(?,?,?,?)";
			int[] myIntArray = {1, 2, 3, 4};
			executeorder(mySQL, insertTableSQL, myIntArray,json);
		
			return 1;
		}
		@Override
		public void update(JsonObject json) {
			String table = json.get("Product").getAsString();
			String query ="update " +table+" set serial_number=?,data=?,insert_time=? where id=?";
			int[] myIntArray = {4, 1, 2, 3};
			
			executeorder(mySQL, query, myIntArray, json);
		}


		@Override
		public JsonObject read(JsonObject json) {
			String table = json.get("Product").getAsString();
			String id = json.get("Line").getAsString();
			String query = "SELECT * FROM "+table+" WHERE id = ?";
			try {
				stmt = mySQL.prepareStatement(query);
				stmt.setString(1,id);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					System.out.println("id -"+rs.getString(1));
					System.out.println("serial_number -"+rs.getString(2));
					System.out.println("data -"+rs.getString(3));
					System.out.println("insert_time -"+rs.getString(4));
					System.out.println("---------------");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		  }

		@Override
		public void delete0(JsonObject id) {
			if(id.get("Product") == null) {return;}
		      String query = "delete from "+id.get("Product").getAsString()+" where id = ?";
		      try {
				stmt = mySQL.prepareStatement(query);
				stmt.setString(1,id.get("delete_line").getAsString());
				stmt.execute();// execute the preparedstatement
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		private void executeorder(Connection mySQL, String query, int[] location_arr, JsonObject json) {
			String SerialNumber = json.get("SerialNumber").getAsString();
			Data = json.get("Data").getAsJsonObject();
			String id = json.get("Line").toString();
			try {
				stmt = mySQL.prepareStatement(query);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				stmt.setInt(location_arr[0], 0);
				stmt.setString(location_arr[1], SerialNumber);
				stmt.setString(location_arr[2], Data.toString());
				stmt.setTimestamp(location_arr[3], timestamp);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: " + e.getSQLState());
			    System.out.println("VendorError: " + e.getErrorCode());
			}
		}
}
