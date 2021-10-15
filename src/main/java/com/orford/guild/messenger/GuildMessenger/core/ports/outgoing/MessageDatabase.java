package com.orford.guild.messenger.GuildMessenger.core.ports.outgoing;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;

public interface MessageDatabase {
    void save(Message message);
}
