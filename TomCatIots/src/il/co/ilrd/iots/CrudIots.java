package il.co.ilrd.iots;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.File;

import javax.servlet.ServletConfig;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import il.co.ilrd.crudy.CRUD;
import il.co.ilrd.crudy.CRUDFile;

/**
 * Servlet implementation class CrudServlet
 */
@WebServlet("/iots")
public class CrudIots extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CRUD<String, Integer> crudFile;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudIots() {
        super();
    }

    @Override
    public void init(ServletConfig config) {
    	if(crudFile != null){return;}
    	String homeFolder =  System.getProperty("user.dir");
    	File file = new File(homeFolder,"my_log.txt");
		try {
			file.createNewFile();
			crudFile = new CRUDFile(file.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  /* 	Path path = Paths.get(System.getProperty("user.dir") +"/log.txt");
		if(!Files.exists(path)) {	
    	try {
				Files.createFile(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	crudFile = new CRUDFile(path.getParent().toString());*/
		//}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(crudFile.toString());
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
		    return jsonRequest.getAsJsonObject("data").get("line").getAsString();
	  }
}

