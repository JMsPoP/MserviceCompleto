package com.jmtsu.ms.payment.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties("app.pix")
public record PixConfig(String clientId, String clientSecret, boolean sandbox, boolean debug, String certificatePath) {

}