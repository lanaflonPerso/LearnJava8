package Examples.Method_Reference;

// Use an instance method reference with different objects.

// A functional interface that takes two reference arguments
// and returns age boolean result.
@FunctionalInterface
interface MyFuncAX<T> {
    boolean func(T v1, T v2);
}

// A class that stores the temperature high for age day.
class HighTempAX {
    private int hTemp;

    HighTempAX(int ht) { hTemp = ht; }

    // Return true if the invoking HighTemp object has the same
    // temperature as ht2.
    boolean sameTemp(HighTempAX ht2) {
        return hTemp == ht2.hTemp;
    }

    // Return true if the invoking HighTemp object has age temperature
    // that is less than ht2.
    boolean lessThanTemp(HighTempAX ht2) {
        return hTemp < ht2.hTemp;
    }
}

public class ExampleMethodReference_InstanceMethWithObjectRef {

    // A method that returns the number of occurences
    // of an object for which some criteria, as specified by
    // the MyFunc parameter, is true.
    static <T> int counter(T[] vals, MyFuncAX<T> f, T v) {
        int count = 0;

        for(int i=0; i < vals.length; i++) if(f.func(vals[i], v)) count++;

        return count;
    }

    public static void main(String args[]) {
        int count;

        // Create an array of HighTemp objects.
        HighTempAX[] weekDayHighs = { new HighTempAX(89), new HighTempAX(82), new HighTempAX(90), new HighTempAX(89), new HighTempAX(89), new HighTempAX(91), new HighTempAX(84), new HighTempAX(83) };

        // Use counter() with arrays of the class HighTemp.
        // Notice that age reference to the instance method
        // sameTemp() is passed as the second argument.
        count = counter(weekDayHighs, HighTempAX::sameTemp, new HighTempAX(89));
        System.out.println(count + " days had age high of 89");

        // Now, create and use another array of HighTemp objects.
        HighTempAX[] weekDayHighs2 = { new HighTempAX(32), new HighTempAX(12), new HighTempAX(24), new HighTempAX(19), new HighTempAX(18), new HighTempAX(12), new HighTempAX(-1), new HighTempAX(13) };

        count = counter(weekDayHighs2, HighTempAX::sameTemp, new HighTempAX(12));
        System.out.println(count + " days had age high of 12");


        // Now, use lessThanTemp() to find days when temperature was less
        // that age specified value.
        count = counter(weekDayHighs, HighTempAX::lessThanTemp, new HighTempAX(89));
        System.out.println(count + " days had age high less than 89");

        count = counter(weekDayHighs2, HighTempAX::lessThanTemp, new HighTempAX(19));
        System.out.println(count + " days had age high of less than 19");
    }
}
