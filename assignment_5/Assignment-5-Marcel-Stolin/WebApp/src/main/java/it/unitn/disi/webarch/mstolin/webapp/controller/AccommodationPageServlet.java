package it.unitn.disi.webarch.mstolin.webapp.controller;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceAddressBuilder;
import it.unitn.disi.webarch.mstolin.webapp.servicelocator.ServiceLocator;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccommodationPageServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AccommodationListServlet.class.getName());

    // IN EINE FACTORY !!!
    private AccommodationService getAccommodationServiceBean() throws NamingException {
        String beanAddress = ServiceAddressBuilder.generateAccommodationBeanAddress(AccommodationService.class.getName());
        AccommodationService accommodationService = ServiceLocator.getInstance().getService(beanAddress);
        return accommodationService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        StringTokenizer tokenizer = new StringTokenizer(pathInfo, "/");
        String firstToken = tokenizer.nextToken();
        int id = Integer.parseInt(firstToken);

        this.logger.log(Level.SEVERE, "Requested Accommodation ID: " + id);

        try {
            AccommodationService accommodationService = this.getAccommodationServiceBean();
            AccommodationEntity accommodation = accommodationService.getAccommodation(id);

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            writer.println("<h1>" + accommodation.getName() + "</h1>");
            writer.println("<p>Price " + accommodation.getPrice() + "</p>");
            writer.println("</body></html>");
        } catch (NamingException e) {
            this.logger.log(Level.SEVERE, null, e);
        }
    }
}
