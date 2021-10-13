package it.unitn.disi.webarch.chat.models.room;

import java.io.Serializable;

public class Message implements Serializable {

    private final String message;
    private final String timestamp;
    private final String user;

    public Message() {
        this.message = null;
        this.timestamp = "";
        this.user = null;
    }

    public Message(String message, String timestamp, String user) {
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getUser() {
        return this.user;
    }

}
