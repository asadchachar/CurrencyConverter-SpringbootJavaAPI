package io.fixer.api.adapter.externalobjects;

import java.util.Map;

public class FixerEuroBaseCurrencyResponse {
	private String success;
	private int timestamp;
	private String base;
	private String date;
	private Map<String, Double> rates;

	public FixerEuroBaseCurrencyResponse() {
	}

	public FixerEuroBaseCurrencyResponse(String success, int timestamp, String base, String date,
			Map<String, Double> rates) {
		super();
		this.success = success;
		this.timestamp = timestamp;
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "FixerEuroBaseCurrencyResponse [success=" + success + ", timestamp=" + timestamp + ", base=" + base
				+ ", date=" + date + ", rates=" + rates + "]";
	}

}
