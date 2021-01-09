package io.fixer.api.adapter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.fixer.api.adapter.externalobjects.FixerEuroBaseCurrencyResponse;
import io.fixer.api.config.FixerSettings;
import io.fixer.api.exception.FixerException;
import io.fixer.api.exception.ResponseCode;

@Service
public class FixerConnector {

	private static final Logger logger = LoggerFactory.getLogger(FixerConnector.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FixerSettings fixerSettings;

	public FixerEuroBaseCurrencyResponse getEuroBaseRates(String from, String to) {

		String URI = String.format(fixerSettings.getEuroEndpoint(), fixerSettings.getApiKey(), from + "," + to, 1);
		logger.info("Endpoint: {} ", URI);

		String jsonResponse = restTemplate.getForObject(URI, String.class);

		return mapJsonToObject(jsonResponse);
	}

	/*
	 * Helper methods
	 */

	private FixerEuroBaseCurrencyResponse mapJsonToObject(String jsonResponse) {

		try {
			ObjectMapper om = new ObjectMapper();
			FixerEuroBaseCurrencyResponse obj = new FixerEuroBaseCurrencyResponse();
			JsonNode baseNode = om.readTree(jsonResponse);

			obj.setSuccess(baseNode.get("success").booleanValue() + "");
			obj.setBase(baseNode.get("base").textValue());
			obj.setDate(baseNode.get("date").textValue());
			obj.setTimestamp(baseNode.get("timestamp").intValue());

			if(baseNode == null || baseNode.get("rates") == null)
				throw new FixerException(ResponseCode.INVALID_RATES_FIXER);

			logger.info("Rates : {}", baseNode.get("rates"));

			Map<String, Double> ratesJsonMap = om.readValue(baseNode.get("rates").toString(), Map.class);
			obj.setRates(ratesJsonMap);

			return obj;
		} catch (Exception e) {
			System.err.println("Error while mapping JSON response from Fixer");
			return null;
		}
	}

}