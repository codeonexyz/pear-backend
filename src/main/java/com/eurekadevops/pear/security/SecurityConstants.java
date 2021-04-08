package com.eurekadevops.pear.security;

/*
 * Application Constants
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30 * 60000;
}
