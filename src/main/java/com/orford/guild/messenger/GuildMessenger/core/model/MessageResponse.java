package com.orford.guild.messenger.GuildMessenger.core.model;

import lombok.Data;

@Data
public class MessageResponse {
	private String messageText;
	private String senderId;
	private String receiverId;

	public static MessageResponse fromMessage(Message message) {
		MessageResponse outResponse = new MessageResponse();
		outResponse.messageText = message.getMessageText();
		outResponse.senderId = message.getSenderId();
		outResponse.receiverId = message.getReceiverId();

		return outResponse;
	}
}
