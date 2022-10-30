package com.example.spatiallasertestbakend;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableAService {
    private TableARepository tableARepository;

    public TableAService(TableARepository tableARepository){
        this.tableARepository = tableARepository;
    }

    public List<TableA> getAllAddresses(){
        return tableARepository.findAll();
    }

}
