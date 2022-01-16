package it.unitn.disi.webarch.mstolin.webapp.controller.accommodation;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AccommodationSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/accommodation/AccommodationSearchView.jsp")
                .forward(request, response);
    }
}
