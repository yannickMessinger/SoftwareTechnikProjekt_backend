package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.api.map.service.MapService;
import de.hsrm.mi.swt02.backend.domain.map.Map;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import de.hsrm.mi.swt02.backend.api.player.service.PlayerService;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import de.hsrm.mi.swt02.backend.domain.lobby.Lobby;
import de.hsrm.mi.swt02.backend.domain.lobby.LobbyModeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyServiceImpl implements LobbyService {

    Logger logger = LoggerFactory.getLogger(LobbyServiceImpl.class);
    @Autowired
    private LobbyRepository lobbyRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MapService mapService;

    @Override
    @Transactional
    public List<Lobby> findAllLobbys() {

        List<Lobby> lobbys = lobbyRepository.findAll();

        if (lobbys.isEmpty()) {
            logger.warn("DB is empty.. found no Lobby´s");
        }

        return lobbys;
    }

    @Override
    @Transactional
    public Lobby findLobbyById(long id) {

        Optional<Lobby> foundLobby = lobbyRepository.findById(id);

        if (foundLobby.isEmpty()) {
            logger.warn("No Lobby with given ID was found");
        }

        return foundLobby.orElseThrow();
    }

    @Override
    @Transactional
    public void deleteLobby(long id) {

        Lobby delLobby = this.findLobbyById(id);

        //logger.warn("No Lobby with given ID was found");
        //Player findPlayer = this.findLobbyById(id).getHost();
        //findPlayer.removeLobbyFromHostedLobbyList(delLobby);
        

        for (Player player : delLobby.getPlayerList()) {
            player.setActiveLobby(null);
        }

        lobbyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long createLobby(String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostId) {
        //todo: wenn bereits exisitierende map mit gegeben wird per id, erst per mapservice finden und setzen!
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

    @Override
    @Transactional
    public void updateLobby(long id) {
        Optional<Lobby> findLobby = lobbyRepository.findById(id);

        // logger.warn("No Lobby with given ID was found");
        if (findLobby.isPresent()) {

        }
    }

    /**
     * Find Player and Lobby by id and maintain the relations.
     * 
     * @param lobbyId  from Lobby
     * @param playerId from Player
     */
    @Override
    @Transactional
    public void addPlayerToLobby(long lobbyId, long playerId) {
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
    public void removePlayerFromLobby(long lobbyId, long playerId) {
        Player player = playerService.findPlayerById(playerId);
        Lobby lobby = this.findLobbyById(lobbyId);
        player.setActiveLobby(null);
        if(lobby.isHostedBy(player.getId())){
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
    public List<Player> findAllPlayersFromLobby(long lobbyId) {
        return this.findLobbyById(lobbyId).getPlayerList();
    }

    /**
     * adds an available map to a lobby, both found by the id
     *
     * @param lobbyId id of lobby
     * @param mapId id of map
     * @return id of map
     */
    @Override
    @Transactional
    public long addMap(long lobbyId, long mapId) {
        Lobby lobby = findLobbyById(lobbyId);
        Map map = mapService.getMapById(mapId);

        if(lobby.getMap() != null)
            lobby.getMap().setLobby(null);

        if(map.getLobby() != null)
            map.getLobby().setMap(null);

        lobby.setMap(map);
        map.setLobby(lobby);
        lobbyRepository.save(lobby);
        return map.getId();
    }

    @Override
    @Transactional
    public void updateLobbyModeBroker(long id, LobbyModeEnum lobbyMode) {
       Lobby updateLobby = this.findLobbyById(id);
       updateLobby.setLobbyMode(lobbyMode);
       this.saveEditedLobby(updateLobby);
      
        
    }

    @Override
    @Transactional
    public void saveEditedLobby(Lobby lobby) {
       this.lobbyRepository.save(lobby);
        
    }


    
}
