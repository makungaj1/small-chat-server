package com.jonathanmakunga.server.chat.db.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "chat_server")
@Data
public class ServerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String ip;
    private int port;
    private String status;

    protected ServerEntity() {}

    public ServerEntity(String ip, int port, String status) {
        this.ip = ip;
        this.port = port;
        this.status = status;
    }
}
