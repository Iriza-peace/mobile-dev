package rw.ac.rca.banking.services;

import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rw.ac.rca.banking.security.UserPrincipal;

import java.util.Date;
import java.util.List;

@Service
@Component
public class JwtService {

    private String jwtSecret="j3H5Ld5nYmGWyULy6xwpOgfSH++NgKXnJMq20vpfd+8=t";

    private int jwtExpirationInMs = 86400000;


    public String generateToken(UserPrincipal userPrincipal) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        // Extract roles from userPrincipal and convert them to a list of role names
        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        System.out.println(userPrincipal.getId());

        try {
            String jwt =  Jwts.builder()
                    .setId(userPrincipal.getId() + "")
                    .setSubject(userPrincipal.getUsername() + "")
                    .claim("user", userPrincipal)
                    .claim("roles", roles) // Include roles in the claim
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
            return jwt;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean isTokenValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature" + ex);
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token" + ex);
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token" + ex);
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token" + ex);
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty" + ex);
        }
        return false;
    }
}