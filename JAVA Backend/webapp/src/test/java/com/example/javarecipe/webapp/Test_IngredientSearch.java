package com.example.javarecipe.webapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Test_IngredientSearch {



    @Test
    void thisShouldReturnAArrayHashMapOneItem() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        IngredientSearchData search = new IngredientSearchData();

        search.setTag1("sugar");
        search.setTag2("");
        search.setTag3("");

        assertInstanceOf(ArrayList.class, app.read(search));

    }

    @Test
    void thisShouldReturnAArrayHashMapTwoItems() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        IngredientSearchData search = new IngredientSearchData();

        search.setTag1("sugar");
        search.setTag2("chocolate");
        search.setTag3("");

        assertInstanceOf(ArrayList.class, app.read(search));

    }


    @Test
    void thisShouldReturnAArrayHashMapThreeItems() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        IngredientSearchData search = new IngredientSearchData();

        search.setTag1("sugar");
        search.setTag2("chocolate");
        search.setTag3("egg");

        assertInstanceOf(ArrayList.class, app.read(search));

    }
}