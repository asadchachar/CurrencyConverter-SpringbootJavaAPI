package io.fixer.api.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class EuroBasedRatesDTO {

	private String base;
	private LocalDateTime dateTime;
	private Map<String, Double> rates;

	public EuroBasedRatesDTO() {
	}

	public EuroBasedRatesDTO(String base, LocalDateTime dateTime, Map<String, Double> rates) {
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
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
