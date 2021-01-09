package io.fixer.api.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ExchangeResponseDTO {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime time;
	private String from;
	private String to;
	private Double amount;
	private Double rate;
	private Double convertResult;
	private EuroBasedRatesDTO euroBasedRates;

	public ExchangeResponseDTO() {
	}

	public ExchangeResponseDTO(LocalDateTime time, String from, String to, Double amount, Double rate,
			Double convertResult) {
		super();
		this.time = time;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.rate = rate;
		this.convertResult = convertResult;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
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

	public EuroBasedRatesDTO getEuroBasedRates() {
		return euroBasedRates;
	}

	public void setEuroBasedRates(EuroBasedRatesDTO euroBasedRates) {
		this.euroBasedRates = euroBasedRates;
	}

	@Override
	public String toString() {
		return "Conversion [time=" + time + ", from=" + from + ", to=" + to + ", amount=" + amount + ", rate=" + rate
				+ ", convertResult=" + convertResult + "]";
	}
}
