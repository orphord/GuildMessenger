package com.orford.guild.messenger.GuildMessenger.infrastructure;

import com.orford.guild.messenger.GuildMessenger.core.model.Message;
import com.orford.guild.messenger.GuildMessenger.core.ports.outgoing.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This is an elementary implementation of a `MessageSender` for demonstration purposes.  In reality I would _expect_ a
 * MessageSender implementation that writes to a Kafka topic or some other way of distributing the messages to recievers.
 */

@Slf4j
public class FileMessageSender implements MessageSender {

    private final Map<String, BufferedWriter> fileWriterMap;

    public FileMessageSender() {
        fileWriterMap = new HashMap<>();
    }

    @PreDestroy
    private void cleanup() {
        log.info("CLEANUP called.");
        for(String id : fileWriterMap.keySet()) {
            try {
                fileWriterMap.get(id).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean sendMessage(Message messageToSend) {
        try {
            String fileName = messageToSend.getReceiverId() + "_MessageFile.txt";
            if(!fileWriterMap.containsKey(messageToSend.getReceiverId())) {
                fileWriterMap.put(messageToSend.getReceiverId(), new BufferedWriter(new FileWriter(fileName, true)));
            }
            BufferedWriter userFileWriter = fileWriterMap.get(messageToSend.getReceiverId());
            log.info("About to write data to file: {}", fileName);

            userFileWriter.write(messageToSend.getSenderId());
            userFileWriter.write("|");
            userFileWriter.write(messageToSend.getMessageText());
            userFileWriter.newLine();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
