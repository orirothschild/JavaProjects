package il.co.ilrd.crudtomcat;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import il.co.ilrd.crudy.CRUD;
import il.co.ilrd.crudy.CRUDFile;

/**
 * Servlet implementation class CrudServlet
 */
@WebServlet("/iots")
public class Iots extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CRUD<String, Integer> crudFile;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Iots() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
   /* 	if(crudFile != null){return;}
    //	File file =  new File(System.getProperty("user.dir" + "log/txt"));
    	String StringPath = System.getProperty("user.dir") +"/log.txt";
    	System.out.println(StringPath);
    	crudFile = new CRUDFile(config.getServletContext().getInitParameter("CrudFilePath"));*/
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doing a doGETE");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		crudFile.create(getRequestJsonAsString(request));
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	  private String getRequestJsonAsString(HttpServletRequest request) throws IOException {
		  Gson Gson = new Gson();
		  StringBuilder sb = new StringBuilder();
		    BufferedReader reader = request.getReader(); /*called instead of inputstream*/
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            sb.append(line).append('\n');
		        }
		    } finally {
		        reader.close();
		    }
		    
		    JsonObject jsonRequest = (JsonObject)Gson.fromJson(sb.toString(),JsonObject.class);
		    System.out.println(jsonRequest.getAsJsonObject("data").get("line").getAsString());
		    return jsonRequest.getAsJsonObject("data").get("line").getAsString();
	  }
}

/*
 * 
 */


