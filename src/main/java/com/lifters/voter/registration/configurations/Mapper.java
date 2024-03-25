package com.lifters.voter.registration.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    ModelMapper modelMapper()  {return new ModelMapper(); }
}
