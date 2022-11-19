package de.hsrm.mi.swt02.backend.api.streetgrid.dtos;

import de.hsrm.mi.swt02.backend.api.streetgrid.gridelements.ColumnData;

import java.util.List;

public record AddRowDataDTO(List<AddColumnDataDTO> columnData) {}
