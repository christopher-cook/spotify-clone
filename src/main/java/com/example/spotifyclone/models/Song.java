package com.example.spotifyclone.models;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private Long length;


}
//need to map songs to user in User Model!

//@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
//public class Course {
//...
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH,
//                    CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name = "user_course",
//            joinColumns = {@JoinColumn(name = "course_id")},
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users;
//
//    public List<User> getUsers(){ return users; }
//
//    public void setUsers(List<User> users) { this.users = users; }
//...
//}