package com.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

//esta clase y anotaciones sirven para pre autorizar usuarios @preAuthorized
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled=true
)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
