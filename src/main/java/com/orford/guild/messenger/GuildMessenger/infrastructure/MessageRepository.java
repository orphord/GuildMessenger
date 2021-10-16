package com.orford.guild.messenger.GuildMessenger.infrastructure;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findMessageBySenderIdAndCreateTimeBefore(String senderId, Timestamp timeLimit);

    List<Message> findMessageByCreateTimeBefore(Timestamp timeLimit);
}
