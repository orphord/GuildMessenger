package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessageHandler;

import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessengerFacade implements AddMessageHandler {
    private final MessageDatabase messageDatabase;
    private final MessageSender messageSender;

    @Override
    public boolean handle(AddMessageCommand addMessageCommand) {
        log.info("Message received in MessengerFacade.handle: " + addMessageCommand.toString());
        Message inMessage = messageFromAddMessageCommand(addMessageCommand);
        log.info("Message to be inserted: " + inMessage);
        Long insertedId = messageDatabase.save(inMessage);
        boolean messageSentSuccessfully = false;
        if(insertedId != null) {
            messageSentSuccessfully = messageSender.sendMessage(inMessage);
        }
        // At this point would be some error checking with compensating calls in case of error

        log.info("ID of log message inserted: {}", insertedId);
        log.info("Message {} sent successfully: {}", insertedId, messageSentSuccessfully);

        return messageSentSuccessfully && insertedId != null;
    }

    private Message messageFromAddMessageCommand(AddMessageCommand addMessageCommand) {
        Message inMessage = new Message();
        inMessage.setMessageText(addMessageCommand.getMessage());
        inMessage.setSenderId(addMessageCommand.getSenderId());
        inMessage.setReceiverId(addMessageCommand.getReceiverId());

        return inMessage;
    }
}
