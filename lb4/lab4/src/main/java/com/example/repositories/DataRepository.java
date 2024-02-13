package com.example.repositories;

import com.example.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<Data, Integer> {
    void deleteAllByUsername(String username);
    List<Data> getAllByUsername(String username);
}