package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessage;

import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessengerFacade implements AddMessage {
    private final MessageDatabase messageDatabase;

    @Override
    public void handle(AddMessageCommand addMessageCommand) {
        log.info("Message received in MessengerFacade.handle: " + addMessageCommand.toString());
        Message inMessage = new Message();
        inMessage.setMessageText(addMessageCommand.getMessage());
        log.info("Message to be inserted: " + inMessage);
        messageDatabase.save(inMessage);
    }
}
