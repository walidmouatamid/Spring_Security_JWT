package com.example.secservice.sec;

public class JWTUtil {
    public static final String SECRET="mySecret0000";
    public static final String AUTH_HEADER="Authorization";
    public static final long EXPIRE_ACCESS_TOKEN=2*60*1000;
    public static final String PREFIX="Bearer ";
    public static final long EXPIRE_REFRESH_TOKEN = 30*60*1000;
}
