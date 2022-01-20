package it.unitn.disi.webarch.mstolin.webapp.services.delegates;

import it.unitn.disi.webarch.mstolin.webapp.services.servicelocator.ServiceFactory;

import javax.naming.NamingException;

public abstract class ServiceDelegate <T extends Object> {

    final protected String beanName;
    final protected String serviceClassName;

    protected ServiceDelegate(String beanName, String serviceClassName) {
        this.beanName = beanName;
        this.serviceClassName = serviceClassName;
    }


    protected T getService() throws NamingException {
        return ServiceFactory.initializeService(
            beanName,
            this.serviceClassName
        );
    }

}
