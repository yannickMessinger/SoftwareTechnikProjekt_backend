package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos;

import de.hsrm.mi.swt02.backend.api.lobby.LobbyMode;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.StreetPlan;

public record GetStreetPlanResponseDTO(String lobbyName, int numOfPlayers, LobbyMode lobbyMode, int sizeX, int sizeY) {
    public static GetStreetPlanResponseDTO from(StreetPlan streetPlan) {
        return new GetStreetPlanResponseDTO(
                streetPlan.getLobbyName(),
                streetPlan.getNumOfPlayers(),
                streetPlan.getLobbyMode(),
                streetPlan.getSizeX(),
                streetPlan.getSizeY()
        );
    }
}
