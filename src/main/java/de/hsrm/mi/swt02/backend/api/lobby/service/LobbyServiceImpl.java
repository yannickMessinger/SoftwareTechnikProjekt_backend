package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LobbyServiceImpl implements LobbyService {

    Logger logger = LoggerFactory.getLogger(LobbyServiceImpl.class);
    @Autowired
    private LobbyRepository lobbyRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MapService mapService;

    /**
     * returns list of all existing lobbys
     */
    @Override
    @Transactional
    public List<Lobby> findAllLobbys () {

        List<Lobby> lobbys = lobbyRepository.findAll();

        if (lobbys.isEmpty()) {
            logger.warn("DB is empty.. found no Lobby´s");
        }

        return lobbys;
    }

    /**
     * return single lobby by given id
     * @param id id of the lobby that is supposed to be returned
     */
    @Override
    @Transactional
    public Lobby findLobbyById (long id) {

        Optional<Lobby> foundLobby = lobbyRepository.findById(id);

        if (foundLobby.isEmpty()) {
            logger.warn("No Lobby with given ID was found");
        }

        return foundLobby.orElseThrow();
    }

    /**
     * deletes single lobby by given id, cuts existing relations before
     * @param id id of the lobby that is supposed to be deleted
     */
    @Override
    @Transactional
    public void deleteLobby (long id) {

        Lobby delLobby = this.findLobbyById(id);


        for (Player player : delLobby.getPlayerList()) {
            player.setActiveLobby(null);
        }

        lobbyRepository.deleteById(id);
    }

    /**
     * creates single lobby by handed parameters and adds / creates necessary relations to other entitiys
     * creates lobby without map existing
     * @param lobbyName name of the lobby assigned by creator / host
     * @param lobbyMode value of mode that lobby was set to, either play oder buildmode
     * @param numOfPlayers amount of players that are in lobby
     * @param hostId id of host of the lobby
     */
    @Override
    @Transactional
    public long createLobby (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId) {
        
        Lobby createLobby = new Lobby(lobbyName, numOfPlayers, lobbyMode);
        createLobby = lobbyRepository.save(createLobby);

        Player host = playerService.findPlayerById(hostId);
        host.AddHostToHostedLobbyList(createLobby);
        createLobby.setHost(host);


        Map test = mapService.createNewMap();
        mapService.assignLobbyToMap(test.getId(), createLobby.getId());
        test.setMapOwner(host);
        mapService.saveEditedMap(test);


        return createLobby.getId();
    }

    /**
     * creates single lobby by handed parameters and adds / creates necessary relations to other entitiys
     * creates lobby with map existing and adds map to lobby and manages necessary relations.
     * @param lobbyName name of the lobby assigned by creator / host
     * @param lobbyMode value of mode that lobby was set to, either play oder buildmode
     * @param numOfPlayers amount of players that are in lobby
     * @param hostId id of host of the lobby
     */
    @Override
    @Transactional
    public long createLobbyWithMap (String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId, long mapId) {
       
        Lobby createLobby = new Lobby(lobbyName, numOfPlayers, lobbyMode);
        createLobby = lobbyRepository.save(createLobby);

        Player host = playerService.findPlayerById(hostId);
        host.AddHostToHostedLobbyList(createLobby);
        createLobby.setHost(host);
        Map map = mapService.getMapById(mapId);
        mapService.assignLobbyToMap(mapId, createLobby.getId());
        map.setMapOwner(host);
        mapService.saveEditedMap(map);
        return createLobby.getId();
    }

   

    /**
     * Find Player and Lobby by id and maintain the relations.
     *
     * @param lobbyId  from Lobby
     * @param playerId from Player
     */
    @Override
    @Transactional
    public void addPlayerToLobby (long lobbyId, long playerId) {
        Player player = playerService.findPlayerById(playerId);
        Lobby lobby = this.findLobbyById(lobbyId);
        player.setActiveLobby(lobby);
        lobby.addPlayerToPlayerlist(player);
        lobbyRepository.save(lobby);
    }

    /**
     * Find Player and Lobby by id and remove the relations.
     *
     * @param lobbyId  from Lobby
     * @param playerId from Player
     */
    @Override
    @Transactional
    public void removePlayerFromLobby (long lobbyId, long playerId) {
        Player player = playerService.findPlayerById(playerId);
        Lobby lobby = this.findLobbyById(lobbyId);
        player.setActiveLobby(null);
        if (lobby.isHostedBy(player.getId())) {
            this.deleteLobby(lobbyId);
            return;
        }
        lobby.getPlayerList().remove(player);
        lobbyRepository.save(lobby);
    }

    /**
     * Find Lobby by lobbyId and get all Players from Lobby
     *
     * @param lobbyId from Lobby
     * @return list of Players
     */
    @Override
    public List<Player> findAllPlayersFromLobby (long lobbyId) {
        return this.findLobbyById(lobbyId).getPlayerList();
    }

    /**
     * adds an available map to a lobby, both found by the id
     *
     * @param lobbyId id of lobby
     * @param mapId   id of map
     * @return id of map
     */
    @Override
    @Transactional
    public long addMap (long lobbyId, long mapId) {
        Lobby lobby = findLobbyById(lobbyId);
        Map map = mapService.getMapById(mapId);

        if (lobby.getMap() != null)
            lobby.getMap().setLobby(null);

        if (map.getLobby() != null)
            map.getLobby().setMap(null);

        lobby.setMap(map);
        map.setLobby(lobby);
        lobbyRepository.save(lobby);
        return map.getId();
    }

    /**
     * method to update lobbymode of existing lobby via messagebroker
     * @param id id of lobby which mode is supposed to be switched
     * @param lobbyMode lobbymode that lobby is supposed to be switched to
     */
    @Override
    @Transactional
    public void updateLobbyModeBroker (long id, LobbyModeEnum lobbyMode) {
        Lobby updateLobby = this.findLobbyById(id);
        updateLobby.setLobbyMode(lobbyMode);
        this.saveEditedLobby(updateLobby);


    }

    /**
     * method to trigger save of handed lobby
     * @param lobby lobby that is supposed to be saved
     */
    @Override
    @Transactional
    public void saveEditedLobby (Lobby lobby) {
        this.lobbyRepository.save(lobby);

    }


}
