package il.co.ilrd.ex1;

import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;

public class TomCatSql implements Observer{
	private String monitorPath;
	private HttpPost requestToTomcat;
		
	public TomCatSql(String monitorPath, String url) throws IOException {
		 Objects.requireNonNull(monitorPath, "file cannot be null");
	     Objects.requireNonNull(url, "url of server cannot be null");
	     
	     this.monitorPath = monitorPath;
	     requestToTomcat = new HttpPost(url);
	}
	
	 @Override
	 public void update(Observable o, Object arg) {
			JsonObject j = buildJsonObject((String)arg, monitorPath);
			StringEntity entity;
			try (CloseableHttpClient httpClient = HttpClients.createDefault()){
				entity = new StringEntity(j.toString());
				requestToTomcat.setEntity(entity);
				httpClient.execute(requestToTomcat);
				
			} catch (IOException e1) {
				e1.printStackTrace();
		}
	}
	 
public JsonObject buildJsonObject(String line,String monitoredfile) {
	JsonObject requestJsonObject = new JsonObject();
	
	requestJsonObject.addProperty("Company","ilrd");
	requestJsonObject.addProperty("Product","Product");
	requestJsonObject.addProperty("SerialNumber",line.substring(1, 4));
	requestJsonObject.addProperty("Line",0);
	requestJsonObject.add("Data",BuildJsonData(line, monitoredfile));
	
	return requestJsonObject;
}

private JsonObject BuildJsonData(String line, String monitoredFile) {
	JsonObject data = new JsonObject();
	data.addProperty("line", line);
	data.addProperty("monitored_file", monitoredFile);
	
	return data;
	}
}
