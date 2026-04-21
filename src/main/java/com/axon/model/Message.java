package com.axon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_id", unique = true, nullable = false)
    private UUID messageId;

    @Column(name = "message_text")
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
