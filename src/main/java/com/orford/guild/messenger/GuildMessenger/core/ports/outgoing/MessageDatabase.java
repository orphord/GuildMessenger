package com.orford.guild.messenger.GuildMessenger.core.ports.outgoing;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;

import java.util.List;

public interface MessageDatabase {
    Long save(Message message);
    List<Message> getMessagesBySenderId(String senderId, Integer daysBack);
    List<Message> getMessages(Integer daysBack);
}
