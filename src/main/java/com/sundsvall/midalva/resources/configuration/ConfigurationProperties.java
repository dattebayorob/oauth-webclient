/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sundsvall.midalva.resources.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;

/**
 *
 * @author johan
 */
@Configuration
public class ConfigurationProperties {
    
    @Autowired
    Environment env;
    
    @Autowired
    OauthProps oauthProps;
    
    @Bean
    public OauthProps readProperties() {
        
        Map<String, OauthClientProperty> values = new HashMap<>();
        Map<String, String> oauthClientProps = new HashMap<>();
        
        Map<String, Object> map = new HashMap();
        for (PropertySource propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            if (propertySource instanceof MapPropertySource) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
               
        map.forEach((key, value) -> {
            if (key.contains("spring.security.oauth2.client.provider") || key.contains("spring.security.oauth2.client.provider")) {
                oauthClientProps.put(key, String.valueOf(value));
            }
        });
       
        PropertyParser propertyParser = new PropertyParser(oauthClientProps);
        propertyParser.parse();
        
        return new OauthProps();
        
    }
}
