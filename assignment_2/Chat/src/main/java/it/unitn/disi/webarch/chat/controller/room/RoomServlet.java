package it.unitn.disi.webarch.chat.controller.room;

import it.unitn.disi.webarch.chat.helper.RoomStore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RoomServlet extends HttpServlet {

    private final String KEY_REQUESTED_ROOM = "REQUESTED_ROOM";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedURI = request.getRequestURI();
        StringTokenizer uriTokenizer = new StringTokenizer(requestedURI, "/");
        // ignore first token
        uriTokenizer.nextToken();

        if (uriTokenizer.hasMoreTokens()) {
            String requestedRoom = uriTokenizer.nextToken();
            System.out.println("RoomServlet - The requested room is " + requestedRoom);

            if (RoomStore.hasRoom(requestedRoom)) {
                request.setAttribute(this.KEY_REQUESTED_ROOM, requestedRoom);
                this.getServletContext()
                        .getRequestDispatcher("/views/Room.jsp")
                        .forward(request, response);
            } else {
                // Room does not exist
                System.out.println("RoomServlet - Room " + requestedRoom + " does not exist");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");

        if (message != null && message.length() >= 1) {
            // add message to chat and reload
        } else {
            // Do nothing, just show the page
        }
    }
}
