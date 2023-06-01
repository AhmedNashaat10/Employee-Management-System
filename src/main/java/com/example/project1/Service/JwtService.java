package com.example.project1.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public static final String SECRET="645267556B58703273357538782F413F4428472B4B6250655368566D59713374";
	
	
	public String GenerateToken(String userName)
	{
		Map<String, Object> claims= new HashMap<>();
		return CreateToken(claims,userName);
	}
	public String CreateToken(Map<String, Object> claims,String userName)
	{
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt( new Date(System.currentTimeMillis()))
				.setExpiration( new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
	private Key getSignKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String ExtractUserName(String token)
	{
		return ExtractClaim(token,Claims::getSubject);
	}
	
	public Date ExtractExpiration(String token)
	{
		return ExtractClaim(token,Claims::getExpiration);
	}
	
	public <T> T ExtractClaim(String token,Function<Claims, T> claimsResolver)
	{
		final Claims claims=ExtractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims ExtractAllClaims(String token)
	{
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	private Boolean isTokenExpired(String token)
	{
		return ExtractExpiration(token).before(new Date());
	}
	
	public Boolean ValidateToken(String token,UserDetails userDetails)
	{
		final String userName=ExtractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
