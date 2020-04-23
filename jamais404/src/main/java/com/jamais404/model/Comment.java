package com.jamais404.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(
        name = "userId",
        nullable = false)
    private User user;

    @Column(name = "datetime",
    nullable = false)
    private Timestamp datetime;

    @Column(name = "name",
    nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "pageId",
    nullable = false)
    private Page page;

    // METHODS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Page getPage(){
        return page;
    }

    public void setPage(Page page){
        this.page = page;
    }
}