package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessageHandler;

import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.MessageRetrievalService;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MessengerFacade implements AddMessageHandler {
    private final MessageDatabase messageDatabase;

    @Override
    public void handle(AddMessageCommand addMessageCommand) {
        log.info("Message received in MessengerFacade.handle: " + addMessageCommand.toString());
        Message inMessage = messageFromAddMessageCommand(addMessageCommand);
        log.info("Message to be inserted: " + inMessage);
        Long insertedId = messageDatabase.save(inMessage);
        log.info("ID of log message inserted: " + insertedId);
    }

    private Message messageFromAddMessageCommand(AddMessageCommand addMessageCommand) {
        Message inMessage = new Message();
        inMessage.setMessageText(addMessageCommand.getMessage());
        inMessage.setSenderId(addMessageCommand.getSenderId());
        inMessage.setReceiverId(addMessageCommand.getReceiverId());

        return inMessage;
    }
}
