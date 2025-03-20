package com.nata.empresa.model;


import com.nata.empresa.model.types.Occupation;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "employee")
@Entity(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String lastname;
    private Occupation occupation;

}
