package com.gaian.practiceJSONStringBuffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@SpringBootApplication
public class OpenApIspec1Application{

	public static void main(String[] args)  throws IOException, JSONException  {
		
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\manit\\Documents\\workspace-spring-tool-suite-4-4.17.2.RELEASE\\openAPIspec-1\\src\\main\\resources\\demo.json"));
		SpringApplication.run(OpenApIspec1Application.class, args);
		String line;
		StringBuilder sbuilderObj = new StringBuilder();
		
		while((line=br.readLine()) !=null){
			sbuilderObj.append(line);
			System.out.println(line);
		}
		JSONObject inputJsonObj = new JSONObject(sbuilderObj.toString());
		
		JSONObject output = new JSONObject();
		
		output.put("mani",createFolder(inputJsonObj));
		System.out.println(output);
		
//		FileWriter file = new FileWriter("C:\\Users\\manit\\Documents\\workspace-spring-tool-suite-4-4.17.2.RELEASE\\openAPIspec-1\\src\\main\\resources\\output.json");
//		file.write(output.toString());
//		file.close();
		
	}

	private static JSONObject createFolder(JSONObject inputJsonObj) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject folder = new JSONObject();
		folder.put("type", "Group");
		folder.put("link", JSONObject.NULL);
		folder.put("comment", JSONObject.NULL);
		JSONArray arr = new JSONArray();
		JSONArray inputArr = inputJsonObj.getJSONArray("item");
		for(int i=0; i<inputArr.length(); i++) {
			JSONObject inputJsonObject = inputArr.getJSONObject(i);
			JSONObject request = new JSONObject();
			String name = inputJsonObject.getJSONObject("request").getString("method")+ "  /"+ inputJsonObject.getString("name");
			request.put(name, createAPI(inputJsonObject));
			arr.put(request);
		}
		folder.put("children", arr);
		return folder;
	}


	private static Object createAPI(JSONObject inputJsonObject) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject api = new JSONObject();
		api.put("type", "API");
		api.put("link", JSONObject.NULL);
		api.put("comment", JSONObject.NULL);
		api.put("apiType", "REST");
		api.put("communicationType", "SYNC");
		String[] apip = {"HTTP-HEADERS","REQUEST-BODY", "RESPONSE-BODY", "PATH-PARAMETERS", "HTTP-METHOD", "HTTP-URL" };
		JSONArray arr = new JSONArray();
		int i=0;
		for(; i<4; i++) {
			JSONObject apiChilds = new JSONObject();
			apiChilds.put(apip[i],createApiPropertiesWithChild(inputJsonObject,i));
			arr.put(apiChilds);
		}
		for(;i<6; i++) {
			JSONObject apiChilds = new JSONObject();
			apiChilds.put(apip[i],createApiPropertiesWithoutChild(inputJsonObject,i));
			arr.put(apiChilds);
		}
	    api.put("children", arr);
		
		return api;
	}
	
	private static Object createApiPropertiesWithChild(JSONObject inputJsonObject,int i) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject apiProp = new JSONObject();
		apiProp.put("type", "Group");
		apiProp.put("link", JSONObject.NULL);
		apiProp.put("comment", JSONObject.NULL);
		switch(i) {
		case 1:
			JSONArray arr = inputJsonObject.getJSONObject("response").getJSONArray("header");
			if(arr.lenght()!=0) {
				for(int i=0; i<)
			}
		}
		
		
		return null;
	}

	private static Object createApiPropertiesWithoutChild(JSONObject inputJsonObject,int i) {
		// TODO Auto-generated method stub
		
		
		return null;
	}



}
