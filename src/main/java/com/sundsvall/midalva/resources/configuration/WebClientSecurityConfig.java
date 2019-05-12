package com.sundsvall.midalva.resources.configuration;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author johan
 */
@Configuration
public class WebClientSecurityConfig {
    
    private static final Logger Logger = LoggerFactory.getLogger(WebClientSecurityConfig.class);

    @Bean
    ReactiveClientRegistrationRepository reactiveClientRegistrationRepository(){
        ClientRegistration clientRegistration
                = ClientRegistration.withRegistrationId("midalva")
                        .clientId("client-id")
                        .clientSecret("secret")
                        .tokenUri("token-url")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .scope("scopes")
                        .build();
        return new InMemoryReactiveClientRegistrationRepository(clientRegistration);
    }
   
    

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth
                = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                        clientRegistrations,
                        new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
        oauth.setDefaultClientRegistrationId("midalva");
        return WebClient.builder()
                .filter(oauth)
                .build();
    }

}
