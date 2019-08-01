package com.trl.stream_api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Stream_count {

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

//-------------------------------------------Rabota-s-methodom-count()--------------------------------------------------

		long count = words.stream().count();
        System.out.println(count);

    }

}
