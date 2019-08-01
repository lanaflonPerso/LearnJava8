package com.trl.stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream_findFirst {

    public static void main(String[] args) {

//----------------------------------------------findFirst()-------------------------------------------------------------

		List<String> wordsWWW = new ArrayList<>(Arrays.asList("QA", "Ba", "Cxx", "QDa", "xxxxxE", "QBaas"));

		Optional<String> startsWithQ = wordsWWW.stream().filter(s -> s.startsWith("Q")).findFirst();

        System.out.println(startsWithQ);

    }

}
