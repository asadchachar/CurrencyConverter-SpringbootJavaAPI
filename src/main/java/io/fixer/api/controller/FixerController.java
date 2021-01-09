package io.fixer.api.controller;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.fixer.api.config.FixerSettings;
import io.fixer.api.config.Util;
import io.fixer.api.controller.model.ConvertCurrencyRequest;
import io.fixer.api.controller.model.ConvertCurrencyResponse;
import io.fixer.api.controller.model.EuroBasedRates;
import io.fixer.api.dto.EuroBasedRatesDTO;
import io.fixer.api.dto.ExchangeResponseDTO;
import io.fixer.api.exception.FixerException;
import io.fixer.api.exception.ResponseCode;
import io.fixer.api.service.FixerService;

@RestController
@RequestMapping("/fixer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FixerController {

	@Autowired
	private FixerService fixerService;

	@Autowired
	private FixerSettings fixerSettings;

	@GET
	@RequestMapping("/convert")
	public ResponseEntity<Object> convertCurrencies(
			@RequestHeader Map<String, String> headers,
			@QueryParam("from") String from, 
			@QueryParam("to") String to, 
			@QueryParam("amount") Double amount,
			@QueryParam("showEuroBase") boolean showEuroBase) {

		from = cleaupParameter(from);
		to = cleaupParameter(to);
		
		validateRequest(headers, from, to, amount);
		return ResponseEntity.ok(mapToModel(this.fixerService.getLatestCurrencyRates(from, to, amount, showEuroBase)));
	}

	@POST
	@RequestMapping("/convert/currency")
	public ResponseEntity<ConvertCurrencyResponse> convertCurrency(
			@RequestHeader Map<String, String> headers,
			@RequestBody ConvertCurrencyRequest request,
			@QueryParam("showEuroBase") boolean showEuroBase) {

		request.setFrom(cleaupParameter(request.getFrom()));
		request.setTo(cleaupParameter(request.getTo()));

		validateRequest(headers, request.getFrom(), request.getTo(), request.getAmount());
		return ResponseEntity.ok(mapToModel(this.fixerService.getLatestCurrencyRates(request.getFrom(), request.getTo(),
				request.getAmount(), showEuroBase)));
	}

	/*
	 * Helper Methods
	 */
	
	private String cleaupParameter(String value) {
		return value.split(",")[value.split(",").length-1];
	}

	private ConvertCurrencyResponse mapToModel(ExchangeResponseDTO dtoResponse) {
		return new ConvertCurrencyResponse(dtoResponse.getTime().toString(), dtoResponse.getFrom(), dtoResponse.getTo(),
				dtoResponse.getAmount(), dtoResponse.getRate(), dtoResponse.getConvertResult(),
				mapToModelEurResponse(dtoResponse.getEuroBasedRates()));
	}

	private EuroBasedRates mapToModelEurResponse(EuroBasedRatesDTO e) {
		if (e == null)
			return null;
		return new EuroBasedRates(e.getBase(), e.getDateTime() != null ? e.getDateTime().toString() : null,
				e.getRates());
	}

	private void validateRequest(Map<String, String> headers, String from, String to, Double amount) {

		if ((headers.get("api-key") == null || !headers.get("api-key").equals(this.fixerSettings.getGatewayApiKey()))
				&& (headers.get("auth-type") == null || !headers.get("auth-type").equalsIgnoreCase("internal"))
				) {
				throw new FixerException(ResponseCode.AUTHENTICATION_FAILURE, HttpStatus.UNAUTHORIZED);
		}

		if (Util.isEmpty(from) || Util.isEmpty(to) || from.length() > 3 || to.length() > 3) // ISO 4217 standard 3 letters
			throw new FixerException(ResponseCode.INVALID_CURRENCY_CODE, HttpStatus.BAD_REQUEST);

		if (amount == null || amount == 0)
			throw new FixerException(ResponseCode.MISSING_AMOUNT);

	}

}
