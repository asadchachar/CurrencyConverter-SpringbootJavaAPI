package io.fixer.api.exception;

public enum ResponseCode {

	INVALID_CURRENCY_CODE(400, "Currency Code is missing or not following ISO 4217 standard"),

	MISSING_AMOUNT(400, "Missing Amount"),

	CURRENCY_CODE_NOT_FOUND(404, "Currency Not Found"),

	INVALID_RATES_FIXER(400, "Invalid Rate List from Fixer"),

	AUTHENTICATION_FAILURE(401, "Unauthorized Request")

	;

	private int code;
	private String description;

	private ResponseCode() {
	}

	private ResponseCode(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
