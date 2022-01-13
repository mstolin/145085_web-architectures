package it.unitn.disi.webarch.mstolin.webapp.controller;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccommodationListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(
                Context.INITIAL_CONTEXT_FACTORY,
                "org.wildfly.naming.client.WildFlyInitialContextFactory"
        );
        jndiProperties.put(
                Context.PROVIDER_URL,
                "http-remoting://localhost:8080"
        );

        Context ctx = null;
        AccommodationService accommodationService = null;

        try {
            ctx = new InitialContext(jndiProperties);
            System.out.println("before");
            accommodationService = (AccommodationService) ctx.lookup(
                    "ejb:/WebServices.1.0-SNAPSHOT/AccommodationBean!it.unitn.disi.webarch.mstolin.webservices.accommodations.AccommodationService");
            List<AccommodationEntity> allAccommodations = accommodationService.getAll();

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            for(AccommodationEntity accommodation: allAccommodations) {
                writer.println("<h1>" + accommodation.getName() + "</h1>");
            }
            writer.println("</body></html>");
        } catch (NamingException ex) {
            Logger.getLogger(AccommodationListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
