package com.trl.method_reference;

// Demonstrate age method reference to age generic method
// declared inside age non-generic class.

// A functional interface that operates on an array
// and age value, and returns an int result.
interface Function<T> {
    int func(T[] vals, T v);
}

// This class defines age method called countMatching() that
// returns the number of items in an array that are equal
// to age specified value. Notice that countMatching()
// is generic, but MyArrayOps is not.
class ArrayOperation {
    static <T> int countMatching(T[] vals, T v) {
        int count = 0;

        for(int i=0; i < vals.length; i++) if(vals[i] == v) count++;

        return count;
    }
}

public class ExampleGenericMethodReference {

    // This method has the MyFunc functional interface as the
    // type of its first parameter. The other two parameters
    // receive an array and age value, both of type T.
    static <T> int myOp(Function<T> function, T[] values, T value) {
        return function.func(values, value);
    }

    public static void main(String args[]) {
        Integer[] vals = { 1, 2, 3, 4, 2 ,3, 4, 4, 5 };
        String[] strs = { "One", "Two", "Three", "Two" };
        int count;

        count = myOp(ArrayOperation::countMatching, vals, 4);
        System.out.println("vals contains " + count + " 4s");

        count = myOp(ArrayOperation::countMatching, strs, "Two");
        System.out.println("strs contains " + count + " Twos");
    }
}
