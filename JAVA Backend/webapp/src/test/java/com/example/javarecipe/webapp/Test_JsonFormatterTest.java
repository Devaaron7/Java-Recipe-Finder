package com.example.javarecipe.webapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class Test_JsonFormatterTest {

    @Test
    void thisShouldReturnAnEmptyArrayHashMapWhenARecipeIsNotFound() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        TitleSearchData search = new TitleSearchData();

        search.setData("steelpipe");

        HashMap<String, String> emptyOne = new HashMap<>();
        HashMap<String, String> emptyTwo = new HashMap<>();
        HashMap<String, String> emptyThree = new HashMap<>();

        ArrayList <HashMap> testCase = new ArrayList<>();
        testCase.add(emptyOne);
        testCase.add(emptyTwo);
        testCase.add(emptyThree);
        for (int i = 0; i < 3; i++) {

            testCase.get(i).put("id", "");
            testCase.get(i).put("title", "");
            testCase.get(i).put("sourceUrl", "");
            testCase.get(i).put("image", "");

        }



        assertEquals(testCase, app.read(search));
    }

    @Test
    void thisShouldReturnAnEmptyArrayHashMapWhenARecipeDoesNotIncludeAllIngredients() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        IngredientSearchData search = new IngredientSearchData();

        search.setTag1("book");
        search.setTag2("super");
        search.setTag3("makeup");

        HashMap<String, String> emptyOne = new HashMap<>();
        HashMap<String, String> emptyTwo = new HashMap<>();
        HashMap<String, String> emptyThree = new HashMap<>();

        ArrayList <HashMap> testCase = new ArrayList<>();
        testCase.add(emptyOne);
        testCase.add(emptyTwo);
        testCase.add(emptyThree);
        for (int i = 0; i < 3; i++) {

            testCase.get(i).put("id", "");
            testCase.get(i).put("title", "");
            testCase.get(i).put("sourceUrl", "");
            testCase.get(i).put("image", "");

        }



        assertEquals(testCase, app.read(search));
    }

}