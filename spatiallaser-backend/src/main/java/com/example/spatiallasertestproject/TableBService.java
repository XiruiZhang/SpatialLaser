package com.example.spatiallasertestproject;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableBService {
    private TableBRepository tableBRepository;

    public TableBService(TableBRepository tableBRepository){
        this.tableBRepository = tableBRepository;
    }

    public List<TableB> getAllAddresses(){
        return tableBRepository.findAll();
    }
}
