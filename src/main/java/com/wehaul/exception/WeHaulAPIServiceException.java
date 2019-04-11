/**
 * 
 */
package com.wehaul.exception;

import org.springframework.http.HttpStatus;

/**
 * @author as.singh
 *
 */
public class WeHaulAPIServiceException extends Exception {

	private int statusCode;
	private String userMessage;

	public WeHaulAPIServiceException() {

	}

	public WeHaulAPIServiceException(int scStatusCode, String message) {
		this.setStatusCode(scStatusCode);
		this.setUserMessage(message);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public HttpStatus getHttpStatusCode() {
		return HttpStatus.valueOf(this.statusCode);
	}

}
