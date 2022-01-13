package it.unitn.disi.webarch.mstolin.webapp.servicelocator;

import javax.naming.NamingException;

public class ServiceFactory {

    private final static String APP_NAME = "WebServices.1.0-SNAPSHOT";

    public final static String ACCOMMODATION_BEAN = "AccommodationBean";

    private static String generateBeanAddress(String beanName, String className) {
        return "ejb:/" + APP_NAME + "/" + beanName + "!" + className;
    }

    public static <T extends Object> T initializeService(String beanName, String className) throws NamingException {
        final String beanAddress = generateBeanAddress(beanName, className);
        T service = ServiceLocator.getInstance().getService(beanAddress);
        return service;
    }

}
