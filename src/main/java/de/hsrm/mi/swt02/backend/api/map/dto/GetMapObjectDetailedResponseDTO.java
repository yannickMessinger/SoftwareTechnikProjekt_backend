package de.hsrm.mi.swt02.backend.api.map.dto;

public record GetMapObjectDetailedResponseDTO(
    long objectTypeId,
    long groupId,
    String type,
    String name,
    int rotation,
    String texture,
    String model3d
) {
    
}
