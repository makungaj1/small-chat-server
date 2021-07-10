package com.jonathanmakunga.server.chat.db;

import com.jonathanmakunga.server.chat.db.model.ServerEntity;
import com.jonathanmakunga.server.chat.db.model.UnsentMessageEntity;
import com.jonathanmakunga.server.chat.db.model.UnsentMessageToEntity;
import com.jonathanmakunga.server.chat.db.repository.ServerEntityRepository;
import com.jonathanmakunga.server.chat.db.repository.UnsentMessageEntityRepository;
import com.jonathanmakunga.server.chat.db.repository.UnsentMessageToEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ServerEntityRepository serverEntityRepository;

    @Autowired
    private UnsentMessageEntityRepository unsentMessageEntityRepository;

    @Autowired
    private UnsentMessageToEntityRepository unsentMessageToEntityRepository;

    @BeforeEach
    void setUp() {
        this.entityManager.persist(new ServerEntity("192.168.0.8", 8000, Status.UP));
        var unsentMessageEntity = this.entityManager.persist(
                new UnsentMessageEntity("1w2u4i8yol", 10L, getRandomByteArray())
        );
        this.entityManager.persist(new UnsentMessageToEntity(11L, unsentMessageEntity));
    }

    @Test
    void testDBSave_serverEntityTableContainsInsertedRow() {
        Iterable<ServerEntity> serverEntities = serverEntityRepository.findAll();
        assertThat(serverEntities.iterator().hasNext()).isTrue();
    }

    @Test
    void testServerIsUp() {
        Iterable<ServerEntity> serverEntities = serverEntityRepository.findAll();
        assertThat(serverEntities.iterator().next().getStatus()).isEqualTo(Status.UP);
    }

    @Test
    void testServerIsNotDown() {
        Iterable<ServerEntity> serverEntities = serverEntityRepository.findAll();
        assertThat(serverEntities.iterator().next().getStatus()).isNotEqualTo(Status.DOWN);
    }

    @Test
    void testDbSave_unsentMessageEntityTableContainsInsertedRow() {
        Iterable<UnsentMessageEntity> unsentMessageEntities= unsentMessageEntityRepository.findAll();
        assertThat(unsentMessageEntities.iterator().hasNext()).isTrue();
    }

    @Test
    void testDbSave_unsentMessageToEntityTableContainsInsertedRow() {
        Iterable<UnsentMessageToEntity> unsentMessageToEntities= unsentMessageToEntityRepository.findAll();
        assertThat(unsentMessageToEntities.iterator().hasNext()).isTrue();
    }

    // TODO: Is this best practice???
    private byte[] getRandomByteArray() {
        byte[] random = new byte[16];
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.nextBytes(random);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return random;
    }
}
