package it.unitn.disi.webarch.mstolin.webapp.services.servicelocator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class ServiceLocator {

    private static ServiceLocator instance = null;

    private final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private final String PROVIDER_URL = "http-remoting://localhost:8080";

    private Context context;

    public static synchronized ServiceLocator getInstance() throws NamingException {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    private ServiceLocator() throws NamingException {
        this.context = this.getContext();
    }

    private Hashtable<String, String> getJNDIProperties() {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(
                Context.INITIAL_CONTEXT_FACTORY,
                this.INITIAL_CONTEXT_FACTORY
        );
        jndiProperties.put(
                Context.PROVIDER_URL,
                this.PROVIDER_URL
        );
        return jndiProperties;
    }

    private Context getContext() throws NamingException {
        final Hashtable jndiProperties = this.getJNDIProperties();
        Context context = new InitialContext(jndiProperties);
        return context;
    }

    public <T extends Object> T getService(String address) throws NamingException {
        T service = (T) this.context.lookup(address);
        return service;
    }
}
