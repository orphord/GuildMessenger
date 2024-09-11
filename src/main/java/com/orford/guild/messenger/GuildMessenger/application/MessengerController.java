package com.orford.guild.messenger.GuildMessenger.application;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.model.MessageListResponse;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessageHandler;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.MessageRetrievalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessengerController {
	private final AddMessageHandler addMessageHandler;
	private final MessageRetrievalService messageRetrievalService;

	@PostMapping(value = "addMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addMessage(@RequestBody AddMessageCommand addMessageCommand) {
		log.info("AddMessageEndpoint called." + addMessageCommand.toString());
		addMessageHandler.handle(addMessageCommand);
		return ResponseEntity.ok("Added message successfully.");
	}

	@GetMapping(value = "getMessages")
	public ResponseEntity<MessageListResponse> getMessages(@RequestParam(required = false) String senderId) {
		log.info("GetMessages endpoint called with senderId: " + senderId);
		return ResponseEntity.ok(messageRetrievalService.getMessages(senderId));

	}
}
