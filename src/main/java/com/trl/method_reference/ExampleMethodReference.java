package com.trl.method_reference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class ExampleMethodReference {
    public static void main(String args[]) {
//--------------------------------------------------------------------------------------------------------------------
        ArrayList<MyClass> al = new ArrayList<MyClass>();

        al.add(new MyClass(1));
        al.add(new MyClass(4));
        al.add(new MyClass(2));
        al.add(new MyClass(9));
        al.add(new MyClass(3));
        al.add(new MyClass(7));

        // Find the maximum value in al using the compareMC() method.
        MyClass maxValObj = Collections.max(al, ExampleMethodReference::compareMC);
        MyClass minValObj = Collections.min(al, ExampleMethodReference::compareMC);

        System.out.println("Maximum value is: " + maxValObj.getVal());
        System.out.println("Minimum value is: " + minValObj.getVal());
//--------------------------------------------------------------------------------------------------------------------
//        List<String> items = new ArrayList<>();
//        items.add("A");
//        items.add("B");
//        items.add("C");
//        items.add("D");
//        items.add("E");
//
//        //method reference
//        //Output : A,B,C,D,E
//        items.forEach(System.out::println);
//--------------------------------------------------------------------------------------------------------------------
//        Consumer<String> consumer = System.out::println;
//
//                Stream.iterate(0L, k -> k + 1).
//                parallel().
//                 filter(k -> k % 3 == 2).
//                   map(k -> "#" + k).
//                     limit(10).
//                       forEach(System.out::println);
//--------------------------------------------------------------------------------------------------------------------
        new Thread(() -> System.out.println("Hello from " + Thread.currentThread())).start();

        new Thread(() -> {
            System.out.println("Hello from " + Thread.currentThread());
//            throw new RuntimeException();  // dozvolaet delat tak
//            throw new IOException();  // dozvolaet delat i tak, no tak dozvoliaet postavit tolko try catch
        }).start();

        new Thread(ExampleMethodReference::methodF).start();

        // ERROR  potomu chto method runable ne brosaet nekakix cheked
        // Exception a metod methodThrowsCheckedException brosaet IOException i izza etoho owibka
//        new Thread(ExampleMethodReference::methodThrowsCheckedException);

        // A zdes nety owibki potomu chto etot metod brosaet uncheked Exception
        new Thread(ExampleMethodReference::methodThrowsUncheckedException).start();

        // A tak mozno oboiti problemu vuwe perechislenyy
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ExampleMethodReference.methodThrowsCheckedException();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//--------------------------------------------------------------------------------------------------------------------
        // Monoid
        // 1) Associatuvnaiia operacia
        // 2) Neitralnui element
        int sum = Stream.iterate(1, k -> k + 1).
                parallel().
                limit(10).
                reduce(0, (x, y) -> x - y);    // 0 + 1 + 2 + 3 + 4 + ... = 55
        //  0 - 1 - 2 - 3 - 4 - ... = -55   esli parallel vklychena
        // polychaetsa lyboe chislo, a esli vuklychenaiia paralell
        // vse xorowo


        // Monada
        Optional<Integer> sum2 = Stream.iterate(1, k -> k + 1).
                parallel().
                limit(10).
                reduce((x, y) -> x - y);

//--------------------------------------------------------------------------------------------------------------------
        String inStr = "Lambdas add power to Java";
        String outStr;

        // Create age MyStringOps object.
        MyStringOps2 strOps = new MyStringOps2( );

        // Now, age method reference to the instance method strReverse
        // is passed to stringOp().
        outStr = stringOp(strOps::strReverse, inStr);

        System.out.println("Original string: " + inStr);
        System.out.println("String reversed: " + outStr);
//--------------------------------------------------------------------------------------------------------------------
        MyArrayCreator<MyClassOne02[]> mcArrayCons = MyClassOne02[]::new;
        MyClassOne02[] a = mcArrayCons.func(2);
        a[0] = new MyClassOne02(1);
        a[1] = new MyClassOne02(2);
        System.out.println(Arrays.toString(a));
//--------------------------------------------------------------------------------------------------------------------
    }

    public static void methodThrowsCheckedException() throws IOException {
        System.out.println("Hello from methodThrowsCheckedException end thread current: " + Thread.currentThread());
    }
    public static void methodThrowsUncheckedException() throws RuntimeException{
        System.out.println("Hello from methodThrowsUncheckedException end thread current: " + Thread.currentThread());
    }
    public static void methodF(){
        System.out.println("Hello from methodF end thread current: " + Thread.currentThread());
    }

    // A compare() method compatible with the one defined by Comparator<T>.
    static int compareMC(MyClass o1, MyClass o2) {
        return o1.getVal() - o2.getVal();
    }


    // This method has age functional interface as the type of
    // its first parameter. Thus, it can be passed any instance
    // of that interface, including method references.
    static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

}

