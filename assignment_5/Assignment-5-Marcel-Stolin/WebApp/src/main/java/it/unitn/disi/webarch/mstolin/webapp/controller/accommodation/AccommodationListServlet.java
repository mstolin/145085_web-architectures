package it.unitn.disi.webarch.mstolin.webapp.controller;

import it.unitn.disi.webarch.mstolin.dao.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.webapp.models.AccommodationListModel;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class AccommodationListServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AccommodationListServlet.class.getName());

    private Date parseStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext()
                .getRequestDispatcher("/views/AccommodationListView.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDateParameter = request.getParameter("startDate");
        String endDateParameter = request.getParameter("endDate");
        String numberOfPersonsParameter = request.getParameter("numberPersons");

        if (startDateParameter != null && endDateParameter != null && numberOfPersonsParameter != null) {
            try {
                Date startDate = this.parseStringToDate(startDateParameter);
                Date endDate = this.parseStringToDate(endDateParameter);
                int numberOfPersons = Integer.parseInt(numberOfPersonsParameter);

                if (numberOfPersons >= 1) {
                    AccommodationListModel m = new AccommodationListModel();
                    List<AccommodationEntity> t = m.getAvailableApartments(startDate, endDate, numberOfPersons);
                    System.out.println(t);
                } else {

                }
            } catch (ParseException exception) {
                exception.printStackTrace();
            }
        } else {
            // show error
        }
    }
}
