package com.trl.stream_api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Stream_min_max {

    public static void main(String[] args) {

		String contents = null;
		try {
			contents = new String(Files.readAllBytes(
					Paths.get("/home/trl/GitHub/LearnJava8/src/main/resources/StreamArchvos/alice30M.txt")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> words = null;

		if (contents != null) {
			words = Arrays.asList(contents.split("\\PL+"));
		}

//-------------------------------------------Robota-s-methodami-min()-max()---------------------------------------------

		Optional<String> min = words.stream().min(Comparator.comparing((String::length)));
		System.out.println(min);

		Optional<String> max = words.stream().max(Comparator.comparing((String::length)));
		System.out.println(max);

		List<String> words1 = new ArrayList<>();

		Optional<String> largest = words1.stream().max(String::compareToIgnoreCase);

		System.out.println("largest: " + largest.orElse("this Collection is empty"));

    }

}
