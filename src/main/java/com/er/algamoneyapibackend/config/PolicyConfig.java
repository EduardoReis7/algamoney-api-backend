package com.er.algamoneyapibackend.config;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolicyConfig {

    @Bean
    public PolicyFactory policyFactory() {
        return new HtmlPolicyBuilder().toFactory();
    }
}
