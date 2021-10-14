package com.orford.guild.messenger.GuildMessenger.core;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessengerFacade implements AddMessage {

    @Override
    public void handle(AddMessageCommand addMessageCommand) {
        log.info("Message received in MessengerFacade.handle: " + addMessageCommand.toString());
    }
}
