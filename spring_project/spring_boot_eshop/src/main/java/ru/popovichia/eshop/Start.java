/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.popovichia.eshop;

import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@SpringBootApplication
public class Start {
    
    private static final Logger LOG = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
    
}
