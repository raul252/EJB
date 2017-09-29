package com.example.ejb.business;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Greeting
 */
@Stateless
@LocalBean
public class Greeting {

    /**
     * Default constructor. 
     */
    public Greeting() {
        System.out.println("Coonstructor Greeting");
    }

    public String getGreeting() {
    	Date d = new Date(System.currentTimeMillis());
    	StringBuilder sb = new StringBuilder();
    	sb.append("Esto es un EJB @STATELESS, ");
    	sb.append("Llamada realizada a las " + d.toString());
    	return sb.toString();
    }
    
    
}
