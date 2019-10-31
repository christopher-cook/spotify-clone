package com.example.spotifyclone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH,
//                    CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name = "user_song",
//            joinColumns = {@JoinColumn(name = "song_id")},
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users;

    @JsonIgnore
    @ManyToMany(mappedBy="songs")
    private List<User> users;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private Double length;

    public Song() { }

    public List<User> getUsers(){ return users; }

    public void setUsers(List<User> users) { this.users = users; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
