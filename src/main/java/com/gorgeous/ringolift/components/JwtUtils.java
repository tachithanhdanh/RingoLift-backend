package com.gorgeous.ringolift.components;

import com.gorgeous.ringolift.exceptions.InvalidParamException;
import com.gorgeous.ringolift.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    // the expiration time of the token in milliseconds, retrieved from the application.properties file
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(User user) throws InvalidParamException {
        // The properties of the user that will be included in the token are called claims.
        Map<String, Object> claims = new HashMap<>();

        // Adding the username as a claim, which will be included in the token.
        claims.put("username", user.getUsername());

        try {
            // We need to generate the token for the user. This is the JWT (JSON Web Token) creation.
            // The token will include the claims, subject, expiration time, and a signature.

            // Build the JWT token
            return Jwts.builder()
                    .claims(claims)  // The claims that will be included in the token
                    .subject(user.getUsername())  // The subject (usually the username) of the token
                    .expiration(new Date(System.currentTimeMillis() + expiration))  // Set the expiration date of the token
                    .signWith(getSignInKey())  // Signing the token with the key (used for verification)
                    .compact();  // Finalizing and compacting the token into a string format
        } catch (Exception e) {
            // If an error occurs during the token generation process, an exception will be thrown
            // In this case, an InvalidParamException will be thrown to indicate an issue with the token generation.

            // Logging the error (this can be improved by adding a logger later)
            // System.err.println("Cannot generate token, error: " + e.getMessage());

            // Throwing a custom exception with a descriptive message about the error
            throw new InvalidParamException("Cannot generate jwt token, error: " + e.getMessage());
        }
    }


    private SecretKey getSignInKey() {
        // This method is used to generate a signing key for JWT (JSON Web Token) creation.
        // The key will be used to sign the token, ensuring its authenticity and integrity.

        // The 'secretKey' is presumably a base64-encoded string (like a shared secret or private key)
        // that is stored somewhere in the application configuration.
        // It should be kept secret and secure to prevent unauthorized access or tampering with the token.

        // Using the Decoders.BASE64.decode() to decode the base64-encoded secretKey into a byte array
        // and then using the Keys.hmacShaKeyFor() method to generate a HMAC-SHA key from this byte array.
        // HMAC-SHA is a cryptographic algorithm commonly used to sign JWT tokens.

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));  // Generate and return the secret signing key
    }

    private Claims extractAllClaims(String token) {
        // https://stackoverflow.com/a/77408683
        // The syntax of the Jwts.parser() method has changed in the latest version of the jjwt library

        // Purpose of the method:
        // This method extracts the claims (payload) from a given JWT token. The claims contain the user data and other
        // information (e.g., roles, username, expiration time) that was stored in the token during its creation.

        // Why is this needed:
        // In a typical JWT-based authentication flow, the client sends a JWT token with each request.
        // To access the information (claims) in the token, the server needs to extract and parse it.
        // This method ensures that the token is valid and provides the claims (data) needed for further processing
        // (e.g., checking user permissions, validating roles, etc.).

        // The method also verifies the integrity of the token by checking the signature using the secret key.
        // If the token has been tampered with (i.e., the signature is invalid), an exception will be thrown.
        return Jwts.parser()  // Create a JWT parser using the JJWT library
                .verifyWith(getSignInKey())  // Use the secret key to verify the signature of the JWT token
                .build()  // Build the parser instance
                .parseSignedClaims(token)  // Parse the token and validate its signature
                .getPayload();  // Extract and return the payload (claims) of the token
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Purpose of the method:
        // This method extracts a specific claim from a JWT token by utilizing a claims resolver function.
        // The claim can be any piece of data stored in the JWT's payload, such as the user's role or username.
        // The method provides flexibility to extract any claim by passing the appropriate resolver function.

        // 1. Extract all claims from the token:
        final Claims claims = extractAllClaims(token);  // Calls the extractAllClaims method to retrieve all claims from the token.

        // 2. Apply the claims resolver to get the specific claim:
        // The claimsResolver function is a parameterized function that tells how to extract the desired claim from the Claims object.
        // This allows for flexibility in extracting different types of claims.
        return claimsResolver.apply(claims);  // Apply the claimsResolver function to the claims and return the result.
    }


    // check expiration
    public boolean isTokenExpired(String token) {
        // extract the expiration date from the token
        // check if the expiration date is before the current date
        // return the result of the check
        Date expirationDate = (Date) this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public String extractUsername(String token) {
        // Extract the username (subject) from the token
        // Return the extracted username
        return extractClaim(token, Claims::getSubject);  // The 'subject' in the JWT is typically the username
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        // Extract the username from the token
        // Extract the expiration date from the token (to check if the token is expired)
        // Extract the username from the user details (to compare with the username in the token)
        // Check if the username from the token matches the username from the user details
        // Check if the token has expired
        // Return the result of these checks (true if the token is valid, false otherwise)
        String username = extractUsername(token);  // Extract the username from the token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Validate if username matches and token is not expired
    }
}