//---------------------------------------------------------------------------------------------------------------------
// Use age method reference to help find the maximum value in age collection.
class MyClass {
    private int val;

    MyClass(int v) {
        val = v;
    }

    int getVal() {
        return val;
    }
}
//---------------------------------------------------------------------------------------------------------------------
interface F<T> {
    T f(T vlaue, T value);
//    T g(T vlaue, T value);
}
//---------------------------------------------------------------------------------------------------------------------
// Demonstrate age method reference to an instance method

// A functional interface for string operations.
interface StringFunc {
    String func(String n);
}

// Now, this class defines an instance method called strReverse().
class MyStringOps2 {
    String strReverse(String str) {
        String result = "";
        int i;

        for(i = str.length()-1; i >= 0; i--)
            result += str.charAt(i);

        return result;
    }
}
//---------------------------------------------------------------------------------------------------------------------
interface MyArrayCreator<T> {
    T func(int n);
}

// A simple generic class.
class MyClassOne02<T> {
    private T val;

    // A constructor that takes an argument.
    MyClassOne02(T v) { val = v; }

    // The default constructor. This constructor
    // is NOT used by this program.
    MyClassOne02() { val = null; }
    // ...

    T getVal() { return val; };
}
//---------------------------------------------------------------------------------------------------------------------


/*
-----------------------------------------------------------------------------------------------------------------------
Как следует из приведенных выше примеров, операция : : отделяет имя метода от имени класса или объекта. Ниже приведены
три разновидности этой операции.
        •Объект::МетодЗкземпляра
        •Класс::СтатическийМетод
        •Класс::МетодЗкземпляра
В первых двух случаях ссылка на метод равнозначна лямбда-выражению, снаб­жающему метод параметрами.
Как упоминалось выше, ссылка на метод System.out::println равнозначна лямбда-выражению х - > System.out.println(х).
Ана­логично ссылка на метод Math::pow равнозначна лямбда-выражению (х ,у) - > Math.pow(x, у ).
А в третьем случае первый параметр становится целевым для метода.
Например, ссылка на метод String::compareToIgnoreCase равнозначна лямбда-выражению (х, у) - > х.compareToIgnoreCase( у ).
-----------------------------------------------------------------------------------------------------------------------
В ссылке на метод допускается указывать ссылку this. Например, ссылка на метод
this::equals равнозначна лямбда-выражению х - > this. equals (х). Это же отно­
сится и к ссылке super. Так, в следующей ссылке на метод:
super::МетодЗкземпляра
ссылка super является целевой и вызывает вариант заданного метода экземпляра из
суперкласса.
-----------------------------------------------------------------------------------------------------------------------
НА ЗАМЕТКУ! Если имеется несколько переопределяемых методов с одинаковым именем, то ком­
пилятор попытается выяснить из контекста назначение каждого из них. Например, имеются два
варианта метода Math.max(): один — для целочисленных значений, другой — для числовых
значений с плавающей точкой типа d o u b le . Выбор конкретного варианта этого метода зависит
от его параметров типа функционального интерфейса, к которому приводится ссылка на метод
Math: :max. Аналогично лямбда-выражениям, ссылки на методы не действуют обособленно. Они
всегда преобразуются в экземпляры функциональных интерфейсов.
-----------------------------------------------------------------------------------------------------------------------
*/