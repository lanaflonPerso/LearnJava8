package com.trl.stream_api;

import com.trl.entityes.Bar;
import com.trl.entityes.Foo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Stream_faltMap {
    public static void main(String[] args) {

        //--------------------------------------------------flatMap()-----------------------------------------------------------

        // flatMap() preobrazyet kazdui element potoka v potok dryhix obektov, takim obrazom kazdui obiekt bydet
        // pereobrazovan v novui potok drygix obektov

        List<Foo> fooList = new ArrayList<>();

        // create foos
        IntStream.range(1,4).
                forEach(i -> fooList.add(new Foo("Foo " + i)));

        fooList.forEach(foo -> IntStream.
                range(1,4).
                forEach(i -> foo.getListBars().add(new Bar("Bar " + i + " <- " + foo.getName()))));

//		fooList.stream().
//				flatMap(foo -> foo.listBars.stream()).
//				forEach(bar -> System.out.println(bar.getName()));

//------------------------------Analogichne-rewenie-no-zdelano-nemnoho-po-drygomy---------------------------------------

//		fooList.stream().
//				flatMap(foo -> foo.listBars.stream()).
//				forEach(bar -> System.out.println(bar.getName()));

//		IntStream.range(1,4).
//				mapToObj(i -> new Foo("Foo" + i)).
//				peek(foo -> IntStream.range(1,4).
//						mapToObj(i -> new Bar("Bar" + i + " <- " + foo.getName())).
//						forEach(foo.listBars::add)).
//				flatMap(foo -> foo.listBars.stream()).
//				forEach(bar -> System.out.println(bar.getName()));

    }
}