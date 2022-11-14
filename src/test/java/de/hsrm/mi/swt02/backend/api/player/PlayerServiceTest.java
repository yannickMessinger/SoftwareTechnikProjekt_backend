package de.hsrm.mi.swt02.backend.api.player;

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

    @Test
    void PreTest() {
        assertThat(playerService).isNotNull();
        assertThat(playerRepository).isNotNull();
    }

    private List<Player> initDB() {
        //init DB to Test service methods
        playerRepository.deleteAll();
        List<Player> playerList = new ArrayList<>();

        for (var name : List.of("Yannick, Marc, Lena, Hans, Flo")) {
            Player player = new Player(name);
            player = playerRepository.save(player);
            playerList.add(player);
        }
        return playerList;
    }

    @Test
    void CreateUsersTest() {
        //create player with Service
        Player yannick = playerService.createPlayer("Yannick");
        Player marc = playerService.createPlayer("Marc");
        Player lena = playerService.createPlayer("Lena");
        Player hans = playerService.createPlayer("Hans");

        //test
        assertThat(playerRepository.count()).isEqualTo(4);

        assertThat(playerRepository.findById(yannick.getId()).orElseThrow()).isEqualTo(yannick);
        assertThat(playerRepository.findById(marc.getId()).orElseThrow()).isEqualTo(marc);
        assertThat(playerRepository.findById(lena.getId()).orElseThrow()).isEqualTo(lena);
        assertThat(playerRepository.findById(hans.getId()).orElseThrow()).isEqualTo(hans);
    }

    @Test
    void DeleteUserTest() {
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
    void FindAllUsersTest() {
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
    void FindUserByIdTest() {
        //Setup
        List<Player> playerList = initDB();

        //Test
        for (var player : playerList) {
            assertThat(playerService.findPlayerById(player.getId())).isEqualTo(player);
        }
    }
}
