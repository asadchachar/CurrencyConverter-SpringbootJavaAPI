package io.fixer.api.service;

import java.text.DecimalFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fixer.api.adapter.FixerAdapter;
import io.fixer.api.dto.EuroBasedRatesDTO;
import io.fixer.api.dto.ExchangeResponseDTO;
import io.fixer.api.exception.FixerException;
import io.fixer.api.exception.ResponseCode;

@Service
public class FixerService {

	private static final Logger logger = LoggerFactory.getLogger(FixerService.class);
	
	@Autowired
	private FixerAdapter fixerAdapter;

	public ExchangeResponseDTO getLatestCurrencyRates(String from, String to, Double amount, boolean showEuro) {

		EuroBasedRatesDTO euroBaseRates = this.fixerAdapter.getEuroBaseRates(from, to);
		return calculateMutualExchangeRates(from, to, amount, euroBaseRates, showEuro);
	}

	/*
	 * Helper Methods
	 */
	private ExchangeResponseDTO calculateMutualExchangeRates(String from, String to, Double amount,
			EuroBasedRatesDTO euroBaseRates, boolean showEuro) {
		ExchangeResponseDTO calculatedResponse = new ExchangeResponseDTO();

		calculatedResponse.setFrom(from);
		calculatedResponse.setTo(to);
		calculatedResponse.setAmount(amount);
		calculatedResponse.setTime(euroBaseRates.getDateTime());

		Map<String, Double> ratesMap = euroBaseRates.getRates();
		Double fromCurrency = Double.valueOf(new DecimalFormat("#.#").format(ratesMap.get(from)));
		Double toCurrency = Double.valueOf(new DecimalFormat("#.#").format(ratesMap.get(to)));
		
		if(fromCurrency == null || toCurrency == null) {
			logger.error("From Currency: {} To Currency: {}", from, to);
			throw new FixerException(ResponseCode.CURRENCY_CODE_NOT_FOUND);
		}
		
		Double rate = toCurrency / fromCurrency;

		calculatedResponse.setRate(rate);
		calculatedResponse.setConvertResult(amount * rate);
		
		if(showEuro)
			calculatedResponse.setEuroBasedRates(euroBaseRates);

		return calculatedResponse;
	}

}
