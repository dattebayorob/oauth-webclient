
package com.sundsvall.midalva.resources;

import com.sundsvall.midalva.resources.model.HelloResponse;
import com.sundsvall.midalva.resources.model.HelloRequest;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class Api{

    public static int count = 0;
    
    @Autowired
    WebClient webclient;

    @PostMapping(path="/hello", consumes = "application/json", produces = "application/json")
    public @ResponseBody HelloResponse hello(@RequestBody HelloRequest request) {     
        return createResponse(request);
    }

    private HelloResponse createResponse(HelloRequest request) {
      

        return HelloResponse.create()
                .withMessage(request.getMessage())
                .withCount(++count)
                .withTimeStamp(LocalDateTime.now());

    }
}
