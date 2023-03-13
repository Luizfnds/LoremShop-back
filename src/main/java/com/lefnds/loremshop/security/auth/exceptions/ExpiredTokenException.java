package com.lefnds.loremshop.security.auth.exceptions;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
        super( "Expired token" );
    }

}
