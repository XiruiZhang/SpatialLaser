package com.example.spatiallasertestproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TableARepository extends JpaRepository<TableA, Long> {

    TableA findTableAByState(String state);
}
