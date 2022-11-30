package de.hsrm.mi.swt02.backend.api.maps.dtos;

import de.hsrm.mi.swt02.backend.api.player.Player;

import java.time.LocalDate;
import java.util.Date;

public record AddMapRequestDTO(
        String mapName,
        LocalDate date,
        Player owner) {}
