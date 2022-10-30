package com.example.spatiallasertestbakend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_a")
public class TableA {
    @Id
    public String address;

    public String city;

    public String state;

}
