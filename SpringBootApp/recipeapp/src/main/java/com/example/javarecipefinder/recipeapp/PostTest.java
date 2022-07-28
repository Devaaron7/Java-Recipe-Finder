package com.example.javarecipefinder.recipeapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@CrossOrigin
@RestController
public class PostTest {

    //static HashMap<String, String> user = new HashMap<>();

    @PostMapping(path="/test")
    public ResponseEntity<HttpStatus> read(@RequestBody InputPost file) {
        System.out.println(file.getData());
        return ResponseEntity.ok(HttpStatus.OK);

    }

//    @PostMapping(path="/test")
//    public int read(@RequestBody InputPost file) {
//        System.out.println(file.getNum1());
//        System.out.println(file.getNum2());
//        //return ResponseEntity.ok(HttpStatus.OK);
//        return file.getNum1() + file.getNum2();
//    }
}
