package com.example.jsonheffner.common.Entity;

public class User {
    private static User currentUser;
    private long token;

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public static User getCurrentUser() {
        if(currentUser==null){
            currentUser=new User();
        }
        return currentUser;
    }
    public static final String EMAIL= "email";
    public static final String PASSWORD= "password";
    public static final String FIRST_NAME= "firstName";
    public static final String LAST_NAME= "lastName";

}
