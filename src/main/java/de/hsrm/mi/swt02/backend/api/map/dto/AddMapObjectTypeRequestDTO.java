package de.hsrm.mi.swt02.backend.api.map.dto;

import de.hsrm.mi.swt02.backend.domain.map.ObjectTypeEnum;

public record AddMapObjectTypeRequestDTO(
        long object_Id,
        String object_Name,
        String img,
        ObjectTypeEnum type
) {}
