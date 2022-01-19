package it.unitn.disi.webarch.mstolin.webapp.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerHelper {

    public static Date parseStringToDate(String dateString, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateString);
    }

    public static Date parseStringToDate(String dateString) throws ParseException {
        return parseStringToDate(dateString, "yyyy-MM-dd");
    }
}
