package com.supnum.middle_service.config;

import com.supnum.supnum_td.soap.CreateServerRequest;
import com.supnum.supnum_td.soap.CreateServerResponse;
import com.supnum.supnum_td.soap.DeleteServerRequest;
import com.supnum.supnum_td.soap.DeleteServerResponse;
import com.supnum.supnum_td.soap.GetServerStatusRequest;
import com.supnum.supnum_td.soap.GetServerStatusResponse;
import com.supnum.supnum_td.soap.ListServersRequest;
import com.supnum.supnum_td.soap.ListServersResponse;
import com.supnum.supnum_td.soap.RenameServerRequest;
import com.supnum.supnum_td.soap.RenameServerResponse;
import com.supnum.supnum_td.soap.Server;
import com.supnum.supnum_td.soap.StartServerRequest;
import com.supnum.supnum_td.soap.StartServerResponse;
import com.supnum.supnum_td.soap.StopServerRequest;
import com.supnum.supnum_td.soap.StopServerResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setClassesToBeBound(
                CreateServerRequest.class,
                CreateServerResponse.class,
                ListServersRequest.class,
                ListServersResponse.class,
                GetServerStatusRequest.class,
                GetServerStatusResponse.class,
                StartServerRequest.class,
                StartServerResponse.class,
                StopServerRequest.class,
                StopServerResponse.class,
                RenameServerRequest.class,
                RenameServerResponse.class,
                DeleteServerRequest.class,
                DeleteServerResponse.class,
                Server.class
        );
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);
        
        template.setDefaultUri("http://soap-service:8082/ws");
        return template;
    }
}