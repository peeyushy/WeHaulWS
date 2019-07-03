/**
 * 
 */
package com.wehaul.dto;

import java.time.LocalDateTime;

/**
 * 
 * @author Peeyush
 *
 */
public class BookingDto {

	private String reqId;
	private LocalDateTime reqDateTime;
	private String reqComment;
	private String reqDropLoc;
	private String reqPickupLoc;
	private boolean reqDateTimeFlexi;
	private boolean reqPickupDropFlexi;
	private String reqType;
	private String vType;
	private String lType;
	private String quoteComment;
	private String quote;
	private String quoteOwner;
	private String quoteContact;
	private LocalDateTime quoteReceivedDateTime;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public LocalDateTime getReqDateTime() {
		return reqDateTime;
	}

	public void setReqDateTime(LocalDateTime reqDateTime) {
		this.reqDateTime = reqDateTime;
	}

	public String getReqComment() {
		return reqComment;
	}

	public void setReqComment(String reqComment) {
		this.reqComment = reqComment;
	}

	public String getReqDropLoc() {
		return reqDropLoc;
	}

	public void setReqDropLoc(String reqDropLoc) {
		this.reqDropLoc = reqDropLoc;
	}

	public String getReqPickupLoc() {
		return reqPickupLoc;
	}

	public void setReqPickupLoc(String reqPickupLoc) {
		this.reqPickupLoc = reqPickupLoc;
	}

	public boolean isReqDateTimeFlexi() {
		return reqDateTimeFlexi;
	}

	public void setReqDateTimeFlexi(boolean reqDateTimeFlexi) {
		this.reqDateTimeFlexi = reqDateTimeFlexi;
	}

	public boolean isReqPickupDropFlexi() {
		return reqPickupDropFlexi;
	}

	public void setReqPickupDropFlexi(boolean reqPickupDropFlexi) {
		this.reqPickupDropFlexi = reqPickupDropFlexi;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	public String getlType() {
		return lType;
	}

	public void setlType(String lType) {
		this.lType = lType;
	}

	public String getQuoteComment() {
		return quoteComment;
	}

	public void setQuoteComment(String quoteComment) {
		this.quoteComment = quoteComment;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getQuoteOwner() {
		return quoteOwner;
	}

	public void setQuoteOwner(String quoteOwner) {
		this.quoteOwner = quoteOwner;
	}

	public String getQuoteContact() {
		return quoteContact;
	}

	public void setQuoteContact(String quoteContact) {
		this.quoteContact = quoteContact;
	}

	public LocalDateTime getQuoteReceivedDateTime() {
		return quoteReceivedDateTime;
	}

	public void setQuoteReceivedDateTime(LocalDateTime quoteReceivedDateTime) {
		this.quoteReceivedDateTime = quoteReceivedDateTime;
	}

}
