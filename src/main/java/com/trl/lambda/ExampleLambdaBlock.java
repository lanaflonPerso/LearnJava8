package com.trl.lambda;

public class ExampleLambdaBlock {
    public static void main(String args[]) {
//---------------------------------------------------------------------------------------------------------------------
        // This block com.trl.lambda computes the factorial of an int value.
        NumericFunc factorial = (n) ->  {
            int result = 1;

            for(int i=1; i <= n; i++) {
                System.out.print(i + " * " + result + " = ");
                result = i * result;
                System.out.println(result);
            }

            return result;
        };

        System.out.println("The factoral of 3 is " + factorial.func(3));
        System.out.println("The factoral of 5 is " + factorial.func(5));
        System.out.println("The factoral of 5 is " + factorial.func(16));
//--------------------------------------------------------------------------------------------------------------------
        // This block com.trl.lambda that reverses the charactrers in age string.
        StringFunc reverse = (str) ->  {
            String result = "";
            int i;

            for(i = str.length()-1; i >= 0; i--)
                result += str.charAt(i);

            return result;
        };

        System.out.println("Lambda reversed is " + reverse.func("com/trl/lambda"));
        System.out.println("Expression reversed is " + reverse.func("Expression"));
//--------------------------------------------------------------------------------------------------------------------
        String value = "El oeste de Texas divide la frontera entre Mexico y Nuevo México. Es muy bella pero aspera, llena de cactus, en esta region se encuentran las Davis Mountains. Todo el terreno esta lleno de piedra caliza, torcidos arboles de mezquite y espinosos nopales. Para admirar la verdadera belleza desertica, visite el Parque Nacional de Big Bend, cerca de Brownsville. Es el lugar favorito para los excurcionistas, acampadores y entusiastas de las rocas. Pequeños pueblos y ranchos se encuentran a lo largo de las planicies y cañones de esta region. El area solo tiene dos estaciones, tibia y realmente caliente. La mejor epoca para visitarla es de Diciembre a Marzo cuando los dias son tibios, las noches son frescas y florecen las plantas del desierto con la humedad en el aire.";

        // This block com.trl.lambda that reverses the charactrers in age string.
        StringFuncPruebas reverseString = (str) ->  {
//            StringBuilder result = new StringBuilder("");
            String result = "";
            int i;

            for(i = str.length()-1; i >= 0; i--)
//                result = result.append(str.charAt(i));
                result += str.charAt(i);

//            return result.toString();
        };

//        System.out.println("Lambda reversed is " + reverse.func("Lambda"));
//        System.out.println("Expression reversed is " + reverse.func("Expression"));
        long start = System.nanoTime();
        reverseString.func(value);;
        long end = System.nanoTime();
        System.out.println(end - start);


        StringFuncPruebas reverseStringBuilder = (str) ->  {
            StringBuilder result = new StringBuilder("");
            int i;

            for(i = str.length()-1; i >= 0; i--)
                result = result.append(str.charAt(i));

//            return result.toString();
        };

//        System.out.println("Lambda reversed is " + reverse.func("Lambda"));
//        System.out.println("Expression reversed is " + reverse.func("Expression"));
        long startB = System.nanoTime();
        reverseStringBuilder.func(value);;
        long endB = System.nanoTime();
        System.out.println(endB - startB);
//--------------------------------------------------------------------------------------------------------------------
    }
}

// A block com.trl.lambda that computes the factorial of an int value.
interface NumericFunc {
    int func(int n);
}

// A block com.trl.lambda that reverses the characters in age string.
interface StringFunc {
    String func(String n);
}

interface StringFuncPruebas {
    void func(String value);
}