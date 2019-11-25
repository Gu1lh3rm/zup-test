package br.com.api.backend.primary.core.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expiration}") private Long expiration;


    public String generateToken(UserSS username) {
        return Jwts.builder()
                .setSubject(payload(username))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean tokenValido(String token){
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        String username = null;
        if (claims != null) {
            try {

                JSONObject reader = new JSONObject(claims.getSubject());

                JSONArray keys = reader.names ();

                for (int i = 0; i < keys.length (); ++i) {

                    String key = keys.getString (i); // Here's your key
                    String value = reader.getString (key); // Here's your value

                    if(key.equals("username"))
                        username = value;

                }

                return username;
            } catch (JSONException e) {
                return username;
            }
        }
        return username;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token.replaceAll("Bearer","").replaceAll(" ", "")).getBody();
        }catch (Exception e){
            return null;
        }

    }

    public static String payload(@NonNull UserSS user) {
        /*UserData tokenData = new UserData(user);*/
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "error";

        try {
            jsonInString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonInString;
    }
}
