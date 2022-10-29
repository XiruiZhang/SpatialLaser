package com.example.spatiallasertestproject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_b")
public class TableB {
    @Id
    public String address;

    public String city;

    public String state;
}
