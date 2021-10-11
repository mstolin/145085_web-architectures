package it.unitn.disi.webarch.chat.controller.room;

import it.unitn.disi.webarch.chat.helper.RoomStore;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

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
            System.out.println("RoomCreate: User wants to create a room " + roomName);
            // create room
            RoomStore.addRoom(roomName);
            // after success redirect to user page
            response.sendRedirect(request.getContextPath() + "/user");
        } else {
            // no valid room name -> send error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
