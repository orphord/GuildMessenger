package com.orford.guild.messenger.GuildMessenger.infrastructure;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
