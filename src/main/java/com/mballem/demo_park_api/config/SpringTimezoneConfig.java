package com.mballem.demo_park_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

// Transforma a classe em uma classe de configuração
@Configuration
public class SpringTimezoneConfig {

    // Essa anotação faz com que após essa classe ser iniciada pelo Spring
    // o método construtor dela é executado, e após essa execução, o primeiro
    // método a ser executado será o abaixo (a configuração do timezone)
    @PostConstruct
    public void timezoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
