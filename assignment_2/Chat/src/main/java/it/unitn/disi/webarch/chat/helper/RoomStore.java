package it.unitn.disi.webarch.chat.helper;

import java.util.*;

public class RoomStore {

    private static Set<String> roomStore = new HashSet<String>();

    public static void addRoom(String roomName) {
        roomStore.add(roomName);
    }

    public static Set<String> getAllRooms() {
        return roomStore;
    }

    public static boolean hasRoom(String roomName) {
        return roomStore.contains(roomName);
    }

}
