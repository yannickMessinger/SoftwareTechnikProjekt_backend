package de.hsrm.mi.swt02.backend.api.player;

import de.hsrm.mi.swt02.backend.api.player.repository.PlayerRepository;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testable
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    private List<Player> initDB() {
        //init DB to Test service methods
        playerRepository.deleteAll();
        List<Player> playerList = new ArrayList<>();

        for (var name : List.of("Yannick, Marc, Lena, Hans, Flo")) {
            Player player = new Player(name, "password");
            player = playerRepository.save(player);
            playerList.add(player);
        }
        return playerList;
    }

    @Test
    void preTest() {
        assertThat(playerService).isNotNull();
        assertThat(playerRepository).isNotNull();
    }

    @Test
    void createPlayersTest() {
        //create player with Service
        playerRepository.deleteAll();

        Player yannick = playerService.createPlayer("Yannick", "password");
        Player marc = playerService.createPlayer("Marc", "password");
        Player lena = playerService.createPlayer("Lena", "password");
        Player hans = playerService.createPlayer("Hans", "password");

        //test
        assertThat(playerRepository.count()).isEqualTo(4);

        assertThat(playerRepository.findById(yannick.getId()).orElseThrow()).isEqualTo(yannick);
        assertThat(playerRepository.findById(marc.getId()).orElseThrow()).isEqualTo(marc);
        assertThat(playerRepository.findById(lena.getId()).orElseThrow()).isEqualTo(lena);
        assertThat(playerRepository.findById(hans.getId()).orElseThrow()).isEqualTo(hans);
    }

    @Test
    void deletePlayersTest() {
        //Setup
        List<Player> playerList = initDB();

        //Test
        for (var player : playerList) {
            playerService.deletePlayer(player.getId());
            assertThat(playerRepository.findById(player.getId()).isPresent()).isFalse();
        }
        assertThat(playerRepository.count()).isZero();
    }

    @Test
    void findAllPlayersTest() {
        //Setup
        List<Player> playerList = initDB();
        List<Player> playerListFromService = playerService.findAllPlayers();

        Iterator<Player> iterator = playerList.iterator();

        //Test
        for (var playerFromService : playerListFromService) {
            Player player = iterator.next();

            assertThat(playerFromService.getUserName()).isEqualTo(player.getUserName());
            assertThat(playerFromService.getId()).isEqualTo(player.getId());
        }
    }

    @Test
    void findPlayersByIdTest() {
        //Setup
        List<Player> playerList = initDB();

        //Test
        for (var player : playerList) {
            assertThat(playerService.findPlayerById(player.getId())).isEqualTo(player);
        }
    }
}
