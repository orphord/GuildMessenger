package com.orford.guild.messenger.GuildMessenger.infrastructure;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDatabaseAdapter implements MessageDatabase {

    private MessageRepository messageRepository;
    public MessageDatabaseAdapter(MessageRepository _messageRepository) {
        messageRepository = _messageRepository;
    }

    @Override
    public Long save(Message message) {
        return messageRepository.save(message).getIdAsLong();

    }
}
