package com.test.devm.stockalarm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class StockData {

	@JsonProperty("Global Quote")
	private JsonNode quote;

	public JsonNode getQuote() {
		return quote;
	}

	public void setQuote(JsonNode quote) {
		this.quote = quote;
	}
	
	
}
