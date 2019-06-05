package il.co.ilrd.gatewayserver;


import com.google.gson.JsonObject;

public interface RequestHandler{
	public ServerResponse apply( JsonObject jsonRequest);
}