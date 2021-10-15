package com.orford.guild.messenger.GuildMessenger;

import com.orford.guild.messenger.GuildMessenger.core.MessengerFacade;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessage;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import com.orford.guild.messenger.GuildMessenger.infrastructure.MessageDatabaseAdapter;
import com.orford.guild.messenger.GuildMessenger.infrastructure.MessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerConfig {
    @Bean
    public AddMessage addMessage(MessageDatabase messageDatabase) { return new MessengerFacade(messageDatabase); }

    @Bean
    MessageDatabase messageDatabase(MessageRepository messageRepository) {
        return new MessageDatabaseAdapter(messageRepository);
    }

}
