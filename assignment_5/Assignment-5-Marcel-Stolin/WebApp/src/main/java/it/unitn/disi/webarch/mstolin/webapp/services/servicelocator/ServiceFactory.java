package it.unitn.disi.webarch.mstolin.webapp.services.servicelocator;

import javax.naming.NamingException;

public class ServiceFactory {

    private final static String APP_NAME = "marcel-stolin-web-services";

    public final static String ACCOMMODATION_BEAN = "AccommodationBean";
    public final static String RESERVATION_BEAN = "ReservationBean";

    private static String generateServiceAddress(String beanName, String className) {
        return "ejb:/" + APP_NAME + "/" + beanName + "!" + className;
    }

    public static <T extends Object> T initializeService(String beanName, String className) throws NamingException {
        final String serviceAddress = generateServiceAddress(beanName, className);
        T service = ServiceLocator.getInstance().getService(serviceAddress);
        return service;
    }

}
