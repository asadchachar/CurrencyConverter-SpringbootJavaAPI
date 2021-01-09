package io.fixer.api.adapter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fixer.api.adapter.externalobjects.FixerEuroBaseCurrencyResponse;
import io.fixer.api.dto.EuroBasedRatesDTO;

@Service
public class FixerAdapter {

	@Autowired
	private FixerConnector fixerConnector;

	public EuroBasedRatesDTO getEuroBaseRates(String from, String to)  {
		FixerEuroBaseCurrencyResponse euroBasedRates = this.fixerConnector.getEuroBaseRates(from, to);
		
		return mapToDTO(euroBasedRates);
	}

	private EuroBasedRatesDTO mapToDTO(FixerEuroBaseCurrencyResponse latestCurrencyRates) {
		EuroBasedRatesDTO dto = new EuroBasedRatesDTO();
		
		dto.setBase(latestCurrencyRates.getBase());
		dto.setDateTime(LocalDateTime.now());
		dto.setRates(latestCurrencyRates.getRates());
		
		return dto;
	}
	
}
