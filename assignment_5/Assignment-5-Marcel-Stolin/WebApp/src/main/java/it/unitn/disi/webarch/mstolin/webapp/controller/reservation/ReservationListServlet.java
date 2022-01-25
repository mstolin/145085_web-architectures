package it.unitn.disi.webarch.mstolin.webapp.controller.reservation;

import it.unitn.disi.webarch.mstolin.dao.reservation.ReservationEntity;
import it.unitn.disi.webarch.mstolin.webapp.models.reservation.ReservationListModel;
import it.unitn.disi.webarch.mstolin.webapp.services.delegates.ReservationDelegate;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ReservationListServlet extends HttpServlet {

    private ReservationDelegate reservationDelegate = new ReservationDelegate();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (firstName != null && lastName != null && firstName.length() > 0 && lastName.length() > 0) {
            String guestName = firstName.trim() + " " + lastName.trim();
            try {
                List<ReservationEntity> reservations = this.reservationDelegate.getReservationForGuest(guestName);
                ReservationListModel reservationListModel = new ReservationListModel(reservations, guestName);
                request.setAttribute("model", reservationListModel);
                this.getServletContext()
                        .getRequestDispatcher("/views/reservation/ReservationListView.jsp")
                        .forward(request, response);
            } catch (NamingException exception) {
                exception.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/reservation/ReservationGetMyView.jsp")
                .forward(request, response);
    }

}
