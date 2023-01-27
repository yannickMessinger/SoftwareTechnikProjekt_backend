package de.hsrm.mi.swt02.backend.websocket.model.lobby;

import de.hsrm.mi.swt02.backend.api.lobby.dto.GetLobbyResponseDTO;
import de.hsrm.mi.swt02.backend.api.player.dto.GetPlayerResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LobbyMessage {
    public GetPlayerResponseDTO playerContent;
    public GetLobbyResponseDTO lobbyContent;
    private MessageType type;

    public enum MessageType {
        JOIN,
        SWITCH_MODE,
        SWITCH_MAP,
        LEAVE,
        CLOSE,
        CREATE,
        DRIVE
    }
}
