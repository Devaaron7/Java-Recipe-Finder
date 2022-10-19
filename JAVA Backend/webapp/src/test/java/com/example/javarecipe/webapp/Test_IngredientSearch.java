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

        search.setTag1("chicken");
        search.setTag2("");
        search.setTag3("");

        assertInstanceOf(ArrayList.class, app.read(search));

    }

    @Test
    void thisShouldReturnAArrayHashMapTwoItems() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        IngredientSearchData search = new IngredientSearchData();

        search.setTag1("chicken");
        search.setTag2("cheese");
        search.setTag3("");

        assertInstanceOf(ArrayList.class, app.read(search));

    }


    @Test
    void thisShouldReturnAArrayHashMapThreeItems() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        IngredientSearchData search = new IngredientSearchData();

        search.setTag1("chicken");
        search.setTag2("cheese");
        search.setTag3("pasta");

        assertInstanceOf(ArrayList.class, app.read(search));

    }
}