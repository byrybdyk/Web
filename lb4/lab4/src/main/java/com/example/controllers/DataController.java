package com.example.controllers;

import com.example.model.Data;
import com.example.services.DataService;
import com.example.DTO.DataDTO;
import com.example.config.jwt.JWTProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/data")
public class DataController {
    private final DataService dataService;
    private final JWTProvider jwtProvider;

    public DataController(DataService dataService, JWTProvider jwtProvider) {
        this.dataService = dataService;
        this.jwtProvider = jwtProvider;
    }

    @CrossOrigin
    @PostMapping("/add-data")
    public ResponseEntity<String> addData(@Valid @RequestBody DataDTO dataDTO, HttpServletRequest request) {
        double x = dataDTO.getX();
        double y = dataDTO.getY();
        double r = dataDTO.getR();
        Data data = new Data(x, y, r);
        data.checkAll();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        data.setTime(time);
        String username = jwtProvider.getLoginFromToken(request.getHeader("Authorization").substring(7));
        data.setUsername(username);
        System.out.println("Received data from user: " + username);
        try {
            dataService.saveData(data);
            System.out.println("Data saved successfully");
            return new ResponseEntity<>("Точка успешно добавлена", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Ошибка при добавлении точки", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/get-data")
    private ResponseEntity<List<Data>> getData(HttpServletRequest request) {
        String username = jwtProvider.getLoginFromToken(request.getHeader("Authorization").substring(7));
        List<Data> mass = dataService.getData(username);
        return new ResponseEntity<>(mass, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    private ResponseEntity<String> deleteData(HttpServletRequest request) {
        String username = jwtProvider.getLoginFromToken(request.getHeader("Authorization").substring(7));
        dataService.deleteData(username);
        return new ResponseEntity<>("Точки пользователя удалены", HttpStatus.OK);
    }
}