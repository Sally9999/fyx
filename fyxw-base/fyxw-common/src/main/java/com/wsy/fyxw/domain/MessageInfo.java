package com.wsy.fyxw.domain;

public class MessageInfo extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6025305762674796422L;
	private String sender;
	private String senderName;
	private String receiver;
	private String receiverName;
	private String type;
	private String status;
	private String message;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageInfo [sender=" + sender + ", senderName=" + senderName + ", receiver=" + receiver
				+ ", receiverName=" + receiverName + ", type=" + type + ", status=" + status + ", message=" + message
				+ "]";
	}

}
