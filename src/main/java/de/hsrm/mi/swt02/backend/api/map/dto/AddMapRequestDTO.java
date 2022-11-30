package de.hsrm.mi.swt02.backend.api.map.dto;

import java.time.LocalDate;

public record AddMapRequestDTO(
        String mapName,
        LocalDate creationDate,
        int sizeX,
        int sizeY
) {}
