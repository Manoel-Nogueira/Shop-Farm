package br.com.farmshop.api.auth;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import br.com.farmshop.api.entities.User;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
	private final SecretKey secret = Jwts.SIG.HS256.key().build();
	
	public String generateToken(User user) {
		
		return Jwts
				.builder()
				.subject(user.getEmail())
				.claim("name", user.getName())
				.claim("role", user.getRole())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(secret)
				.compact();
		
	}
	
	public String extractEmail(String token) {
		
		return Jwts
				.parser()
				.verifyWith(secret)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
		
	}
	
	public boolean isValid(String token, UserDetails user) {
		
		return extractEmail(token).equals(user.getUsername());
		
	}
	
}



















