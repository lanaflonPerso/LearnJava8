package com.trl;

import java.util.Map;
import java.util.TreeMap;

public class ExampleJava8 {
    public static void main(String[] args) {
//---------------------------------------------------------------------------------------------------------------------

//        String value1 = "ABCDEF";
//        String[] value2 = {"A", "B", "C", "D", "E", "F"};
//
//        String join0 = String.join(", ", "A", "B", "C", "D", "E", "F");
//        String join1 = String.join(", ", value1);
//        String join2 = String.join(", ", value2);
//        System.out.println(join0);
//        System.out.println(join1);
//        System.out.println(join2);

//---------------------------------------------------------------------------------------------------------------------

////        Collection<String> strings = Arrays.asList("A", null, "B", "C", null, "D", "E", null, "F");
//        Collection<String> strings = Arrays.asList("A", "", "B", "C", "", "D", "E", "", "F");
////        Collection<String> strings = Arrays.asList("A", "1", "B", "C", "1", "D", "E", "1", "F");
//        String join = strings
//            .stream()
////            .filter(i -> i != null || i.isEmpty())  // neznay kak rabotaet ???
////            .filter(i -> !i.equals("1"))
//            .filter(i -> !i.equals(""))
//            .collect(Collectors.joining(", "));
//
//        System.out.println(join);

//---------------------------------------------------------------------------------------------------------------------

//        Map<String, Integer> data = new HashMap<>()
        Map<String, Integer> data = new TreeMap<>();
        data.put("A", 10);
        data.put("Z", 5);
        data.put("Q", 5);
        data.put("V", 5);
        data.put("F", 10);

        String[] stringsValues = {"A", "B", "C", "D", "E", "F"};

        // Kak delalos do Java 8
//        for (String value : stringsValues) {
//            if (!data.containsKey(value)) data.put(value, 0);
//            data.put(value, data.get(value) + 1);
//        }

        // A tak mozno delat posle vuxoda Java 8
        // Variant #1
        for (String s : stringsValues) data.merge(s, 1, (a, b) -> a + b);

        // Variant #2
//        data.forEach(s -> data.merge(s, 1, (a, b) -> a + b));

        for (Map.Entry f:data.entrySet()){
            System.out.println("key = " + f.getKey() + "   value = " + f.getValue());
        }

//---------------------------------------------------------------------------------------------------------------------
    }
}
