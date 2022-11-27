package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos;

import de.hsrm.mi.swt02.backend.api.lobby.LobbyMode;

public record AddStreetPlanRequestDTO(String lobbyName, int numOfPlayers, LobbyMode lobbyMode, int sizeX, int sizeY) {

}
