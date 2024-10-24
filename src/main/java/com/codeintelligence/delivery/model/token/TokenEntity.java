package com.codeintelligence.delivery.model.token;

import com.codeintelligence.delivery.model.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity that represents a token for user authentication.
 * This class is mapped to the "token" table in the database.
 */
@Entity
@Table(name = "token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne(targetEntity = UserEntity.class)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Getters and Setters

    /**
     * Gets the unique identifier of the token.
     *
     * @return the id of the token
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the token.
     *
     * @param id the unique id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the token string.
     *
     * @return the token string
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token string.
     *
     * @param token the token string to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Checks if the token is logged out.
     *
     * @return true if the token is logged out, false otherwise
     */
    public boolean isLoggedOut() {
        return loggedOut;
    }

    /**
     * Sets the logged-out status of the token.
     *
     * @param loggedOut true to mark the token as logged out, false otherwise
     */
    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    /**
     * Gets the user associated with the token.
     *
     * @return the UserEntity associated with the token
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Sets the user associated with the token.
     *
     * @param user the UserEntity to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Default constructor for the TokenEntity class.
     */
    public TokenEntity() {
    }

    /**
     * Constructs a new TokenEntity with the specified parameters.
     *
     * @param id        the unique identifier of the token
     * @param token     the token string
     * @param loggedOut the logged-out status of the token
     * @param user      the user associated with the token
     */
    public TokenEntity(Long id, String token, boolean loggedOut, UserEntity user) {
        this.id = id;
        this.token = token;
        this.loggedOut = loggedOut;
        this.user = user;
    }
}
