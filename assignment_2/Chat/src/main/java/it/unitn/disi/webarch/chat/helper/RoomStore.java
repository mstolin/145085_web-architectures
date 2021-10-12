package it.unitn.disi.webarch.chat.helper;

import it.unitn.disi.webarch.chat.models.room.Room;

import java.util.*;

public class RoomStore {

    private static Set<Room> roomStore = new HashSet<Room>();

    public static void addRoom(String roomName) {
        Room room = new Room(roomName);
        roomStore.add(room);
    }

    public static Set<Room> getAllRooms() {
        return roomStore;
    }

    public static boolean hasRoom(String roomName) {
        Room room = new Room(roomName);
        return roomStore.contains(room);
    }

}
