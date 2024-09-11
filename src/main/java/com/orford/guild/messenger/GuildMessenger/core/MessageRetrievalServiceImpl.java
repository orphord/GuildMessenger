package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.MessageRetrievalService;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MessageRetrievalServiceImpl implements MessageRetrievalService {
	private final MessageDatabase messageDatabase;

	@Override
	public MessageListResponse getMessages(String senderId) {
		List<Message> messageList;
		if (senderId == null) {
			messageList = messageDatabase.getMessages(null);
		} else {
			messageList = messageDatabase.getMessagesBySenderId(senderId, null);
		}
		MessageListResponse outMessageList = MessageListResponse.fromMessageList(messageList);

		return outMessageList;
	}
}
