package io.fixer.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixerSettings {

	@Value("${fixer.api.key}")
	private String apiKey;

	@Value("${fixer.euro.base.rates.endpoint}")
	private String euroEndpoint;

	@Value("${gateway.api.key}")
	private String gatewayApiKey;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getEuroEndpoint() {
		return euroEndpoint;
	}

	public void setEuroEndpoint(String euroEndpoint) {
		this.euroEndpoint = euroEndpoint;
	}

	public String getGatewayApiKey() {
		return gatewayApiKey;
	}

	public void setGatewayApiKey(String gatewayApiKey) {
		this.gatewayApiKey = gatewayApiKey;
	}

}
