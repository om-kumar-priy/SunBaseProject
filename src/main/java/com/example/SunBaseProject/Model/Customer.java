package com.example.SunBaseProject.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter  //use gor getter
@Setter  //use for setter
@NoArgsConstructor //for creating no arg... constructor
@AllArgsConstructor  //for creating with arg... constructor
@Entity  //denote table
@Builder  //use for building function
@Table(name="user") //table name
public class Customer
{

    @Id  //pri key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// self generated UUI
    private int id;


    private String first_name;

    private String last_name;

    private String street;
    private String address;

    private String city;

    private String state;

    @Column(nullable=false, unique=true)
    private String email;


    private String phone;

    @Column(nullable=false)
    private String password;


}
