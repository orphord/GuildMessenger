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

    private AddMessageCommand messageCommand;

    @BeforeEach
    void setup() {
        log.info("Setup() called.");
        messageCommand = new AddMessageCommand();
        messageCommand.setSenderId("orphord");
        messageCommand.setReceiverId("bplantico");
        messageCommand.setMessage("Well hello there");

    }

    @Test
    void testIncomingMessageHandle() {
        log.info("testIncomingMessageHandle called.");
        boolean success = addMessageHandler.handle(messageCommand);
        Assertions.assertTrue(success, "Should have been successful.");
    }

    @Test
    void testGetAllMessages() {
        log.info("Test get all messages.");
        addMessageHandler.handle(messageCommand);
        addMessageHandler.handle(messageCommand);

        MessageListResponse listResponse = retrievalService.getMessages(null);
        log.info("Number of messages returned: {}", listResponse.getMessageResponses().size());
//TODO: Might be 2 or 3 depending on which test runs first. :-/
        Assertions.assertEquals(2, listResponse.getMessageResponses().size(), "Should have been two messages returned.");
    }
}