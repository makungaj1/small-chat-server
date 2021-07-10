package com.jonathanmakunga.server.chat.db;

import com.jonathanmakunga.server.chat.db.model.ServerEntity;
import com.jonathanmakunga.server.chat.db.constant.ServerConstant;
import com.jonathanmakunga.server.chat.db.repository.ServerEntityRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ServerEntityRepository repository;

    @BeforeEach
    void setUp() {
        this.entityManager.persist(new ServerEntity("192.168.0.8", 8000, ServerConstant.UP));
    }

    @Test
    void testDBSave_tableContainsInsertedRow() {
        Iterable<ServerEntity> serverEntities = repository.findAll();
        assertThat(serverEntities.iterator().hasNext()).isTrue();
    }

    @Test
    void testServerIsUp() {
        Iterable<ServerEntity> serverEntities = repository.findAll();
        assertThat(serverEntities.iterator().next().getStatus()).isEqualTo(ServerConstant.UP);
    }
}
