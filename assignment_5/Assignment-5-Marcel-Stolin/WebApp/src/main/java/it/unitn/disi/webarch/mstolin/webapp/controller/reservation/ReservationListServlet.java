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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ReservationEntity> reservations = this.reservationDelegate.getReservationForGuest("Mock Guest");
            ReservationListModel reservationListModel = new ReservationListModel(reservations, "Mock Guest");
            request.setAttribute("model", reservationListModel);
            this.getServletContext()
                    .getRequestDispatcher("/views/reservation/ReservationListView.jsp")
                    .forward(request, response);
        } catch (NamingException exception) {
            exception.printStackTrace();
        }
    }

}
