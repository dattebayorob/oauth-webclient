/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sundsvall.midalva.resources.model;

import java.time.LocalDateTime;

/**
 *
 * @author johan
 */
public class HelloResponse {
   
   private String message;
   private int count;
   private LocalDateTime timeStamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp= timeStamp;
    }
    
    public HelloResponse withMessage(String message) {
        this.message = message;
       return this;
    }

    public HelloResponse withCount(int i) {
        this.count = i;
        return this;
    }

    public HelloResponse withTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp= timeStamp;
        return this;
    }
    
    
    public static HelloResponse create(){
        return new HelloResponse();
    }
}
