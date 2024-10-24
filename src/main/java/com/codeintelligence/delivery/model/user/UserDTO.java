package com.codeintelligence.delivery.model.user;

import com.codeintelligence.delivery.model.token.TokenEntity;

import java.util.List;

/**
 * Data Transfer Object (DTO) that represents a user in the system.
 * This class is used for transferring user data without exposing the entity structure.
 */
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String username;
    private String password;
    private Role role;
    private List<TokenEntity> tokens;

    // Getters and Setters

    /**
     * Gets the unique identifier of the user.
     *
     * @return the id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the unique id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the email of the user.
     *
     * @param mail the email to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * Note: Be cautious when exposing passwords through DTOs.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * Note: Be cautious when exposing passwords through DTOs.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the list of tokens associated with the user.
     *
     * @return the list of TokenDTO associated with the user
     */
    public List<TokenEntity> getTokens() {
        return tokens;
    }

    /**
     * Sets the list of tokens associated with the user.
     *
     * @param tokens the list of TokenDTO to set
     */
    public void setTokens(List<TokenEntity> tokens) {
        this.tokens = tokens;
    }

    // Constructors

    /**
     * Default constructor for the UserDTO class.
     */
    public UserDTO() {
    }

    /**
     * Constructs a new UserDTO with the specified parameters.
     *
     * @param id        the unique identifier of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param mail      the email of the user
     * @param username  the username of the user
     * @Param password  the password of the user
     * @param role      the role of the user
     * @param tokens    the list of tokens associated with the user
     */
    public UserDTO(Long id, String firstName, String lastName, String mail, String username, String password, Role role, List<TokenEntity> tokens) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tokens = tokens;
    }
}
