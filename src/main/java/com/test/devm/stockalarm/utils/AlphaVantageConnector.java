package com.test.devm.stockalarm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.devm.stockalarm.model.StockData;

@Service
public class AlphaVantageConnector {
	public static final String BASE_ENDPOINT = "https://www.alphavantage.co/query?";
	public static final String FUNCTION_TYPE = "GLOBAL_QUOTE";
	
	public double getPrice(String stock) {
		String reply = getRequest(stock);
		if (reply == null) {
			return 0;
		}
		
		String price = parseItem(reply);
		if ( price == null ) {
			return 0;
		}
		
		price = price.replaceAll("^\"|\"$", "");
		return Double.valueOf(price);
	}
	
	private String parseItem(String item) {
		
		ObjectMapper mapper = new ObjectMapper();
		String price = null;
		try {
			StockData data = mapper.readValue(item,  StockData.class);
			price = data.getQuote().get("05. price").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return price;
	}
	
	private String getRequest(String stock) {
		try {
			URL url = new URL(BASE_ENDPOINT + "function=" + FUNCTION_TYPE + "&symbol=" + stock + "&apikey=6L4QZJQWTC5N1RI2");
			URLConnection connection = url.openConnection();
			
			InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStream);
			StringBuilder responseBuilder = new StringBuilder();

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				responseBuilder.append(line);
			}
			bufferedReader.close();
			System.out.println("getRequest returned " + responseBuilder.toString());
			return responseBuilder.toString();
		} catch (IOException ex) {
			System.out.println("error on alpha request");
			ex.printStackTrace();
		}
		
		return null;
		
	}

}
