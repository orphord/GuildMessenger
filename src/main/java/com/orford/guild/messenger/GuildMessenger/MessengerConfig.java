package com.orford.guild.messenger.GuildMessenger;

import com.orford.guild.messenger.GuildMessenger.core.MessageRetrievalServiceImpl;
import com.orford.guild.messenger.GuildMessenger.core.MessengerFacade;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessageHandler;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.MessageRetrievalService;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageDatabase;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageSender;
import com.orford.guild.messenger.GuildMessenger.infrastructure.FileMessageSender;
import com.orford.guild.messenger.GuildMessenger.infrastructure.MessageDatabaseAdapter;
import com.orford.guild.messenger.GuildMessenger.infrastructure.MessageRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerConfig {
    @Bean
    public AddMessageHandler addMessage(MessageDatabase messageDatabase, MessageSender messageSender) { return new MessengerFacade(messageDatabase, messageSender); }

    @Bean
    MessageDatabase messageDatabase(MessageRepository messageRepository) {
        return new MessageDatabaseAdapter(messageRepository);
    }

    @Bean
    MessageRetrievalService retrievalService(MessageDatabase messageDatabase) { return new MessageRetrievalServiceImpl(messageDatabase); }

    @Bean
    MessageSender messageSender() {return new FileMessageSender();}

}
