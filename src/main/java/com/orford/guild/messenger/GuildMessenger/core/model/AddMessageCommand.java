package com.orford.guild.messenger.GuildMessenger.core.model;

import lombok.*;

@RequiredArgsConstructor
@Data
public class AddMessageCommand {
	private String message;
	private String senderId;
	private String receiverId;

	public String toString() {
		return "{Sender: " + senderId + ", Receiver: " + receiverId + ", Message: " + message + "}";
	}
}
