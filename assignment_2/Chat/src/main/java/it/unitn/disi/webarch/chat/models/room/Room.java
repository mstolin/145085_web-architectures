package it.unitn.disi.webarch.chat.models.room;

public class Room {

    private final String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
