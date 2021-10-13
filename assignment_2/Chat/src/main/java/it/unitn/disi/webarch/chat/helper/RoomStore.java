package it.unitn.disi.webarch.chat.helper;

import it.unitn.disi.webarch.chat.models.room.Room;

import java.util.*;

public class RoomStore extends ObjectStore<Room> {

    private static RoomStore instance = null;

    public static RoomStore getInstance() {
        if (instance == null) {
            instance = new RoomStore();
        }
        return instance;
    }

    public Room getRoom(String roomName) {
        Optional<Room> firstRoom = this.get(room -> room.getName().equals(roomName));

        if (firstRoom.isPresent()) {
            return firstRoom.get();
        } else {
            return null;
        }
    }

}
