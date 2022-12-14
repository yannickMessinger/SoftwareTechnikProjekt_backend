package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.MapObject;

import java.util.List;

public record AddMapObjectsRequestDTO(
        List<AddMapObjectRequestDTO> mapObjects
) {}
