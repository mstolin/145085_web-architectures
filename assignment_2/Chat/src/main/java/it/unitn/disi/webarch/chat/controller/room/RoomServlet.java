package it.unitn.disi.webarch.chat.controller.room;

import it.unitn.disi.webarch.chat.helper.RoomStore;
import it.unitn.disi.webarch.chat.models.room.Message;
import it.unitn.disi.webarch.chat.models.room.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RoomServlet extends HttpServlet {

    private final String KEY_ACTIVE_ROOM = "activeRoom";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedURI = request.getRequestURI();
        String requestedRoom = this.getRequestedRoomName(requestedURI);

        if (requestedRoom != null && requestedRoom.length() >= 1) {
            System.out.println("INFO (RoomServlet) - The requested room is " + requestedRoom);

            Room room = RoomStore.getInstance().getRoom(requestedRoom);
            if (room != null) {
                request.setAttribute(this.KEY_ACTIVE_ROOM, room);
                this.getServletContext()
                        .getRequestDispatcher("/views/Room.jsp")
                        .forward(request, response);
            } else {
                // Room does not exist
                System.out.println("ERROR (RoomServlet) - Room " + requestedRoom + " does not exist");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            // No room requested
            System.out.println("ERROR (RoomServlet) - No valid room requested");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedURI = request.getRequestURI();
        String requestedRoom = this.getRequestedRoomName(requestedURI);

        if (requestedRoom != null && requestedRoom.length() >= 1) {
            Room room = RoomStore.getInstance().getRoom(requestedRoom);
            if (room != null) {
                String messageText = request.getParameter("message");

                if (messageText != null && messageText.length() >= 1) {
                    // add message to chat and reload
                    Message message = new Message(messageText, "", "user1");
                    room.addMessage(message);
                }
            } else {
                // Room does not exist
                System.out.println("ERROR (RoomServlet) - Room " + requestedRoom + " does not exist");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            // No room requested
            System.out.println("ERROR (RoomServlet) - No valid room requested");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
    private String getRequestedRoomName(String uri) {
        StringTokenizer uriTokenizer = new StringTokenizer(uri, "/");
        // ignore first token
        uriTokenizer.nextToken();

        if (uriTokenizer.hasMoreTokens()) {
            return uriTokenizer.nextToken();
        } else {
            return null;
        }
    }
    
}
