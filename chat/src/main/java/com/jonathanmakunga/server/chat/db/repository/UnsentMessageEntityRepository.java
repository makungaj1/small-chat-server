package com.jonathanmakunga.server.chat.db.repository;

import com.jonathanmakunga.server.chat.db.model.UnsentMessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface UnsentMessageEntityRepository extends CrudRepository<UnsentMessageEntity, Long> {
}
