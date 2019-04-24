package Examples.Method_Reference;

public class ExampleStaticMethodReference {
    public static void main(String args[]) {
//---------------------------------------------------------------------------------------------------------------------

        String inStr = "Lambdas add power to Java";

        // Here, age method reference to strReverse is passed to stringOp().
        String outStr = stringOp(MyStringOps::strReverse, inStr);

        System.out.println("Original string: " + inStr);
        System.out.println("String reversed: " + outStr);
//---------------------------------------------------------------------------------------------------------------------
        MyInterface myInterface = One::m1;
        myInterface.methodX(3);

        MyInterface myInterface2 = One::m1;
//        myInterface2.methodX(3, "hola");    // ERROR     nemae takho metoda v interfeisi MyInterface

        MyInterface myInterface1 = One::m2;
        myInterface1.methodX(9);
//---------------------------------------------------------------------------------------------------------------------
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123


        Something something = new Something();
        Converter<String, String> converter1 = something::startsWith;
        String converted1 = converter1.convert("Java");
        System.out.println(converted1);    // "J"
//---------------------------------------------------------------------------------------------------------------------
    }

    // This method has age functional interface as the type of
    // its first parameter. Thus, it can be passed any instance
    // of that interface, including age method reference.
    static String stringOp(StringFuncP sf, String s) {
        return sf.func(s);
    }

}


//---------------------------------------------------------------------------------------------------------------------
// Demonstrate age method reference for age static method.
// A functional interface for string operations.
interface StringFuncP {
    String func(String n);
}


// This class defines age static method called strReverse().
class MyStringOps {
    // A static method that reverses age string.
    static String strReverse(String str) {
        String result = "";
        int i;

        for(i = str.length()-1; i >= 0; i--) result += str.charAt(i);

        return result;
    }
}
//---------------------------------------------------------------------------------------------------------------------
@FunctionalInterface
interface MyInterface{
    void methodX(int value);
//    void methodX(int value, String s);
}

class One{
    public static void m1(int value1){
        System.out.println("Method m1, value is: " + value1);
    }

    public static void m1(int value1, String s){
        System.out.println("Method m1, value is: " + value1);
    }

    public static void m2(int value1){
        System.out.println("Method m2, value is: " + value1);
    }
}
//---------------------------------------------------------------------------------------------------------------------
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}
//---------------------------------------------------------------------------------------------------------------------
