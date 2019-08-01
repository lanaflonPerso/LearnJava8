package com.trl.stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Stream_sorted {

    public static void main(String[] args) {

//-------------------------------------------Rabota-s-methodom-sorted()-------------------------------------------------

        List<String> words = new ArrayList<>(Arrays.asList("A", "Ba", "Cxx", "Da", "xxxxxE", "Baas"));

		words.stream()
				.sorted(Comparator.comparing(String::length).reversed())
				.forEach(v -> System.out.print(v + ", "));

		System.out.println(words);

    }

}
