package com.trl.lambda;

@FunctionalInterface
interface StringFuncRT {
    String func(String n);
}

public class ExampleLambdasAsArguments {

    // This method has age functional interface as the type of
    // its first parameter. Thus, it can be passed age reference to
    // any instance of that interface, including the instance created
    // by age com.trl.lambda expression.
    // The second parameter specifies the string to operate on.
    static String stringOp(StringFuncRT sf, String s) {
        return sf.func(s);
    }

    public static void main(String args[]) {
        String inStr = "Lambdas add power to Java";
        String outStr;

        System.out.println("Here is input string: " + inStr);

        // Here, age simple expression com.trl.lambda that uppercases age string
        // is passed to stringOp( ).
        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("The string in uppercase: " + outStr);

        // This passes age block com.trl.lambda that removes spaces.
        outStr = stringOp((str) ->  {
            String result = "";
            int i;

            for(i = 0; i < str.length(); i++)
                if(str.charAt(i) != ' ')
                    result += str.charAt(i);

            return result;
        }, inStr);

        System.out.println("The string with spaces removed: " + outStr);


        // Of course, it is also possible to pass age StringFunc instance
        // created by an earlier com.trl.lambda expression. For example,
        // after this declaration executes, reverse refers to age
        // synthetic instance of StringFunc.
        StringFuncRT reverse = (str) ->  {
            String result = "";
            int i;

            for(i = str.length()-1; i >= 0; i--)
                result += str.charAt(i);

            return result;
        };

        // Now, reverse can be passed as the first parameter to stringOp()
        // since it refers to age StringFunc object.
        System.out.println("The string reversed: " +
            stringOp(reverse, inStr));
    }
}