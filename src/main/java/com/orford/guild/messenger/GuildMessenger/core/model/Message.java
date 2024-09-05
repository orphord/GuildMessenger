package com.orford.guild.messenger.GuildMessenger.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
@Table(name="messages")
@Slf4j
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name="sender_id")
    private String senderId;

    @Column(name="receiver_id")
    private String receiverId;

    @Column(name="message_text")
    private String messageText;

    @CreationTimestamp
    @Column(name="create_timestamp")
    private Timestamp createTime;

    public Long getIdAsLong() {
        return id;
    }
}
