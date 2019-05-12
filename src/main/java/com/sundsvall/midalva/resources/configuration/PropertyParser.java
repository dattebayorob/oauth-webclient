
package com.sundsvall.midalva.resources.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author johan
 */
public class PropertyParser {

    private Map<String, String> map;
    private final Map<String, OauthClientProperty> values = new HashMap<>();

    public PropertyParser(Map<String, String> map) {
        this.map = map;
    }

    public void parse() {
        
        map.forEach((key, value) -> { 
             parseRegistration(key, value);
        });
    }
    

    private void parseRegistration(String key, String value) {

        String[] split = key.split(".");
        String registrationId = split[4];
        String valueIdentifier = split[6];

        createOauthClientPropertyIfNotExists(key);

        switch (valueIdentifier) {
            case "authorization-grant-type":
                values.get(registrationId).setAuthorizationGrantType(value);
                break;
            case "client-id":
                values.get(registrationId).setClientId(value);
                break;
            case "client-secret":
                values.get(registrationId).setClientSecret(value);
                break;
            case "token-uri":
                values.get(registrationId).setTokenURI(value);
                break;
        }

        System.out.println("registrationId :" + registrationId + " valueIdentifier: " + valueIdentifier);

    }

    private void createOauthClientPropertyIfNotExists(String key) {
        if (!values.containsKey(key)) {
            OauthClientProperty prop = new OauthClientProperty();
            prop.setRegistrationId(key);
            values.put(key, prop);
        }
    }

}
