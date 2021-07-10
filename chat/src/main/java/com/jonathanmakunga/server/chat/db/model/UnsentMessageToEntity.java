package com.jonathanmakunga.server.chat.db.model;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "unsent_msg_to")
@Data
public class UnsentMessageToEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name="_to", nullable=false, unique=false)
    private Long to;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unsent_msg_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UnsentMessageEntity unsentMessageEntity;

    protected UnsentMessageToEntity() {}

    public UnsentMessageToEntity(Long to, UnsentMessageEntity unsentMessageEntity) {
        this.to = to;
        this.unsentMessageEntity = unsentMessageEntity;
    }
}
