package it.unitn.disi.webarch.chat.models.room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Room implements Serializable {

    private final String name;

    private List<Message> messages;

    public Room() {
        this.name = null;
        this.messages = null;
    }

    public Room(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        // always sort
        this.messages.sort(Comparator.comparing(Message::getDate).reversed());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Room) {
            Room otherRoom = (Room)obj;
            return otherRoom.equals(otherRoom.getName());
        } else if (obj instanceof String) {
            String roomName = (String)obj;
            return roomName.equals(this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int somePrime = 31;
        return this.name.hashCode() + somePrime;
    }
}
