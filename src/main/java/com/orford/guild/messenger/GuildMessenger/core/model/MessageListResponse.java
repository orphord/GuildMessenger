package com.orford.guild.messenger.GuildMessenger.core.model;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class MessageListResponse {
    List<MessageResponse> messageResponses;
}
