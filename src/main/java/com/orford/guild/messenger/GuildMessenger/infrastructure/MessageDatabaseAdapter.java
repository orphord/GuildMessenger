package com.orford.guild.messenger.GuildMessenger.infrastructure;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

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

    @Override
    public List<Message> getMessagesBySenderId(String senderId) {
        Instant limitDaysFromNow = Instant.now().plus(30, ChronoUnit.DAYS);;
        return messageRepository.findMessageBySenderIdAndCreateTimeBefore(senderId,Timestamp.from(limitDaysFromNow));
    }

    @Override
    public List<Message> getMessages() {
        Instant limitDaysFromNow = Instant.now().plus(30, ChronoUnit.DAYS);;
        return messageRepository.findMessageByCreateTimeBefore(Timestamp.from(limitDaysFromNow));
    }


}
