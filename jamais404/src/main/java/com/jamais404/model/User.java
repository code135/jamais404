package com.jamais404.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username",
    length = 32,
    nullable = false
    )
    private String username;

    @Column(name = "email",
    nullable = false)
    private String email;

    @Column(name = "password",
    length = 255,
    nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Comment> ownedComments;

    @OneToMany(mappedBy = "owner")
    private Set<Page> ownedPages;

    // METHODS

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Page> getPages() {
        return ownedPages;
    }
}