package il.co.ilrd.crudy;



import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.JsonObject;

public class TomCatCrud implements Observer{
	private String monitorPath;
	private HttpPost requestToTomcat;
		
	public TomCatCrud(String monitorPath, String url) throws IOException {
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
				/*requestToTomcat.setHeader("Accept", "application/json");
				requestToTomcat.setHeader("Content-type", "application/json");*/
				httpClient.execute(requestToTomcat);
				
			} catch (IOException e1) {
				e1.printStackTrace();
		}
	}
	 
public JsonObject buildJsonObject(String line,String monitoredfile) {
	JsonObject requestJsonObject = new JsonObject();
	requestJsonObject.addProperty("command_type","POST");
	requestJsonObject.addProperty("company","ilrd");
	requestJsonObject.addProperty("product","monitoredfile");
	requestJsonObject.add("data",BuildJsonData(line, monitoredfile)/*json object*/);
	
	return requestJsonObject;
}

private JsonObject BuildJsonData(String line, String monitoredFile) {
	JsonObject data = new JsonObject();
	data.addProperty("line", line);
	data.addProperty("monitored_file", monitoredFile);
	
	return data;
	}
}