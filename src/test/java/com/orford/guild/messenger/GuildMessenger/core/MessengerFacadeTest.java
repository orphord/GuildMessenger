package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;

import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;
import com.orford.guild.messenger.GuildMessenger.core.model.MessageResponse;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessageHandler;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
class MessengerFacadeTest {
	@Mock
	MessageDatabase mockMsgDatabase;
	@Mock
	MessageSender mockMsgSender;

	private AddMessageHandler addMessageHandler;

	private static AddMessageCommand messageCommand;
	private static int numInserted = 0;

	@BeforeEach
	void setup() {
		log.info("Setup() called.");
		addMessageHandler = new MessengerFacade(mockMsgDatabase, mockMsgSender);

		when(mockMsgSender.sendMessage(any())).thenReturn(true);
//		final List<Message> mockMessageResponse = new ArrayList<>(2);
//		mockMessageResponse.add(msg1);
//		mockMessageResponse.add(msg2);
		//when(mockMsgDatabase.save(any())).thenReturn(mockMessageResponse);
		doReturn(2L).when(mockMsgDatabase).save(any());

	}

	@Test
	void testIncomingMessageHandle() {
		log.info("testIncomingMessageHandle called.");
		messageCommand = new AddMessageCommand();
		messageCommand.setMessage("Message 1");
		messageCommand.setSenderId("orphord");
		messageCommand.setReceiverId("hammer");
		boolean success = addMessageHandler.handle(messageCommand);
		numInserted++;
		Assertions.assertTrue(success, "Should have been successful.");
	}

//	@Test
//	void testGetAllMessages() {
//		log.info("++++++++ Test get all messages +++++++++");
//		MessageListResponse listResponse = retrievalService.getMessages(null);
//		log.info("-- Number of messages returned: {}", listResponse.getMessageResponses().size());
//
//		Assertions.assertEquals(numInserted, listResponse.getMessageResponses().size(), "Should have been " + numInserted + " messages returned.");
//	}
//
//	@Test
//	void testGetMessagesBySenderId() {
//		log.info("========= Test get messages by sender ID: " + messageCommand.toString());
//		messageCommand.setSenderId("lester");
//		boolean success = addMessageHandler.handle(messageCommand);
//		numInserted++;
//		MessageListResponse listResponse = retrievalService.getMessages("orphord");
//		log.info("++ Number of messages returned: {}", listResponse.getMessageResponses().size());
//		for (MessageResponse msg : listResponse.getMessageResponses()) {
//			log.info("******** MESSAGE: {}", msg.getMessageText());
//		}
}
