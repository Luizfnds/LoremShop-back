package com.lefnds.loremshop.security.auth;

import com.lefnds.loremshop.security.auth.exceptions.ExpiredTokenException;
import com.lefnds.loremshop.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final long EXPIRATION_TIME = 30 * 60000;
    private static final String SECRET_KEY = System.getenv("SECRET_KEY");

    public String generateToken( User user ) {
        return Jwts
                .builder()
                .setSubject( user.getUsername() )
                .setIssuedAt( new Date( System.currentTimeMillis() ) )
                .setExpiration( new Date( System.currentTimeMillis() + EXPIRATION_TIME ) )
                .signWith( SignatureAlgorithm.HS256 , SECRET_KEY )
                .compact();
    }

    public Claims decodeToken( String token ) {
        String treatedToken = token.replace( "Bearer " , "" );
        return Jwts
                .parser()
                .setSigningKey( SECRET_KEY )
                .parseClaimsJws( treatedToken )
                .getBody();

    }

    public Boolean isTokenExpired( String token ) {
        String treatedToken = token.replace( "Bearer " , "" );
        Claims claims = decodeToken( treatedToken );
        if( claims.getExpiration().before( new Date( System.currentTimeMillis() ) ) ) {
            throw new ExpiredTokenException();
        }
        return false;
    }

    public Boolean isTokenValid( String token , UserDetails userDetails ) {
        String username = decodeToken( token ).getSubject();
        return ( username.equals( userDetails.getUsername() ) ) && !isTokenExpired( token );
    }

    public String getSubject( String token ) {
        return decodeToken( token ).getSubject();
    }

}
