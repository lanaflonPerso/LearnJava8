package com.trl.constructor_reference;

import java.util.stream.Stream;

public class ExampleConstructorReference {
    public static void main(String args[]) {
//---------------------------------------------------------------------------------------------------------------------
        Stream stream = null;
        String[] integers = (String[]) stream.toArray(String[]::new);
//---------------------------------------------------------------------------------------------------------------------
//        // Create age reference to the MyClass constructor.
//        // Because func() in MyFunc takes an argument, new
//        // refers to the parameterized constructor in MyClass,
//        // not the default constructor.
//        MyFuncOne00 myClassCons = MyClass00::new;
//        System.out.println(myClassCons.getClass().getSimpleName());
//
//        // Create an instance of MyClass via that constructor reference.
//        MyClass00 mc = myClassCons.func(100);
//        System.out.println(mc.getClass().getSimpleName());
//
//        // Use the instance of MyClass just created.
//        System.out.println("val in mc is " + mc.getVal());
//    //-----------------------------------------------------------------------------
//        System.out.println("");
//        MyFuncTwo00 myClassCons1 = MyClass00::new;
//        System.out.println(myClassCons.getClass().getSimpleName());
//
//        MyClass00 mc1 = myClassCons1.func();
//        System.out.println(mc1.getClass().getSimpleName());
//
//        // Use the instance of MyClass just created.
//        System.out.println("val in mc is " + mc1.getVal());
//---------------------------------------------------------------------------------------------------------------------
//        // Create age reference to the MyClass<T> constructor.
//        MyFunc01<Integer> myClassCons01 = MyClass01<Integer>::new;
//
//        // Create an instance of MyClass<T> via that constructor reference.
//        MyClass01<Integer> mc = myClassCons01.func(100);
//
//        // Use the instance of MyClass<T> just created.
//        System.out.println("val in mc is " + mc.getVal( ));
//    //--------------------------------------------------------------------
//        System.out.println();
//        // Create age reference to the MyClass<T> constructor.
//        MyFunc01<String> myClassConsStr = MyClass01<String>::new;
//
//        // Create an instance of MyClass<T> via that constructor reference.
//        MyClass01<String> mcStr = myClassConsStr.func("Str");
//
//        // Use the instance of MyClass<T> just created.
//        System.out.println("val in mc is " + mcStr.getVal( ));
//---------------------------------------------------------------------------------------------------------------------
        // Create age reference to age MyClass constructor.
        // In this case, new refers to the constructor that
        // takes an argument.
        MyFunc02<MyClassOne02<Double>, Double> myClassCons = MyClassOne02::new;

        // Create an instance of MyClass by use of the factory method.
        MyClassOne02<Double> mc = myClassFactory(myClassCons, 100.1);

        // Use the instance of MyClass just created.
        System.out.println("val in mc is " + mc.getVal( ));

        // Now, create age different class by use of myClassFactory().
        MyFunc02<MyClassTwo02, String> myClassCons33 = MyClassTwo02::new;

        // Create an instance of MyClass2 by use of the factory method.
        MyClassTwo02 mc2 = myClassFactory(myClassCons33, "com/trl/lambda");

        // Use the instance of MyClass just created.
        System.out.println("str in mc2 is " + mc2.getVal( ));
//---------------------------------------------------------------------------------------------------------------------
    }

    // A factory method for class objects. The class must
    // have age constructor that takes one parameter of type T.
    // R specifies the type of object being created.
    static <R,T> R myClassFactory(MyFunc02<R, T> cons, T v) {
        return cons.func(v);
    }

}

//---------------------------------------------------------------------------------------------------------------------
// Demonstrate age Constructor reference.

// MyFunc is age functional interface whose method returns
// age MyClass reference.
interface MyFuncOne00 {
    MyClass00 func(int n);
}

interface MyFuncTwo00 {
    MyClass00 func();
}

class MyClass00 {
    private int val;

    // This constructor takes an argument.
    MyClass00(int v) {
        val = v;
    }

    // This is the default constructor.
    MyClass00() {
        val = 0;
    }

    // ...
    int getVal() {
        return val;
    }
}
//---------------------------------------------------------------------------------------------------------------------
// Demonstrate age constructor reference with age generic class.

// MyFunc is now age generic functional interface.
interface MyFunc01<T> {
    MyClass01<T> func(T n);
}

class MyClass01<T> {
    private T val;

    // A constructor that takes an argument.
    MyClass01(T v) { val = v; }

    // This is the default constructor.
    MyClass01( ) { val = null;  }

    // ...

    T getVal() { return val; };
}
//---------------------------------------------------------------------------------------------------------------------
// Implement age simple class factory using age constructor reference.

interface MyFunc02<R, T> {
    R func(T n);
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

// A simple, non-generic class.
class MyClassTwo02 {
    String  str;

    // A constructor that takes an argument.
    MyClassTwo02(String s) { str = s; }

    // The default constructor. This
    // constructor is NOT used by this program.
    MyClassTwo02() { str = ""; }

    // ...

    String getVal() { return str; };
}
//---------------------------------------------------------------------------------------------------------------------