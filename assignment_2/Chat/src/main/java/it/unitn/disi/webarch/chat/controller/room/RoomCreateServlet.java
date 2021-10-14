package it.unitn.disi.webarch.chat.controller.room;

import it.unitn.disi.webarch.chat.helper.RoomStore;
import it.unitn.disi.webarch.chat.models.room.Room;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RoomCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/RoomCreate.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomName = request.getParameter("name");

        if(roomName != null && roomName.length() >= 1) {
            // safe room as a model
            try {
                String cleanedRoomName = URLEncoder.encode(roomName, "UTF-8");
                System.out.println("INFO (RoomCreateServlet) - User wants to create a room " + cleanedRoomName);
                // create room, only if it does not exist
                Room room = new Room(cleanedRoomName);
                if (!RoomStore.getInstance().has(room)) {
                    System.out.println("INFO (RoomCreateServlet) - " + cleanedRoomName + " does not exist and will be created");
                    RoomStore.getInstance().add(room);
                }
                // after success redirect to user page
                response.sendRedirect(request.getContextPath() + "/user");
            } catch (UnsupportedEncodingException exception) {
                System.out.println("ERROR (RoomCreateServlet) - " + exception.getMessage());
                this.doGet(request, response);
            }
        } else {
            // no valid room name -> send error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
