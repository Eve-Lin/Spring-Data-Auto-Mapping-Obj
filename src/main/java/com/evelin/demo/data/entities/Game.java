package com.evelin.demo.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String image;
    private double price;
    private Date releaseDate;
    private double size;
    private String title;
    private String trailer;
    @ManyToMany
    private Set<User> users;


}
