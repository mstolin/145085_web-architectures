package it.unitn.disi.webarch.mstolin.webapp.servicelocator;

public class ServiceAddressBuilder {

    private final static String APP_NAME = "WebServices.1.0-SNAPSHOT";

    private final static String ACCOMMODATION_BEAN_NAME = "AccommodationBean";

    public static String generateAccommodationBeanAddress(String viewClassName) {
        return "ejb:/" + APP_NAME + "/" + ACCOMMODATION_BEAN_NAME + "!" + viewClassName;
    }

}
