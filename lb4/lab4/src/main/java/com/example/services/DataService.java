package com.example.services;

import com.example.model.Data;
import com.example.repositories.DataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataService {
    private final DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Transactional
    public boolean saveData(Data data) {
        dataRepository.save(data);
        return true;
    }

    @Transactional
    public void deleteData(String username) {
        dataRepository.deleteAllByUsername(username);
    }

    @Transactional
    public List<Data> getData(String username) {
        return dataRepository.getAllByUsername(username);
    }
}