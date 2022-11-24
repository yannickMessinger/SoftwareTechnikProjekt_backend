package de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.streetConstructionKit.dtos.GetStreetConstructionKitResponseDTO;
import de.hsrm.mi.swt02.backend.api.streetgrid.streetPlan.StreetPlan;

public record GetStreetPlanResponseDTO(String lobbyName, String numOfPlayers, String lobbyMode, String sizeX, String sizeY) {
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
