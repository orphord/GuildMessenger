package com.orford.guild.messenger.GuildMessenger.core.ports.incoming;

import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;

public interface MessageRetrievalService {
    MessageListResponse getMessages(String senderId);
}
