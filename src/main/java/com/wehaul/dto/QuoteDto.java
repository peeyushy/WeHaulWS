package com.wehaul.dto;

import java.time.LocalDateTime;

/**
 * 
 * @author Peeyush
 *
 */
public class QuoteDto {

	private String qId;
	private String qOwnerId;
	private String qOwnerName;
	private String quote;
	private String qComment;
	private LocalDateTime qDatetime;

	/**
	 * @return the qId
	 */
	public String getqId() {
		return qId;
	}

	/**
	 * @param qId the qId to set
	 */
	public void setqId(String qId) {
		this.qId = qId;
	}

	/**
	 * @return the qOwnerId
	 */
	public String getqOwnerId() {
		return qOwnerId;
	}

	/**
	 * @param qOwnerId the qOwnerId to set
	 */
	public void setqOwnerId(String qOwnerId) {
		this.qOwnerId = qOwnerId;
	}

	/**
	 * @return the qOwnerName
	 */
	public String getqOwnerName() {
		return qOwnerName;
	}

	/**
	 * @param qOwnerName the qOwnerName to set
	 */
	public void setqOwnerName(String qOwnerName) {
		this.qOwnerName = qOwnerName;
	}

	/**
	 * @return the quote
	 */
	public String getQuote() {
		return quote;
	}

	/**
	 * @param quote the quote to set
	 */
	public void setQuote(String quote) {
		this.quote = quote;
	}

	/**
	 * @return the qComment
	 */
	public String getqComment() {
		return qComment;
	}

	/**
	 * @param qComment the qComment to set
	 */
	public void setqComment(String qComment) {
		this.qComment = qComment;
	}

	/**
	 * @return the qDatetime
	 */
	public LocalDateTime getqDatetime() {
		return qDatetime;
	}

	/**
	 * @param qDatetime the qDatetime to set
	 */
	public void setqDatetime(LocalDateTime qDatetime) {
		this.qDatetime = qDatetime;
	}
}
