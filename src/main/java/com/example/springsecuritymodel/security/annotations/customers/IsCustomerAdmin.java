package com.example.springsecuritymodel.security.annotations.customers;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.springsecuritymodel.security.SecurityRoles.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Secured(ROLE_PREFIX + CUSTOMERS_ADMIN)
public @interface IsCustomerAdmin {
}
