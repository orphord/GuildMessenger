package com.orford.guild.messenger.GuildMessenger.core.ports.incoming;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;

public interface AddMessageHandler {
    boolean handle(AddMessageCommand addMessageCommand);

}
