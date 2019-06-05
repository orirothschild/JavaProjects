package il.co.ilrd.gatewayserver;

public enum ServerResponse{ 
	OK(200, "all ok"){


	
	},	
	BAD_REQUEST(400, "unsuported action"){



    },
	
	MISSING_TOKENS(404, "missing tokens"){


    },
	
	FAILURE(410,"failed to preform request"){



    },
	
	GENERIC_RESPONSE(410,"failed to preform request"){
		
	};
	
	
	private ServerResponse(int code , String msg) {
		this.msg = msg;
		this.code = code;
	}
	
	public void setResponse(String msg ,int code)
	{
		this.msg = msg;
		this.code = code;
	}
	
	public void setCode(int code) {
		
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	int code;
	String msg;
	
}