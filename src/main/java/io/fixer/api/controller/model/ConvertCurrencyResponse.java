package io.fixer.api.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ConvertCurrencyResponse {

	private boolean success;
	private String time;
	private String from;
	private String to;
	private Double amount;
	private Double rate;
	private Double convertResult;
	private EuroBasedRates euroBasedRates;

	public ConvertCurrencyResponse() {
	}

	public ConvertCurrencyResponse(String time, String from, String to, Double amount, Double rate,
			Double convertResult, EuroBasedRates euroBasedRates) {
		super();
		this.time = time;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.rate = rate;
		this.convertResult = convertResult;
		this.euroBasedRates = euroBasedRates;
		this.success = true;
	}

	public ConvertCurrencyResponse(String time, String from, String to, Double amount, Double rate,
			Double convertResult) {
		super();
		this.time = time;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.rate = rate;
		this.convertResult = convertResult;
		this.success = true;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getConvertResult() {
		return convertResult;
	}

	public void setConvertResult(Double convertResult) {
		this.convertResult = convertResult;
	}

	public EuroBasedRates getEuroBasedRates() {
		return euroBasedRates;
	}

	public void setEuroBasedRates(EuroBasedRates euroBasedRates) {
		this.euroBasedRates = euroBasedRates;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "Conversion [time=" + time + ", from=" + from + ", to=" + to + ", amount=" + amount + ", rate=" + rate
				+ ", convertResult=" + convertResult + "]";
	}
}
