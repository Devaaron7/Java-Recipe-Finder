package com.example.javarecipefinder.recipeapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PostTest {

    //static HashMap<String, String> user = new HashMap<>();

    @PostMapping("/test")
    public ResponseEntity read(@RequestBody InputPost file) {

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
