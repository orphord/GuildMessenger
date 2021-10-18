package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessageHandler;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.MessageRetrievalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MessengerFacadeTest {

    @Autowired
    private AddMessageHandler addMessageHandler;

    @Autowired
    MessageRetrievalService retrievalService;

    private static AddMessageCommand messageCommand;
    private static int numInserted = 0;

    @BeforeEach
    void setup() {
        log.info("Setup() called.");
        messageCommand = new AddMessageCommand();
        messageCommand.setSenderId("orphord");
        messageCommand.setReceiverId("bplantico");
        messageCommand.setMessage("Well hello there");
        addMessageHandler.handle(messageCommand);
        numInserted++;
        messageCommand.setMessage("Well hello again");
        addMessageHandler.handle(messageCommand);
        numInserted++;

    }

    @Test
    void testIncomingMessageHandle() {
        log.info("testIncomingMessageHandle called.");
        boolean success = addMessageHandler.handle(messageCommand);
        numInserted++;
        Assertions.assertTrue(success, "Should have been successful.");
    }

    @Test
    void testGetAllMessages() {
        log.info("Test get all messages.");
        MessageListResponse listResponse = retrievalService.getMessages(null);
        log.info("-- Number of messages returned: {}", listResponse.getMessageResponses().size());

        Assertions.assertEquals(numInserted, listResponse.getMessageResponses().size(), "Should have been " + numInserted + " messages returned.");
    }

    @Test
    void testGetMessagesBySenderId () {
        log.info("Test get messages by sender ID.");
        messageCommand.setSenderId("lester");
        boolean success = addMessageHandler.handle(messageCommand);
        numInserted++;
        MessageListResponse listResponse = retrievalService.getMessages("orphord");
        log.info("++ Number of messages returned: {}", listResponse.getMessageResponses().size());

        Assertions.assertEquals(numInserted -1 , listResponse.getMessageResponses().size(), "All messages but 1 added by orphord should have been" + numInserted + " messages returned.");

    }
}