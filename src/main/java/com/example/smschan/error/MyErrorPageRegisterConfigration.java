package com.example.smschan.error;

import org.springframework.context.annotation.Bean;

public class MyErrorPageRegisterConfigration {
    @Bean
    public MyErrorPageRegister errorPageRegister(){
        return new MyErrorPageRegister();
    }
}
