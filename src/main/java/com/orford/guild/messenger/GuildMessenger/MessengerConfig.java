package com.orford.guild.messenger.GuildMessenger;

import com.orford.guild.messenger.GuildMessenger.core.MessengerFacade;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerConfig {
    @Bean
    public AddMessage addMessage() { return new MessengerFacade(); }

}
