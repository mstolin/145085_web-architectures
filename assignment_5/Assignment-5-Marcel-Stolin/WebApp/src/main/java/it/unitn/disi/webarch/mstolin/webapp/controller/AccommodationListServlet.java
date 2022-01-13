package it.unitn.disi.webarch.mstolin.webapp.controller;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.dao.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceFactory;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccommodationListServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AccommodationListServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AccommodationService accommodationService = ServiceFactory.initializeService(
                    ServiceFactory.ACCOMMODATION_BEAN,
                    AccommodationService.class.getName()
            );
            List<AccommodationEntity> allAccommodations = accommodationService.getAll();

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            for(AccommodationEntity accommodation: allAccommodations) {
                if (accommodation instanceof ApartmentEntity) {
                    writer.println("<a href=\"" + request.getContextPath() + "/accommodation/" + accommodation.getId() + "\">Apartment: " + accommodation.getName() + "</a><br/>");
                } else if (accommodation instanceof HotelEntity) {
                    writer.println("<a href=\"" + request.getContextPath() + "/accommodation/" + accommodation.getId() + "\">Hotel: " + accommodation.getName() + "</a><br/>");
                }
            }
            writer.println("</body></html>");
        } catch (NamingException e) {
            this.logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
