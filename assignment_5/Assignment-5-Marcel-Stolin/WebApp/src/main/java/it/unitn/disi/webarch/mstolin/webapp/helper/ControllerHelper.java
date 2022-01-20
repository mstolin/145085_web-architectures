package it.unitn.disi.webarch.mstolin.webapp.helper;

import java.text.ParseException;
import java.sql.Date;

public class ControllerHelper {

    public static Date parseStringToDate(String dateString) throws ParseException {
        return Date.valueOf(dateString);
    }

}
