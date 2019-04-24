package com.trl.lambda;

import java.util.ArrayList;

public class ExamplePredicate {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList();
        list.add(12);
        list.add(null);
        list.add(134);
        list.add(16);
        list.add(18);
        list.add(null);
        list.add(19);
        list.add(10);
        list.add(null);
        list.add(null);

        System.out.println(list);

        list.removeIf(e -> e == null);

        list.forEach((a) -> System.out.print(a + ", "));
    }
}
