package com.example.spatiallasertestbakend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TableARepository extends JpaRepository<TableA, Long> {

    TableA findTableAByState(String state);
}
