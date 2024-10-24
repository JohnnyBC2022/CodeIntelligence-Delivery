package com.codeintelligence.delivery.model.user;

import com.codeintelligence.delivery.model.Token.TokenEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Entity that represents a user in the system.
 * This class is mapped to the "user" table in the database.
 */
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToMany(targetEntity = TokenEntity.class,fetch = FetchType.LAZY,mappedBy = "user")
    private List<TokenEntity> tokens;

    // Getters and Setters

    /**
     * Gets the unique identifier of the user.
     *
     * @return the id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the unique id to set
     */
    public void setId(int id) {
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

    @Override
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

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
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
     * @return the list of TokenEntity associated with the user
     */
    public List<TokenEntity> getTokens() {
        return tokens;
    }

    /**
     * Sets the list of tokens associated with the user.
     *
     * @param tokens the list of TokenEntity to set
     */
    public void setTokens(List<TokenEntity> tokens) {
        this.tokens = tokens;
    }

    // Constructors

    /**
     * Default constructor for the UserEntity class.
     */
    public UserEntity() {
    }

    /**
     * Constructs a new UserEntity with the specified parameters.
     *
     * @param id        the unique identifier of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param mail      the email of the user
     * @param username  the username of the user
     * @param password  the password of the user
     * @param role      the role of the user
     * @param tokens    the list of tokens associated with the user
     */
    public UserEntity(int id, String firstName, String lastName, String mail, String username, String password, Role role, List<TokenEntity> tokens) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.tokens = tokens;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the account is non-expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     *
     * @return true if the account is non-locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return true if the credentials are non-expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is enabled.
     *
     * @return true if the account is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of granted authorities, including the user's role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
