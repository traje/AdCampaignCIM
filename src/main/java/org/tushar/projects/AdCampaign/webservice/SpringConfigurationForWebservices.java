package org.tushar.projects.AdCampaign.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.eval.adcampaign.service.AdCampaignService;

public class SpringConfigurationForWebservices {

	private final Logger logger = Logger.getLogger(SpringConfigurationForWebservices.class); 
	
	/*
    @Bean
    public Server getJaxRsServer() {
    	logger.debug("entered Server getJaxRsServer()");

        final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress("/");
        factory.setServiceBeans(getWebServicesList());
        factory.setProviders(getProvidersForWebServices());
        final Server server = factory.create();
        return server;
    }

    private List<Object> getWebServicesList() {
    	logger.debug("entered Server getWebServicesList()");
        final List<Object> webServices = new ArrayList()<Object>;
        webServices.add(new AdCampaignWebService(new AdCampaignService()));
        return webServices;
    }
    private final List<Object> getProvidersForWebServices() {
    	logger.debug("entered Server getProvidersForWebServices()");
        final List<Object> providers = new ArrayList<Object>();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        final JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
        jaxbProvider.setMapper(mapper);
        providers.add(jaxbProvider);
        return providers;
    }*/
	
}
