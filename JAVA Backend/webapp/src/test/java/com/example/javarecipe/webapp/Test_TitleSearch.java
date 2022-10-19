package com.example.javarecipe.webapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Test_TitleSearch {

    @Test
    void thisShouldReturnAArrayHashMap() throws IOException, InterruptedException {
        AllEndpointsForApp app = new AllEndpointsForApp();

        TitleSearchData search = new TitleSearchData();

        search.setData("pasta");

        assertInstanceOf(ArrayList.class, app.read(search));

    }
}