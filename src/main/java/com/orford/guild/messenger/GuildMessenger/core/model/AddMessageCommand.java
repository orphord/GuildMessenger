package com.orford.guild.messenger.GuildMessenger.core.model;

import lombok.*;

@RequiredArgsConstructor
@Data
public class AddMessageCommand {
    private String message;
}
