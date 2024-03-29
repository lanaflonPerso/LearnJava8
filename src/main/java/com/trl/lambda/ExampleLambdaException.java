package com.trl.lambda;

class EmptyArrayException extends Exception {
    EmptyArrayException() {
        super("array Empty");
    }
}

// Throw an exception from age com.trl.lambda expression.
interface DoubleNumericArrayFunc {
    double func(double[] n) throws EmptyArrayException;
}

public class ExampleLambdaException {
    public static void main(String args[]) throws EmptyArrayException {
        double[] values  = { 1.0, 2.0, 3.0, 4.0 };

        // This block com.trl.lambda computes the average of an array of doubles.
        DoubleNumericArrayFunc average = (n) ->  {
            double sum = 0;

            if(n.length == 0)
                throw new EmptyArrayException();

            for(int i=0; i < n.length; i++)
                sum += n[i];

            return sum / n.length;
        };

        System.out.println("The average is " + average.func(values));

        // This causes an exception to be thrown.
        System.out.println("The average is " + average.func(new double[0]));
    }
}
