package de.hsrm.mi.swt02.backend.api.game.crossroad.service;

import de.hsrm.mi.swt02.backend.api.lobby.service.LobbyServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapObjectServiceImpl;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith (SpringExtension.class)
class CrossroadServiceImplTest {
    @MockBean
    private LobbyServiceImpl lobbyServiceImpl;

    @MockBean
    private MapObjectServiceImpl mapObjectServiceImpl;

    @MockBean
    private MapService mapService;

    @MockBean
    private SqlDataSourceScriptDatabaseInitializer sqlDataSourceScriptDatabaseInitializer;

    @Autowired
    private CrossroadServiceImpl crossroadServiceImpl;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CrossroadServiceImpl#CrossroadServiceImpl()}
     *   <li>{@link CrossroadServiceImpl#getThread()}
     * </ul>
     */
    @Test
    void testConstructor () {
        assertNull((new CrossroadServiceImpl()).getThread());
    }

    /**
     * Method under test: {@link CrossroadServiceImpl#getTrafficLightById(String)}
     */
    @Test
    void testGetTrafficLightById () {
        assertNull(crossroadServiceImpl.getTrafficLightById("42"));
    }
}

