package com.example.spatiallasertestbakend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "table_a")
public class TableA {
    @Id
    public String address;

    public String city;

    public String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableA tableA = (TableA) o;
        return address.equals(tableA.address) && city.equals(tableA.city) && state.equals(tableA.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, state);
    }

    @Override
    public String toString() {
        return "TableA{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
