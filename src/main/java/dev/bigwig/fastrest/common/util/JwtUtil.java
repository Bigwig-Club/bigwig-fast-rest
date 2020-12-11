package dev.bigwig.fastrest.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtil {

  private static final String SECRET = "secret";

  public static String generate(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return create(claims, userDetails.getUsername());
  }

  public static boolean validate(String token, UserDetails userDetails) {
    String username = getUsername(token);
    return username.equals(userDetails.getUsername()) && !getExpiration(token).before(new Date());
  }

  public static String getUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public static Date getExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public static <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
    Claims claims = Jwts.parser()
      .setSigningKey(SECRET)
      .parseClaimsJws(token)
      .getBody();
    return claimsTFunction.apply(claims);
  }

  public static String create(Map<String, Object> claims, String subject) {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 6 * 1000))
      .signWith(SignatureAlgorithm.HS512, SECRET)
      .compact();
  }
}
