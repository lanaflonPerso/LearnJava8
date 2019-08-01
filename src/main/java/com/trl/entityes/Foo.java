package com.trl.entityes;

import java.util.ArrayList;
import java.util.List;

public class Foo {

    private String name;
    private List<Bar> listBars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Bar> getListBars() {
        return listBars;
    }
}
