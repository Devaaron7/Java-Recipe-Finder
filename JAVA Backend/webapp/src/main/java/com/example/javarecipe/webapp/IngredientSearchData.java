package com.example.javarecipe.webapp;


import java.util.HashMap;

public class IngredientSearchData {
    private String tag1;

    private String tag2;

    private String tag3;

    public String getTag1() {

        return tag1;
    }

    public String getTag2() {

        return tag2;
    }

    public String getTag3() {

        return tag3;
    }

    public HashMap<String, String> getTagList() {
        HashMap<String, String> tagList = new HashMap<>();
        tagList.put("tag1", tag1);
        tagList.put("tag2", tag2);
        tagList.put("tag3", tag3);

        return tagList;
    }

    public void setTag1(String tag1) {

        this.tag1 = tag1;
    }

    public void setTag2(String tag2) {

        this.tag2 = tag2;
    }

    public void setTag3(String tag3) {

        this.tag3 = tag3;
    }

}