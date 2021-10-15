package it.unitn.disi.webarch.chat.models.room;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {

    private final String message;
    private final Date date;
    private final String user;

    public Message() {
        this.message = null;
        this.date = new Date();
        this.user = null;
    }

    public Message(String message, String user) {
        this.message = message;
        this.date = new Date();
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getDate() {
        return this.date;
    }

    public String getUser() {
        return this.user;
    }

    public String getFormattedDate() {
        String formattedDate = new SimpleDateFormat("HH:mm:ss")
                .format(this.date);
        return formattedDate;
    }

}
