package com.sifts.Sifts.Auth.Module.config;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CConfig {
        @Bean
        public ObjectMapper objectMapper(){
            return new ObjectMapper();
        }
//        @Bean
//    public Gson gson(){
//            return new Gson();
//        }


}
