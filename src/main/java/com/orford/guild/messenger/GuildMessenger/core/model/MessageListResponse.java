package com.orford.guild.messenger.GuildMessenger.core.model;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageListResponse {
	List<MessageResponse> messageResponses;

	public static MessageListResponse fromMessageList(List<Message> messageList) {
		List<MessageResponse> messageResponses = messageList.stream()
				.map(message -> {
					return fromMessage(message);
				}).collect(Collectors.toList());

		MessageListResponse messageListResponse = new MessageListResponse();
		messageListResponse.setMessageResponses(messageResponses);

		return messageListResponse;
	}

	public static MessageResponse fromMessage(Message message) {
		MessageResponse outMessageResponse = new MessageResponse();
		outMessageResponse.setSenderId(message.getSenderId());
		outMessageResponse.setReceiverId(message.getReceiverId());
		outMessageResponse.setMessageText(message.getMessageText());

		return outMessageResponse;
	}

	public void addMessageResponse(MessageResponse messageResponse) {
	}
}
