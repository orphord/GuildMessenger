package com.orford.guild.messenger.GuildMessenger.application;

import com.orford.guild.messenger.GuildMessenger.core.model.AddMessageCommand;
import com.orford.guild.messenger.GuildMessenger.core.ports.incoming.AddMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessengerController {
    private final AddMessage addMessage;

    @GetMapping
    public ResponseEntity<AddMessageCommand> test() {
        log.info("Test endpoint called");


        return ResponseEntity.ok(new AddMessageCommand());
    }

    @PostMapping(value = "addMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMessage(@RequestBody AddMessageCommand addMessageCommand) {
        log.info("AddMessageEndpoint called." + addMessageCommand.toString());
        addMessage.handle(addMessageCommand);
        return ResponseEntity.ok("Added message successfully.");
    }
}
