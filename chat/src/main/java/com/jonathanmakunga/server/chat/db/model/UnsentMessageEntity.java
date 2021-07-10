package com.jonathanmakunga.server.chat.db.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "unsent_msg")
@Data
public class UnsentMessageEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name="msg_id", length=100, nullable=false, unique=true)
    private String msgId;

    @NonNull
    @Column(name="_from", nullable=false, unique=false)
    private Long from;

    @NonNull
    @Column(name="msg_body", nullable=false, unique=false)
    private byte[] msgBody;

    protected UnsentMessageEntity() {}

    public UnsentMessageEntity(String msgId, Long from, byte[] msgBody) {
        this.msgId = msgId;
        this.from = from;
        this.msgBody = msgBody;
    }
}
