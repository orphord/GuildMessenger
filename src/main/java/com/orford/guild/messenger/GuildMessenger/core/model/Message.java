package com.orford.guild.messenger.GuildMessenger.core.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Slf4j
@Data
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;

	@Column(name = "sender_id")
	private String senderId;

	@Column(name = "receiver_id")
	private String receiverId;

	@Column(name = "message_text")
	private String messageText;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	private Timestamp createTime;

	public Message() {}

	public Long getIdAsLong() {
		return id;
	}
}
