package io.fixer.api.controller.model;

import java.util.Map;

public class EuroBasedRates {

	private String base;
	private String dateTime;
	private Map<String, Double> rates;

	public EuroBasedRates() {
	}

	public EuroBasedRates(String base, String dateTime, Map<String, Double> rates) {
		super();
		this.base = base;
		this.dateTime = dateTime;
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "CurrencyExchangeResponseDTO [base=" + base + ", dateTime=" + dateTime + ", rates=" + rates + "]";
	}

}
