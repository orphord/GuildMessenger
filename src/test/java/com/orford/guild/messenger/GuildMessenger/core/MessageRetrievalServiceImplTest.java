package com.orford.guild.messenger.GuildMessenger.core;


import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.MessageRetrievalService;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MessageRetrievalServiceImplTest {
	@Mock
	MessageDatabase mockMsgDatabase;

	MessageRetrievalService messageRetrievalService;

	@BeforeEach
	void setup() {
		messageRetrievalService = new MessageRetrievalServiceImpl(mockMsgDatabase);
		Message msg1 = new Message();
		msg1.setId(0L);
		msg1.setSenderId("orphord");
		msg1.setReceiverId("ncrane");
		msg1.setMessageText("Message 1");
		Message msg2 = new Message();
		msg2.setId(1L);
		msg2.setSenderId("orphord");
		msg2.setReceiverId("hammer");
		msg2.setMessageText("Message 2");
		List<Message> msgList = new ArrayList<>();
		msgList.add(msg1);
		msgList.add(msg2);
		doReturn(msgList).when(mockMsgDatabase).getMessagesBySenderId("orphord", null);
	}

	@Test
	void testGetMessagesBySenderId() {
		MessageListResponse messageListResponse = messageRetrievalService.getMessages("orphord");
		Assertions.assertTrue(messageListResponse.getMessageResponses().size() == 2,
				"-------- There should have been two messages there!!! --------");
	}
}