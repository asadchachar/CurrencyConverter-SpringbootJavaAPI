package io.fixer.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.fixer.api.adapter.FixerAdapter;
import io.fixer.api.dto.EuroBasedRatesDTO;
import io.fixer.api.dto.ExchangeResponseDTO;
import io.fixer.api.service.FixerService;

@RunWith(SpringRunner.class)
@SpringBootTest
class FixerTests {

	private FixerAdapter fixerAdapter;

	@Autowired
	private FixerService fixerService;

	@Test
	public void convert() {
		Map<String, Double> rates = new HashMap<String, Double>();
		rates.put("NOK", 10d);
		rates.put("USD", 1d);

		when(fixerAdapter.getEuroBaseRates("NOK", "USD"))
				.thenReturn(new EuroBasedRatesDTO("EUR", LocalDateTime.parse("2021-02-28T00:00:00.000"), rates));

		ExchangeResponseDTO conversion = fixerService.getLatestCurrencyRates("NOK", "USD", 20d, false);
		assertEquals("NOK", conversion.getFrom());
		assertEquals("USD", conversion.getTo());
		assertEquals(LocalDateTime.parse("2021-02-28T00:00:00.000"), conversion.getTime());
		
	}

}
