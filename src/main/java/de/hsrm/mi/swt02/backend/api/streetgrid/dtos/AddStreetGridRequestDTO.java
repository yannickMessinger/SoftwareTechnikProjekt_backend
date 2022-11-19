package de.hsrm.mi.swt02.backend.api.streetgrid.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.RowData;

import java.util.List;

public record AddStreetGridRequestDTO(List<AddRowDataDTO> playerGrid){}

    


