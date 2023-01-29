package de.hsrm.mi.swt02.backend.websocket.model.crossroad;

import de.hsrm.mi.swt02.backend.domain.game.trafficLight.Light;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/*
 * CrossroadMessage is a class that holds information about a crossroad.
 * It is used to communicate with other parts of the system, e.g. a web application or a database.
 */
@Setter
@Getter
public class CrossroadMessage {
    /* The type of the message, either CREATE, UPDATE, or DELETE */
    private MessageType type;
    /* The id of the crossroad */
    private Long id;
    /* The traffic lights of the crossroad, represented as a map of id to Light */
    private Map<Long, Light> tl = new HashMap<>();

    /**
     * Constructs a new CrossroadMessage.
     * @param id the id of the crossroad
     * @param tl a map of traffic light id to light state
     * @param type the type of the message
     */
    public CrossroadMessage(long id, Map<Long, Light> tl, MessageType type){
        this.id = id;
        this.tl = tl;
        this. type = type;
    }
    /**
     * The MessageType enum holds the different types of messages that can be sent with a CrossroadMessage.
     */
    public enum MessageType {
        /* Message type indicating that a new crossroad is being created */
        CREATE,
        /* Message type indicating that an existing crossroad is being updated */
        UPDATE,
        /* Message type indicating that an existing crossroad is being deleted */
        DELETE
    }
}
