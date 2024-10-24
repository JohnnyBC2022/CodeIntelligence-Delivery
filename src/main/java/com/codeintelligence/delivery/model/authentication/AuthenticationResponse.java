package com.codeintelligence.delivery.model.authentication;

/**
 * This class represents the response that is returned after a successful authentication.
 * It contains a token and a message that provide information about the result of the authentication process.
 */
public class AuthenticationResponse {

    private String token;
    private String message;

    /**
     * Retrieves the JWT token.
     *
     * @return the JWT token as a String.
     */
    private String getToken() {
        return token;
    }

    /**
     * Sets the JWT token.
     *
     * @param token the JWT token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Retrieves the message associated with the authentication process.
     *
     * @return the message as a String.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the authentication process.
     *
     * @param message the message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Default constructor for AuthenticationResponse.
     * Creates an empty instance of AuthenticationResponse.
     */
    public AuthenticationResponse() {

    }

    /**
     * Parameterized constructor for AuthenticationResponse.
     *
     * @param token the JWT token returned after authentication.
     * @param message the message to provide context or information about the authentication process.
     */
    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
