package de.hsrm.mi.swt02.backend.api.lobby.service;

import de.hsrm.mi.swt02.backend.api.lobby.repository.LobbyRepository;
import de.hsrm.mi.swt02.backend.domain.player.Player;
import lombok.extern.java.Log;
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

        logger.warn("No Lobby with given ID was found");

        for (Player player : this.findLobbyById(id).getPlayerList()) {
            player.setActiveLobby(null);
        }

        lobbyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public long createLobby(String lobbyName, LobbyModeEnum lobbyMode, int numOfPlayers, long hostID) {

        Lobby createLobby = new Lobby(lobbyName, numOfPlayers, lobbyMode);

        // DTO aus Frontend anpassen und PlayerID mitschicken der Lobby hosted um Host
        // korrekt zu setzen
        Player host = playerService.findPlayerById(hostID);
        createLobby.setHost(host);

        return lobbyRepository.save(createLobby).getId();
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
        
        if(lobby.isHostOf(player.getId())){
            this.deleteLobby(lobbyId);
            return;
        }
        player.setActiveLobby(null);
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
}
