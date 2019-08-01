package com.trl.stream_api;

public class CheckNullReferenceStream {

    public static void main(String[] args) {

//----------------Danui-premer-nam-pokazuvaet-kak-mozno-zdelat-proverky-na-null-----------------------------------------

//		Outer outer = new Outer();
//		if(outer != null && outer.nested != null && outer.nested.inner != null){
//			System.out.println(outer.nested.inner.foo);
//		}

//--------------------------------A-vot-tak-mozno-zdelat-s-pomowchy-StreamAPI-------------------------------------------

//		Optional.of(new Outer())
//				.flatMap(outer1 -> Optional.ofNullable(outer1.nested))
//				.flatMap(nested -> Optional.ofNullable(nested.inner))
//				.flatMap(inner -> Optional.ofNullable(inner.foo))
//				.ifPresent(System.out::println);

    }

}
