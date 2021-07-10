package com.jonathanmakunga.server.chat.db.model;

import com.jonathanmakunga.server.chat.db.Status;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "chat_server")
@Data
public class ServerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String ip;

    @NonNull
    @Column(length=4, nullable=false, unique=false)
    private int port;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    protected ServerEntity() {}

    public ServerEntity(String ip, int port, Status status) {
        this.ip = ip;
        this.port = port;
        this.status = status;
    }
}
