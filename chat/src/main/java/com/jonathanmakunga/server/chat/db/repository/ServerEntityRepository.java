package com.jonathanmakunga.server.chat.db.repository;

import com.jonathanmakunga.server.chat.db.model.ServerEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServerEntityRepository extends CrudRepository<ServerEntity, Long> {
}
