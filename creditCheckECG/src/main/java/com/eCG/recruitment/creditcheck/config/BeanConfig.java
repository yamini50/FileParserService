package com.eCG.recruitment.creditcheck.config;

import com.eCG.recruitment.creditcheck.services.CsvToClientDataParser;
import com.eCG.recruitment.creditcheck.services.PrnToClientDataParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
    @Bean
    @Scope("prototype")
    PrnToClientDataParser prnToClientDataParser() {
        return new PrnToClientDataParser();
    }

    @Bean
    @Scope("prototype")
    CsvToClientDataParser csvToClientDataParser() {
        return new CsvToClientDataParser();
    }
}
