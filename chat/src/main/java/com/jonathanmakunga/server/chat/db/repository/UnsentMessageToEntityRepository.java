package com.jonathanmakunga.server.chat.db.repository;

import com.jonathanmakunga.server.chat.db.model.UnsentMessageToEntity;
import org.springframework.data.repository.CrudRepository;

public interface UnsentMessageToEntityRepository extends CrudRepository<UnsentMessageToEntity, Long> {
}
